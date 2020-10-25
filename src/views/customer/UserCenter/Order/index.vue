<template>
    <div class="customerOrder-contain">
        <el-menu
                :default-active="activeIndex"
                class="el-menu-demo"
                mode="horizontal"
                router
                style="margin: 0 20px;"
        >
            <el-menu-item index="/customerOrder/totalOrder">全部订单</el-menu-item>
            <el-menu-item index="/customerOrder/finish">已完成</el-menu-item>
            <el-menu-item index="/customerOrder/waitingPay">待支付</el-menu-item>
            <el-menu-item index="/customerOrder/waitingEvaluation">待评价</el-menu-item>
        </el-menu>
        <div class="customerOrder-main" v-loading="listLoading" element-loading-text="正在加载中">
            <div v-if="flag">
                <div v-if="total===0" class="text">
                    对不起，您没有该类型的订单
                </div>
                <div v-else>
                    <div v-for="(item, index) in orderInfo" :key="index" class="customerOrder-orderInfo clearFix">
                        <div class="customerOrder-orderInfo-img" @click="orderDetail(item)">
                            <el-image
                                    style="width: 80px; height: 80px;vertical-align: middle"
                                    :src="item.orderImg"
                                    :fit="'contain'"
                            >
                                <div slot="placeholder" class="image-slot"></div>
                            </el-image>
                        </div>
                        <div class="customerOrder-orderInfo-info" @click="orderDetail(item)">
                            <div class="info-box">
                                <p class="info-title">{{item.movieName}}</p>
                                <p class="info">下单时间：{{item.orderTime}}</p>
                                <p class="info">数量：{{ Object.keys(item.orderSeat).length}}</p>
                            </div>


                        </div>
                        <div class="customerOrder-orderInfo-price">
                            总价：￥{{item.orderPrice}}
                        </div>
                        <div class="customerOrder-orderInfo-status">

                            <div v-if="item.orderStatus === '待评价'">
                                <el-button class="customerOrder-orderInfo-do" @click="goEvaluation(item)">评价</el-button>
                            </div>
                            <div v-else-if="item.orderStatus === '待支付'">
                                <el-button class="customerOrder-orderInfo-do" @click="goPay(item)">支付</el-button>
                            </div>
                            <div v-else>
                                {{item.orderStatus}}
                            </div>
                        </div>
                    </div>
                </div>

                <el-pagination
                        v-if="total>5"
                        class="order-page"
                        background
                        :current-page="queryForm.pageNo"
                        :page-size="queryForm.pageSize"
                        :layout="layout"
                        :total="total"
                        @current-change="handleCurrentChange"
                ></el-pagination>
            </div>
        </div>
        <edit ref="edit"></edit>
    </div>
</template>

<script>
    import {getOrderList} from "@/api/order";
    import Edit from "./components/orderDetail.vue";

    export default {
        name: "Order",
        components: {Edit},
        data() {
            return {
                type: "all",
                orderAllInfo: '',
                middleInfo: '',
                orderInfo: '',
                listLoading: false,
                queryForm: {
                    pageNo: 1,
                    pageSize: 5,
                },
                total: 1,
                handleSizeChange: '',
                flag: false,
                layout: "total,prev, pager, next, jumper",
            };
        },
        created() {
            this.type = this.$route.params.type
            console.log("当前类型------：" + this.type)
            this.fetchData();

        },
        computed: {
            activeIndex() {
                const route = this.$route;
                const {path} = route;
                return path;
            },
        },
        watch: {
            $route(to, from) {
                this.type = to.params.type;
                console.log("当前类型：" + this.type)
                this.fetchData();
                console.log(this.orderInfo)
            }
        },
        mounted() {

        },
        methods: {
            goEvaluation(item) {
                this.$router.push({
                    path: "/customerEvaluation/index",
                    query: {
                        data:
                            {
                                orderInfo: {
                                    orderId: item.orderId,
                                    orderImg: item.orderImg,
                                    movieName:  item.movieName,
                                    orderTime:  item.orderTime,
                                },
                                evaluationScore: null,
                                evaluationContent: null,
                                evaluationTime: null,
                            }

                    }
                })
            },
            goPay(item) {
                this.$router.push({
                    path: "/ticket/orderGenerated",
                    query: {
                        orderId: item.orderId
                    }
                })
            },
            orderDetail(item) {
                this.$refs["edit"].showEdit(item);

            },
            handleCurrentChange(val) {
                this.queryForm.pageNo = val;
                this.pagingInfo();
            },
            pagingInfo() {
                this.orderInfo = this.middleInfo.filter(
                    (item, index) =>
                        index < this.queryForm.pageSize * this.queryForm.pageNo && index >= this.queryForm.pageSize * (this.queryForm.pageNo - 1)
                );
            },
            filterData() {

                this.middleInfo = this.orderAllInfo;
                this.total = this.middleInfo.length;
                this.queryForm.pageNo = 1;
                console.log(this.total)
                // this.total = 100;
                this.pagingInfo();

            },
            async fetchData() {
                if (this.type) {
                    this.flag = false;
                    this.total = 0;
                    this.listLoading = true;
                    const {data} = await getOrderList({type: this.type});
                    setTimeout(() => {
                        this.orderAllInfo = data.orderInfo;
                        this.filterData();
                        this.flag = true;
                        this.listLoading = false;
                    }, 1000);

                }
            },

        },
    };
</script>
<style>
    .customerOrder-contain {
        margin-top: 20px;
        width: 1000px;
        height: 800px;
        border: 1px #e6e6e6 solid;
        float: left;
        background-color: #fff;
        font-family: "-apple-system", "BlinkMacSystemFont", "Roboto", "Helvetica Neue", "MIcrosoft YaHei", sans-serif !important;
    }

    .customerOrder-main {
        height: 720px;
    }

    .customerOrder-contain .el-menu-item {
        width: 120px;
        text-align: center;
    }

    .customerOrder-contain .el-menu--horizontal > .el-menu-item.is-active {
        border-bottom: 2px solid rgb(230, 82, 83);
        color: #303133;
    }

    .customerOrder-orderInfo {
        width: 800px;
        margin: 0 auto;
        border-top: 1px solid #E5E5E5;
        padding: 20px 0;
        /*cursor: pointer;*/
    }

    .clearFix:after {
        content: " ";
        visibility: hidden;
        display: block;
        height: 0;
        clear: both;
    }

    .customerOrder-orderInfo-img {
        float: left;
        margin-top: 7px;
        cursor: pointer;
    }

    .customerOrder-orderInfo-info {
        float: left;
        height: 90px;
        width: 325px;
        position: relative;
        cursor: pointer;
    }

    .customerOrder-orderInfo-price {
        float: left;
        font-size: 12px;
        color: #666666;
        line-height: 90px;
        text-align: center;
        width: 200px;
    }

    .customerOrder-orderInfo-status {
        float: left;
        font-size: 12px;
        color: #333;
        line-height: 90px;
        text-align: center;
        width: 100px;
    }

    .customerOrder-orderInfo-do {
        padding: 10px 10px;
        text-align: center;
        color: rgb(230, 82, 82);
        width: 65px;
        border-radius: 20px;
        font-size: 12px;
    }

    .info-box {
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
    }

    .info-title {
        font-size: 16px;
        color: #222222;
        margin: 10px 0 10px 15px;
        font-weight: 500;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        max-width: 300px;
    }

    .info {
        font-size: 12px;
        color: #666666;
        margin-left: 15px;
        height: 18px;
    }

    .order-page {
        /*width: 100px;*/
        /*margin: 0 auto  ;*/
        text-align: center;
        margin-top: 20px;
        margin-bottom: 30px;
    }

    .text {
        border-top: 1px solid #E5E5E5;
        padding: 40px 0;
        text-align: center;
        font-size: 14px;
        color: #666;
    }

</style>
<template>
    <div class="customerEvaluation-container">
        <el-table
                ref="filterTable"
                :data="evaluationList"
                style="width: 1000px;height: 700px;overflow: scroll;border: none"
                v-loading="listLoading"
                element-loading-text="正在加载中"
        >
            <el-table-column label="订单信息" align="center">
                <template slot-scope="scope">
                    <!--          <el-image-->
                    <!--                  style="width: 80px; height: 80px;vertical-align: middle;float:left;"-->
                    <!--                  :src="scope.row.orderInfo.orderImg"-->
                    <!--                  :fit="'contain'"-->
                    <!--          >-->
                    <!--          </el-image>-->
                    <span class="info">电影名称：{{scope.row.orderInfo.movieName}}</span>
                    <span class="info">订单时间：{{scope.row.orderInfo.orderTime}}</span>
                </template>
            </el-table-column>
            <el-table-column prop="evaluationScore" label="评分" width="100px" align="center">
                <template slot-scope="scope">
                    <span class="info">{{scope.row.evaluationScore===null? '待评价' : scope.row.evaluationScore}}</span>
                </template>
            </el-table-column>
            <el-table-column prop="evaluationContent" label="评论" align="center">
                <template slot-scope="scope">
                    <span class="info">{{scope.row.evaluationContent===null? '待评价' : scope.row.evaluationContent}}</span>
                </template>
            </el-table-column>
            <el-table-column prop="evaluationTime" label="评论时间" align="center">
                <template slot-scope="scope">
                    <span class="info">{{scope.row.evaluationTime===null? '待评价' : scope.row.evaluationTime}}</span>
                </template>
            </el-table-column>
            <el-table-column label="评价状态" align="center" column-key="evaluationStatus"
                             :filters="[{ text: '已评价', value: '已评价' }, { text: '待评价', value: '待评价' },]"
                             :filter-method="filterHandler" :filter-multiple=false
                             filter-placement="bottom-end"
            >
                <template slot-scope="scope">
                    <div class="info"
                         v-if="scope.row.evaluationScore !== null&&scope.row.evaluationScore !== null && scope.row.evaluationScore!==null">
                        已评价
                    </div>
                    <el-button v-else
                               class="evaluation-Button"
                               @click="showEdit(scope.row)">
                        评价
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
      <!--  <el-pagination
                v-if="total>queryForm.pageSize"
                class="evaluation-page"
                background
                :current-page="queryForm.pageNo"
                :page-size="queryForm.pageSize"
                :layout="layout"
                :total="total"
                @current-change="handleCurrentChange"
        ></el-pagination>-->
        <edit ref="edit" @fetch-data="fetchData"></edit>
    </div>
</template>

<script>
    import {getCusEva} from "@/api/EvaluationList";
    import Edit from "./components/evaluationEdit.vue";

    export default {
        name: "CustomerEvaluation",
        components: {Edit},
        data() {
            return {
                listLoading: false,
                // orderDetail: this.$route.query.data || '',
                evaluationList: [],
                evaluations: [],
                queryForm: {
                    pageNo: 1,
                    pageSize: 8,
                },
                layout: "total,prev, pager, next, jumper",
                total: 0,
            };
        },
        watch: {
            // orderDetail() {
            //     console.log("dddd")
            //     this.orderDetail = this.$route.query.data || '';
            //     if (this.orderDetail){
            //         this.$refs["edit"].showEdit(this.orderDetail);
            //     }
            // },
            $route(to, from) {
                console.log("sdssdsdsd")
                if (this.$route.query.data){
                    this.$refs["edit"].showEdit(this.$route.query.data)
                    this.$router.push({ query:{} })

                }
                console.log("*******"+this.$route.query.data)

            }
        },
        created() {
        },
        mounted() {
            this.fetchData();
            console.log("*******"+this.$route.query.data)
            if (this.$route.query.data){
                this.$refs["edit"].showEdit(this.$route.query.data)
                this.$router.push({ query:{} })

            }
            // this.$route.query.data=null
        },
        methods: {
            // handleCurrentChange(val) {
            //     this.queryForm.pageNo = val;
            //     this.pagingInfo();
            // },
            showEdit(row) {
                console.log(row)
                this.$refs["edit"].showEdit(row);
            },
            // pagingInfo() {
            //     this.evaluations = this.evaluationList.filter(
            //         (item, index) =>
            //             index < this.queryForm.pageSize * this.queryForm.pageNo && index >= this.queryForm.pageSize * (this.queryForm.pageNo - 1)
            //     );
            // },
            filterHandler(value, row, column) {
                console.log("111111111111111111")
                if (value === '已评价') {
                    return row.evaluationScore !== null && row.evaluationContent !== null && row.evaluationTime !== null
                } else if (value === '待评价') {
                    return row.evaluationScore === null && row.evaluationContent === null && row.evaluationTime === null
                }


            },
            async fetchData() {
                this.total = 0;
                this.listLoading = true;
                const {data, totalCount} = await getCusEva();
                setTimeout(() => {
                    this.evaluationList = data.customerEvaluations;
                    this.total = totalCount;
                    this.queryForm.pageNo = 1;
                    // this.pagingInfo();
                    this.listLoading = false;
                }, 1000);
            }
        },
    };
</script>
<style>
    .customerEvaluation-container {

        float: left;
        width: 1000px;
        height: 700px;
        margin-top: 20px;
        border: 1px #e6e6e6 solid
    }

    .customerEvaluation-container .el-table td div {
        /*font-size: 14px;*/
        padding: 10px 10px;
        height: 60px;
    }
 .customerEvaluation-container .el-table::before {
      display: none;
    }

    .customerEvaluation-container .info {
        display: block;
        text-align: center;
        width: 100%;
        float: left;
        font-size: 13px;
        color: #666666;
        /*margin-left: 15px;*/
        height: 18px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;

    }

    .evaluation-Button {
        padding: 8px 8px;
        text-align: center;
        color: rgb(230, 82, 82);
        width: 50px;
        border-radius: 13px;
        font-size: 12px;
    }

    .evaluation-page {
        text-align: center;
        margin-top: 20px;
        margin-bottom: 30px;
    }
</style>

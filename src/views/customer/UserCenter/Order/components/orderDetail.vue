<template>
    <el-dialog
            title="订单详情"
            :visible.sync="dialogFormVisible"
            width="800px"
            @close="close"
    >
        <div style="border: 1px #e6e6e6 solid;margin-bottom: 30px;">
            <div class="Detail-title">
                <span class="Detail-title-info">订单编号</span>
                <span class="Detail-title-info">影片</span>
                <span class="Detail-title-info">时间</span>
                <span class="Detail-title-info">座位</span>
            </div>
            <div class="Detail-info">
                <div class="Detail-info-info">{{orderDetail.orderId}}</div>
                <div class="Detail-info-info">{{orderDetail.movieName}}</div>
                <div class="Detail-info-info" style="    color: #f03d37">{{orderDetail.orderTime}}</div>
                <div class="info-arrangementSeat">

                    <span class="info-arrangement">{{orderDetail.arrangementPlace}}号厅</span>
                     <div class="info-seat">
                         <span v-for="( item, index) in orderDetail.orderSeat" :key="index" style="display: inline-block;padding: 0 5px">{{item}}座</span>
                     </div>

        </div>
            </div>
        </div>
    </el-dialog>

</template>

<script>
    export default {
        name: "InformationEdit",
        props: {},
        data() {
            return {
                title: "",
                dialogFormVisible: false,
                orderDetail: ''

            };
        },
        created() {
        },
        methods: {
            showEdit(row) {
                console.log(row);
                this.orderDetail = row;
                this.dialogFormVisible = true;
            },
            close() {
                this.dialogFormVisible = false;
            },

        },
    };
</script>
<style>
    .Detail-title {
        background: #f5f7fa;
        color: #606266;
        font-size: 14px;
        font-weight: 400;
        height: 30px;
        line-height: 30px;
        padding: 1.5px 0;
        /*display: flex;*/
        /*justify-content: center;*/
        /*align-content: center;*/
        /*align-items: center;*/
    }

    .Detail-title-info {
        display: inline-block;
        width: 25%;
        text-align: center;
    }

    .Detail-info {
        height: 80px;
        /*line-height: 80px;*/
    }

.Detail-info-info{
    display: inline-block;
    width: 25%;
    line-height: 80px;
    text-align: center;
}

    .info-arrangementSeat {
        display: inline-block;
        width: 25%;
        text-align: center;
    }
    .info-arrangement {
        /*width: 100%;*/
        display: block;
        font-size: 14px;
        /*float: left;*/
        color: #424242;
        font-weight: 700;
        margin-right: 10px;
        margin-bottom: 5px;
    }
    .info-seat {
        font-size: 14px;
        color: #424242;

        margin-right: 10px;

    }
</style>
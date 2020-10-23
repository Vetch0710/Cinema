<template>
    <el-dialog
            title="评价"
            :visible.sync="dialogFormVisible"
            width="800px"
            @close="close"
    >
        <div style="border: 1px #e6e6e6 solid;margin:0 auto;width: 75%;">
            <div class="evaluationDetail evaInfo">
                <span class="evaluationDetail-title">订单信息：</span>
                <div class="evaluationDetail-title-info">
                    <span class="info-det">电影名称：{{orderDetail.movieName}}</span>
                    <span class="info-det">订单时间：{{orderDetail.orderTime}}</span>
                </div>
            </div>
            <div class="evaluationDetail evaScore">
                <span class="evaluationDetail-title">评分：</span>
                <div>
                    <el-rate v-model="score" class="evaluationDetail-title-evaScore"></el-rate>
                </div>
            </div>
            <div class="evaluationDetail evaContent">
                <span class="evaluationDetail-title" style="margin-top: 20px;">评价：</span>
                <div class="evaluationDetail-info-content">
                    <el-input
                            type="textarea"
                            :rows="10"
                            placeholder="请输入内容"
                            v-model="content">
                    </el-input>
                </div>
            </div>

        </div>
        <div slot="footer" class="dialog-footer">
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
        </div>
    </el-dialog>

</template>

<script>
    import { saveEvaluation } from "@/api/EvaluationList";

    export default {
        name: "evaluationEdit.vue",
        props: {},
        data() {
            return {
                title: "",
                dialogFormVisible: false,
                orderDetail: '',
                score: null,
                content: null,
                evaData:'',
            };
        },
        created() {
        },
        methods: {
            showEdit(row) {
                console.log(row);
                this.orderDetail = row.orderInfo;
                this.evaData=row;
                this.dialogFormVisible = true;
            },
            close() {
                this.dialogFormVisible = false;
            },
            async save() {
                console.log(this.score)
                console.log(this.content)
                if (this.score && this.content) {
                    const {msg} = await saveEvaluation({
                        orderId:this.orderDetail.orderId,
                        score: this.score,
                        content: this.content,
                        evaluationTime:new Date()
                    });
                    this.$baseMessage(msg, "success");
                    this.$emit("fetch-data");
                    this.close();
                }else {
                    this.$baseMessage("请检查信息是否填写完全", "error");
                }

            }

        },
    };
</script>
<style>
    .evaluationDetail {
        width: 90%;
        height: 100px;
        margin: 0 auto;
        border-bottom: 1px seashell solid;
        overflow: hidden;
    }


    .evaContent {
        height: 150px;
    }

    .evaluationDetail-title {
        display: inline-block;
        width: 25%;
        text-align: center;
        float: left;
        line-height: 100px;
        /*padding: 40px 0 0 0;*/
    }

    .evaluationDetail-title-info {
        display: inline-block;
        width: 75%;
        margin-top: 35px;
    }

    .evaluationDetail-title-evaScore {
        display: inline-block;
        width: 75%;
        margin-top: 40px;
    }

    .evaluationDetail-title-info .info-det {
        display: inline-block;
        width: 75%;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;

    }

    .evaluationDetail-info-content .el-textarea {
        /*height: 100px;*/
        width: 70%;
        float: left;
        margin-top: 20px;
    }

    .evaluationDetail-info-content .el-textarea__inner {
        height: 100px;
    }

    /*.evaluationDetail-info {*/
    /*    height: 80px;*/
    /*    !*line-height: 80px;*!*/
    /*}*/

    /*.evaluationDetail-info-info {*/
    /*    display: inline-block;*/
    /*    width: 25%;*/
    /*    !*line-height: 80px;*!*/
    /*    text-align: center;*/
    /*    !*float: left;*!*/
    /*    overflow: hidden;*/
    /*    padding: 20px 0;*/

    /*}*/

    /*.evaluationDetail-info-info .info-det {*/
    /*    width: 90%;*/
    /*    display: inline-block;*/
    /*    font-size: 12px;*/
    /*    color: #666666;*/
    /*    !*margin-left: 15px;*!*/
    /*    height: 18px;*/
    /*    padding: 0 10px;*/
    /*    overflow: hidden;*/
    /*    text-overflow: ellipsis;*/
    /*    white-space: nowrap;*/
    /*}*/
</style>
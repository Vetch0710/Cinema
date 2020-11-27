<template>
    <div class="salesStatistics-container">
        <div class="salesStatistics-chart">
            <el-card shadow="none">
                <div slot="header">
                    <span style="display: inline-block;margin-bottom: 20px  ;">营业额</span>
                    <div class="salesStatistics-chart-select">
                        <div style="float:right;" class="salesStatistics-chart-month">
                            <div class="salesStatistics-chart-select-time">
                                <span class="salesStatistics-chart-select-descTime">当前：{{ month}} 月</span>
                                <el-date-picker
                                        v-model="monthday"
                                        type="month"
                                        placeholder="选择月"
                                        format="yyyy 年 M 月"
                                        value-format="yyyy-M">
                                </el-date-picker>
                            </div>
                            <el-button @click="selectMonths(monthday)">提交</el-button>
                        </div>
                        <div style="float:left;">
                            <div class="salesStatistics-chart-select-time">
                                <span class="salesStatistics-chart-select-descTime">当前：{{ year}} 年</span>
                                <el-date-picker
                                        v-model="yearMonth"
                                        type="year"
                                        placeholder="选择年"
                                        format="yyyy年"
                                        value-format="yyyy">
                                </el-date-picker>
                            </div>

                            <el-button @click="submitSelect(yearMonth)">提交</el-button>
                        </div>

                    </div>
                </div>
                <div class="salesStatistics-chart-left" style="float:left;">
                    <byui-chart
                            :autoresize="true"
                            theme="byui-echarts-theme"
                            :options="fwl"
                    />
                </div>
                <div class="salesStatistics-chart-right" style="float:right;margin-right: 100px;">
                    <byui-chart
                            :autoresize="true"
                            theme="byui-echarts-theme"
                            :options="lwl"
                    />
                </div>

            </el-card>
        </div>
    </div>
</template>
<script>
    import "echarts";
    import "echarts-wordcloud";

    import byuiChart from "@/views/manager/plugins/echarts";
    import {getStatistics,getStatisticsYear} from "@/api/salesStatistics.js";
    import Vue from "vue";

    export default {
        name: "SalesStatistics",
        data() {
            return {
                year:'',
                yearMonth:'',
                monthday:'',
                month:'',
                startTime: '',
                endTime: '',
                fwl: {
                    tooltip: {
                        trigger: "axis",
                        axisPointer: {
                            type: "line",
                        },
                    },
                    grid: {
                        top: "4%",
                        left: "0",
                        right: "1%",
                        bottom: "0%",
                        containLabel: true,
                    },
                    xAxis: {
                        type: 'category',
                        data: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul','Aug','Sep','Oct','Nov','Dec']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: [],
                        type: 'bar',
                    }]
                },
                lwl: {
                    tooltip: {
                        trigger: "axis",
                        axisPointer: {
                            type: "line",
                        },
                    },
                    grid: {
                        top: "4%",
                        left: "0",
                        right: "1%",
                        bottom: "0%",
                        containLabel: true,
                    },
                    xAxis: {
                        type: 'category',
                        data: ['1', '2', '3', '4','5']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: [],
                        type: 'bar',
                            // showBackground: true,
                    }]
                    // xAxis: [
                    //     {
                    //         name: '天',
                    //         type: "category",
                    //         boundaryGap: false,
                    //         data: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 0, 3, 1, 2, 3, 1, 2, 3, 2, 1, 2, 1, 3, 3, 4, 5, 6, 7, 8, 9, 1, 0, 3, 1, 2, 3, 1, 2, 3, 2, 1, 2, 1, 3],
                    //         axisTick: {
                    //             alignWithLabel: true,
                    //         },
                    //     },
                    // ],
                    // yAxis: [
                    //     {
                    //         type: "value",
                    //         min: 50,
                    //         max: 500,
                    //     },
                    // ],
                    // series: [
                    //     {
                    //         name: "营业额",
                    //         type: "bar",
                    //         data: [10, 52, 20, 33, 39, 33, 22],
                    //         smooth: true,
                    //         areaStyle: {},
                    //     },
                    // ],
                },
            };
        },
        components: {byuiChart},
        created() {
        },
        mounted() {
            let date = new Date();
            // let month = date.getMonth() + 1;
            // let d = new Date(year, month, 0);
            // let day = [];
            // // console.log( d.getDate())
            // for (let i = 1; i <= d.getDate(); i++) {
            //     day.push(i);
            // }
            this.year=date.getFullYear();
            this.month=date.getMonth() + 1;
            this.submitSelect( date.getFullYear())
            console.log(date.getFullYear()+(date.getMonth() + 1))
            this.selectMonths((date.getFullYear()+"-"+(date.getMonth() + 1)))
            // console.log(day)
            // console.log(this.fwl.xAxis[0].data)
            // this.fwl.xAxis[0].data = day
            // this.selectMonths('当月');
        },
        methods: {

            async submitSelect(item) {
                // this.fwl.series[0].data=null;
                console.log(item)
                if (item) {
                    const result = await getStatisticsYear({
                        yearMonth: item,
                    })
                    console.log(result)
                    this.fwl.series[0].data = result
                    this.type='';
                }else{
                    Vue.prototype.$baseMessage("请检查数据是否齐全", "error");
                }
            },
            async selectMonths(item) {
                console.log(item)
                if (item) {
                    this.lwl.series[0].data=[];
                    const result = await getStatistics({
                        yearMonth: item,
                    })
                    console.log(result)
                    let arr=[];
                    let index=0;
                    let day=1;
                    let sum=0;
                    console.log(result.length)
                    while (index<=result.length){

                        if (day===8 || index===result.length){
                            console.log("day:"+day+"  sum:"+sum +"index "+index)
                            arr.push(sum);
                            sum= 0;
                            day=1;
                        }
                        sum+=result[index];
                        day++;
                        index++;
                    }
                    console.log(arr)
                    this.lwl.series[0].data = arr
                }else{
                    Vue.prototype.$baseMessage("请检查数据是否齐全", "error");
                }

            },
        },
    };
</script>
<style>
    .salesStatistics-chart-left .echarts {
        width: 700px;
        height: 300px;
    }
    .salesStatistics-chart-right .echarts {
        width: 250px;
        height: 300px;
    }

    .salesStatistics-chart-select:after {
        content: "";
        display: block;
        clear: both;
    }

    .salesStatistics-chart-select-time {
        font-size: 14px;
        float: left;
        margin: 0 10px;
    }

    .salesStatistics-chart-select-descTime {
        margin: 0 5px;
        font-size: 14px;

    }

    .salesStatistics-chart-month .el-button + .el-button {
        position: relative;
        margin-left: 0;
        left: -5px;
    }

    .salesStatistics-chart-now {
        width: 50px;
        height: 40px;
        display: inline-block;
        border: 1px #e6e6e6 solid;
        text-align: center;
        line-height: 40px;
        padding: 0 10px;
        font-size: 14px;
        cursor: pointer;
    }

    .salesStatistics-chart-now:hover {
        background: #f6f8f9;
    }

    .selectMonth {
        background: #d8e1e5;

    }
</style>
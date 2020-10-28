<template>
    <div class="salesStatistics-container">
        <div class="salesStatistics-chart">
            <el-card shadow="none">
                <div slot="header">
                    <span style="display: inline-block;margin-bottom: 20px  ;">营业额</span>
                    <div class="salesStatistics-chart-select">
                        <div style="float:left;" class="salesStatistics-chart-month">
                            <div>
                                <span class="salesStatistics-chart-now" style="margin-left: 20px;"
                                      :class="{selectMonth:type ==='当月'}" @click="selectMonths('当月')">当月</span>
                                <span class="salesStatistics-chart-now" :class="{selectMonth:type ==='上月'}"
                                      @click="selectMonths('上月')">上月</span>
                            </div>
                        </div>
                        <div style="float:right;">
                            <div class="salesStatistics-chart-select-time">
                                <span class="salesStatistics-chart-select-descTime">开始时间</span>
                                <el-date-picker
                                        v-model="startTime"
                                        type="date"
                                        placeholder="选择日期"
                                        format="yyyy 年 MM 月 dd 日"
                                        value-format="timestamp">
                                </el-date-picker>
                            </div>
                            <div class="salesStatistics-chart-select-time">
                                <span class="salesStatistics-chart-select-descTime">结束时间</span>
                                <el-date-picker
                                        v-model="endTime"
                                        type="date"
                                        placeholder="选择日期"
                                        format="yyyy 年 MM 月 dd 日"
                                        value-format="timestamp">
                                </el-date-picker>
                            </div>
                            <el-button @click="submitSelect">提交</el-button>
                        </div>

                    </div>
                </div>
                <byui-chart
                        :autoresize="true"
                        theme="byui-echarts-theme"
                        :options="fwl"
                />
            </el-card>
        </div>
    </div>
</template>
<script>
    import "echarts";
    import "echarts-wordcloud";

    import byuiChart from "@/views/manager/plugins/echarts";
    import {getStatistics} from "@/api/salesStatistics.js";
    import Vue from "vue";

    export default {
        name: "SalesStatistics",
        data() {
            return {
                type: '当月',
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
                    xAxis: [
                        {
                            name: '天',
                            type: "category",
                            boundaryGap: false,
                            data: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 0, 3, 1, 2, 3, 1, 2, 3, 2, 1, 2, 1, 3, 3, 4, 5, 6, 7, 8, 9, 1, 0, 3, 1, 2, 3, 1, 2, 3, 2, 1, 2, 1, 3],
                            axisTick: {
                                alignWithLabel: true,
                            },
                        },
                    ],
                    yAxis: [
                        {
                            type: "value",
                            min: 100,
                            max: 1000,
                        },
                    ],
                    series: [
                        {
                            name: "营业额",
                            type: "line",
                            data: [10, 52, 20, 33, 39, 33, 22],
                            smooth: true,
                            areaStyle: {},
                        },
                    ],
                },
            };
        },
        components: {byuiChart},
        created() {
        },
        mounted() {
            let date = new Date();
            let year = date.getFullYear();
            let month = date.getMonth() + 1;
            let d = new Date(year, month, 0);
            let day = [];
            // console.log( d.getDate())
            for (let i = 1; i <= d.getDate(); i++) {
                day.push(i);
            }
            // console.log(day)
            // console.log(this.fwl.xAxis[0].data)
            this.fwl.xAxis[0].data = day
            this.selectMonths('当月');
        },
        methods: {

            async submitSelect() {
                // this.fwl.series[0].data=null;
                console.log(this.startTime)
                console.log(this.endTime)
                if (this.startTime && this.endTime) {
                    const {data} = await getStatistics({
                        startTime: this.startTime,
                        endTime: this.endTime
                    })
                    console.log(data)
                    this.fwl.series[0].data = data
                }else{
                    Vue.prototype.$baseMessage("请检查数据是否齐全", "error");
                }
            },
            async selectMonths(type) {
                // this.fwl.series[0].data=null;
                let startTime = new Date();
                startTime.setHours(0);
                startTime.setSeconds(0);
                startTime.setMinutes(0);

                let endTime = new Date();
                endTime.setHours(0);
                endTime.setSeconds(0);
                endTime.setMinutes(0);
                this.type = type;
                let params;
                if (type === '当月') {
                    //获取每月有多少天
                    let date = new Date();
                    let year = date.getFullYear();
                    let month = date.getMonth() + 1;
                    let d = new Date(year, month, 0);

                    startTime.setDate(1);
                    endTime.setDate(d.getDate());

                    // console.log(startTime.getTime())
                    // console.log(endTime.getTime())
                    params = {
                        startTime: startTime.getTime(),
                        endTime: endTime.getTime()
                    }
                } else {
                    //获取上个月有多少天
                    let date = new Date();
                    let year = date.getFullYear();
                    let month = date.getMonth();
                    console.log(month)
                    let d = new Date(year, month, 0);

                    startTime.setDate(1);
                    startTime.setMonth(month - 1, 1);

                    endTime.setMonth(month - 1, 1);
                    endTime.setDate(d.getDate());

                    // console.log(startTime.valueOf())
                    // console.log(endTime.valueOf())
                    params = {
                        startTime: startTime.getTime(),
                        endTime: endTime.getTime()
                    }
                }
                const {data} = await getStatistics(params)
                console.log(data)

                this.fwl.series[0].data = data

            },
        },
    };
</script>
<style>
    .salesStatistics-chart .echarts {
        width: 100%;
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
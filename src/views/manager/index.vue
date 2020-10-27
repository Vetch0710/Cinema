<template>
  <div class="index-container">
    <div class="index-left">
      <ve-line :data="chartData" :settings="chartSettings"></ve-line>
    </div>
    <div class="calendar">
      <el-calendar v-model="selectDate"> </el-calendar>
    </div>
    <div class="time-line">
      <el-timeline>
        <el-timeline-item
          :timestamp="selectDate.getMonth() + 1 + '-' + selectDate.getDate()"
          placement="top"
        >
          <el-card>
            <h4>更新 Github 模板</h4>
            <p>王小虎 提交于 {{ selectDate }}</p>
          </el-card>
        </el-timeline-item>
        <el-timeline-item
          :timestamp="selectDate.getMonth() + 1 + '-' + selectDate.getDate()"
          placement="top"
        >
          <el-card>
            <h4>更新 Github 模板</h4>
            <p>王小虎 提交于 {{ selectDate }}</p>
          </el-card>
        </el-timeline-item>
        <el-timeline-item
          :timestamp="selectDate.getMonth() + 1 + '-' + selectDate.getDate()"
          placement="top"
        >
          <el-card>
            <h4>更新 Github 模板</h4>
            <p>王小虎 提交于 {{ selectDate }}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<script>
import VeLine from "v-charts/lib/line";
export default {
  name: "Index",
  components: {
    VeLine,
  },
  data() {
    return {
      selectDate: new Date(),
      dateArray: [],
      chartSettings: {
        axisSite: { right: ["下单率"] },
        yAxisType: ["KMB", "percent"],
        yAxisName: ["数值", "比率"],
      },
      chartData: {
        columns: ["日期", "访问用户", "下单用户", "下单率", "盈利"],
        rows: [
          {
            日期: "",
            访问用户: 1393,
            下单用户: 1093,
            下单率: 0.32,
            盈利: 3300,
          },
          {
            日期: "",
            访问用户: 3530,
            下单用户: 3230,
            下单率: 0.26,
            盈利: 5000,
          },
          {
            日期: "",
            访问用户: 2923,
            下单用户: 2623,
            下单率: 0.76,
            盈利: 3400,
          },
          {
            日期: "",
            访问用户: 1723,
            下单用户: 1423,
            下单率: 0.49,
            盈利: 3600,
          },
          {
            日期: "",
            访问用户: 3792,
            下单用户: 3492,
            下单率: 0.323,
            盈利: 4000,
          },
          {
            日期: "",
            访问用户: 4593,
            下单用户: 4293,
            下单率: 0.78,
            盈利: 2000,
          },
          {
            日期: "",
            访问用户: 4593,
            下单用户: 4293,
            下单率: 0.78,
            盈利: 1500,
          },
        ],
      },
    };
  },
  watch: {
    selectDate: "aa",
  },
  created() {
    this.getTime();
    this.aa();
  },
  beforeDestroy() {},
  mounted() {},
  methods: {
    getTime() {
      // new Date() new一个data对象，当前日期和时间
      // toLocaleString() 方法可根据本地时间把 Date 对象转换为字符串，并返回结果。
      this.nowtime = new Date().toLocaleString();
      console.log("nowtime: ", this.nowtime);
    },
    aa() {
      var myDate = new Date(this.selectDate); // 获取今天日期
      this.dateArray = [];
      console.log(myDate);
      myDate.setDate(myDate.getDate() - 6);
      var dateTemp;
      var flag = 1;
      for (var i = 0; i < 7; i++) {
        dateTemp = myDate.getMonth() + 1 + "-" + myDate.getDate();
        this.dateArray.push(dateTemp);
        myDate.setDate(myDate.getDate() + flag);
      }
      for (let i = 0; i < this.dateArray.length; i++) {
        this.chartData.rows[i].日期 = this.dateArray[i];
      }
    },
  },
};
</script>


<style>
.index-container .calendar {
  width: 48%;
  float: right;
  text-align: center;
  margin-right: 30px;
}
.index-container .index-left {
  width: 48%;
  height: 50px;
  float: left;
}
.el-calendar-table .el-calendar-day {
  height: 35px !important;
}

.v-charts-component-loading {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: hsla(0, 0%, 100%, 0.9);
}
.v-charts-mask-status {
  -webkit-filter: blur(1px);
  filter: blur(1px);
}
.v-charts-component-loading .circular {
  width: 42px;
  height: 42px;
  -webkit-animation: a 2s linear infinite;
  animation: a 2s linear infinite;
}
.v-charts-component-loading .path {
  -webkit-animation: b 1.5s ease-in-out infinite;
  animation: b 1.5s ease-in-out infinite;
  stroke-dasharray: 90, 150;
  stroke-dashoffset: 0;
  stroke-width: 2;
  stroke: #20a0ff;
  stroke-linecap: round;
}
@-webkit-keyframes a {
  to {
    -webkit-transform: rotate(1turn);
    transform: rotate(1turn);
  }
}
@keyframes a {
  to {
    -webkit-transform: rotate(1turn);
    transform: rotate(1turn);
  }
}
@-webkit-keyframes b {
  0% {
    stroke-dasharray: 1, 200;
    stroke-dashoffset: 0;
  }
  50% {
    stroke-dasharray: 90, 150;
    stroke-dashoffset: -40px;
  }
  to {
    stroke-dasharray: 90, 150;
    stroke-dashoffset: -120px;
  }
}
@keyframes b {
  0% {
    stroke-dasharray: 1, 200;
    stroke-dashoffset: 0;
  }
  50% {
    stroke-dasharray: 90, 150;
    stroke-dashoffset: -40px;
  }
  to {
    stroke-dasharray: 90, 150;
    stroke-dashoffset: -120px;
  }
}
.v-charts-data-empty {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: hsla(0, 0%, 100%, 0.9);
  color: #888;
  font-size: 14px;
}

.time-line {
  width: 95%;
  float: left;
  margin-left: 30px;
  margin-top: 50px;
}
</style>
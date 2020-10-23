<template>
  <div>
    <div class="orderSteps">
      <el-steps :active="ticketStep" align-center>
        <el-step title="选择影片场次"></el-step>
        <el-step title="选择座位"></el-step>
        <el-step title="等待付款"></el-step>
        <el-step title="订单完成"></el-step>
      </el-steps>
    </div>
  </div>
</template>
<script>
export default {
  name: "TicketSteps",
  data() {
    return { 
      ticketStep: 1,
       };
  },
  watch: {
    $route: "getStep",
  },
  created() {
    this.getStep()
  },
  mounted() {},
  methods: {
    getStep() {
      const paths = this.$route.path.split("/");
      const step = paths[paths.length-1];
      console.log(step);
      if(step == 'selectSession'){
        this.ticketStep = 1;
      }else if(step == 'seatSelection'){
        this.ticketStep = 2;
      }else if(step == 'orderGenerated'){
        this.ticketStep = 3;
      }else if(step == 'orderFinished'){
        this.ticketStep = 4;
      }
    },
  },
};
</script>
<style>
.orderSteps {
  margin-top: 60px;
}

.el-step__head.is-finish {
  color: #f03d37;
  border-color: #f03d37;
}

.el-step__title.is-finish {
  color: #f03d37;
}
.el-step__head.is-process {
  color: #ffd8d7;
  border-color: #ffd8d7;
}

.el-step__line {
  background-color: #ffd8d7;
}

.el-step__head.is-wait {
  color: #ffd8d7;
  border-color: #ffd8d7;
}

.el-step__title.is-process {
  color: #ffd8d7;
}

.el-step__title.is-wait {
  color: #ffd8d7;
}
</style>
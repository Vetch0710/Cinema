<template>
  <div style="width: 100%">
    <div class="evalustion-userinfo">
      <div class="evaluation-userimg">
        <el-avatar :size="50" :src="customer.customerPicture">
          <img
            src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"
          />
        </el-avatar>
      </div>
      <div class="evaluation-username" :title="evaluationTime">
        {{ customer.customerName }}<br />
        <div >
          <span style="float:left;font-size:16px;color:#999999">{{ date }}</span>
          <el-rate style="float:left" v-model="evaluationScore" disabled> </el-rate>
        </div>
      </div>
    </div>
    <br style="clear:both">
    <div class="evaluation-content">
        <span>{{ evaluationContent }}</span>
    </div>
    <br style="clear:both">
  </div>
</template>

<script>
export default {
  name: "Evaluation",
  data() {
    return {};
  },
  props: {
    customer: { type: Object, required: true },
    evaluationScore: { type: Number, required: true },
    evaluationContent: { type: String, required: true },
    evaluationTime: { type: String, required: true },
  },
  methods: {},
  computed: {
    date() {
      const str = this.evaluationTime.split(/\s+/)[0].split("-");
      return str[1] + "-" + str[2];
    },
  },
  components: {},
};
</script>

<style>
.evalustion-userinfo {
  display: inline;
}

.evaluation-username{
    line-height: 25px;
}

.evaluation-username,
.evaluation-userimg {
  float: left;
  margin-right: 20px;
}

.evaluation-username .el-rate__icon {
  margin-right: -5px;
  font-size: 18px;
}

.evaluation-username .el-icon-star-on:before {
  font-size: 24px;
}

.evaluation-content{
    margin-top:15px;
    padding-bottom: 30px;
    border-bottom: 1px solid #e5e5e5;
    color: #666;
    line-height: 26px;
    font-size: 14px;
    width: 660px;
}
</style>

<template>
  <div style="width: 100%">
    <div class="evalustion-userinfo">
      <div class="evaluation-userimg">
        <el-avatar :size="50" :src="'http://8.131.77.164:8089/CinemaData/img/'+customerPicture">
          <img
            src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"
          />
        </el-avatar>
      </div>
      <div class="evaluation-username" :title="timeTitle">
        {{ customerName }}<br />
        <div>
          <span style="float: left; font-size: 16px; color: #999999">{{
            date
          }}</span>
          <el-rate style="float: left" v-model="evaluationScore" disabled>
          </el-rate>
        </div>
      </div>
    </div>
    <br style="clear: both" />
    <div class="evaluation-content">
      <span>{{ evaluationContent }}</span>
    </div>
    <br style="clear: both" />
  </div>
</template>

<script>
import { parseTime, formatTime } from "@/utils/index";
export default {
  name: "Evaluation",
  data() {
    return {};
  },
  props: {
    customerName: { type: String, required: true },
    customerPicture: { type: String, required: true },
    evaluationScore: { type: Number, required: true },
    evaluationContent: { type: String, required: true },
    evaluationTime: { type: Number, required: true },
    // evaLoaded: { type: Boolean, required: true },
  },
  methods: {},
  computed: {
    date() {
      // const str = this.evaluationTime.split(/\s+/)[0].split("-");
      // return str[1] + "-" + str[2];
      return formatTime(this.evaluationTime);
    },
    timeTitle() {
      return parseTime(this.evaluationTime);
    },
  },
  components: {},
};
</script>

<style>
.evalustion-userinfo {
  display: inline;
}

.evaluation-username {
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

.evaluation-content {
  margin-top: 15px;
  padding-bottom: 30px;
  border-bottom: 1px solid #e5e5e5;
  color: #666;
  line-height: 26px;
  font-size: 14px;
  width: 660px;
  float: right;
}
</style>

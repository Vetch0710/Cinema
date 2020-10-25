import path from 'path';
<template>
  <div class="orderFinished">
    <el-image
      style="width: 200px; height: 200px"
      :src="require('@/views/customer/ticket/orderFinished/paySuccessful.png')"
      fit="cover"
    ></el-image>
    <h2>订单支付成功!</h2>
    <span @click="showOrder">查看订单</span>
    <span @click="returnIndex">返回首页</span>
  </div>
</template>

<script>
export default {
  name: "OrderFinished",
  data() {
    return {};
  },
  methods: {
    showOrder() {
      this.$router.push({
        path: "/customerOrder/totalOrder",
      });
    },
    returnIndex() {
      this.$router.push({
        path: "/",
      });
    },
  },
  components: {},
};
</script>

<style>
.orderFinished {
  margin-top: 60px;
  text-align: center;
  color: #ff2742;
  line-height: 40px;
}

.orderFinished span {
  margin-top: 60px;
  display: inline-block;
  padding: 2px 50px;
  margin-right: 30px;
  margin-left: 30px;
  color: #757575;
  border: 1px solid #bdbdbd;
  border-radius: 4px;
  cursor: pointer;
}
</style>

<template>
  <div
    class="el-input el-input-group el-input-group--append"
    style="width: 170px"
    inputmodel=""
  >
    <!---->
    <input
      class="el-input__inner"
      type="number"
      :placeholder="placeholder"
      v-model.number="inputModel"
    />
    <!----><!---->
    <div class="el-input-group__append">元</div>
    <!---->
  </div>
</template>
<script type="text/ecmascript-6">
export default {
  components: {},
  model: {
    prop: "inputModel",
    event: "notify",
  },
  props: {
    point: {
      default: 0,
    },
    max: Number,
    placeholder: String,
    inputNumber: null,
  },
  data() {
    return {
      lineData: {},
      formData: {},
      showDialog: false,
      inputModel: this.inputNumber,
    };
  },
  mounted() {},
  methods: {
    setParentModeVal() {
      this.$emit("notify", this.inputModel);
    },
  },
  watch: {
    inputModel: function (newVal, oldVal) {
      // 解决数字键盘可以输入输入多个小数点问题
      if (newVal === "" && oldVal.toString().indexOf(".") > 0) {
        this.inputModel = oldVal;
        this.setParentModeVal();
        return;
      }
      // 保留两位小数
      if (newVal) {
        newVal = newVal.toString();
        var pointIndex = newVal.indexOf(".");
        if (pointIndex > 0 && newVal.length - pointIndex > this.point + 1) {
          this.inputModel = oldVal;
          this.setParentModeVal();
          return;
        }
      }
      // 最大值
      if (this.max > 0 && newVal > this.max) {
        this.inputModel = oldVal;
        this.setParentModeVal();
        return;
      }
      if (newVal < 0) {
        this.inputModel = 0;
        this.setParentModeVal();
        return;
      }
      this.setParentModeVal();
    },
  },
};
</script>
 
<style>
.my-number-input {
  width: 180px;
  vertical-align: top;
  line-height: normal;
  display: inline-table;
  border-collapse: separate;
  border-spacing: 0;
  position: relative;
  font-size: 14px;
}
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none !important;
  margin: 0;
}
</style>
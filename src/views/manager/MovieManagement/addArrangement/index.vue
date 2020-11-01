<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogFormVisible"
    width="500px"
    @close="close"
  >
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="影片编号" prop="movieId">
        <el-input v-model="form.movieId" autocomplete="off" disabled></el-input>
      </el-form-item>
      <el-form-item label="片名" prop="movieName">
        <el-input
          v-model="form.movieName"
          autocomplete="off"
          disabled
        ></el-input>
      </el-form-item>
      <el-form-item label="影厅" prop="arrangementPlace">
        <el-input
          v-model.number="form.arrangementPlace"
          placeholder="请输入影厅号"
          autocomplete="off"
          @input="chageInput"
          style="width: 180px"
          ><template slot="append">号厅</template></el-input
        >
      </el-form-item>
      <el-form-item label="日期" prop="arrangementDate">
        <el-date-picker
          align="right"
          type="date"
          placeholder="选择日期"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          :picker-options="pickerOptions"
          v-model="form.arrangementDate"
          @change="chageDate"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="时间" prop="arrangementTime">
        <el-time-picker
          :picker-options="selectTimeFormat"
          placeholder="选择时间"
          format="HH:mm"
          value-format="HH:mm"
          v-model="form.arrangementTime"
          :disabled="(isInput || !isSelected) && title != '修改'"
        >
        </el-time-picker>
      </el-form-item>
      <el-form-item label="票价" prop="arrangementPrice">
        <MyNumberInput
          :point="1"
          :max="200"
          placeholder="请输入金额"
          v-model.number="form.arrangementPrice"
          :inputNumber="form.arrangementPrice"
          @setarrangementprcie="setarrangementprcie"
        ></MyNumberInput
        >&nbsp;&nbsp;&nbsp;<span style="color: red">0&lt;price&lt;200</span>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="save">确 定</el-button>
    </div>
  </el-dialog>
</template>
<script>
import { doEdit, addArrangement, getDisableTime } from "@/api/Arrangements";
import MyNumberInput from "@/components/Plugin/MyNumberInput";
export default {
  name: "AddArrange",
  data() {
    return {
      dialogFormVisible: false,
      title: "",
      form: {},
      selectTimeFormat: {
        selectableRange: [],
        format: "HH:mm",
      },
      isSelected: false,
      pickerOptions: {
        disabledDate(time) {
          const date = new Date(time);
          date.setDate(date.getDate() + 1);
          return date < new Date();
        },
      },
      rules: {
        arrangementPlace: [
          { required: true, type: "number", message: "请输入数字" },
        ],
        arrangementPrice: [
          { required: true, type: "number", message: "请输入数字" },
        ],
        arrangementTime: [{ required: true, message: "请选择日期" }],
        arrangementDate: [{ required: true, message: "请选择日期" }],
      },
    };
  },
  computed: {
    isInput: function () {
      return (
        this.form.arrangementPlace == null || this.form.arrangementPlace == ""
      );
    },
  },
  created() {},
  watch: {
    // "form.arrangementDate": "setDisableTime",
  },
  mounted() {},
  components: {
    MyNumberInput,
  },
  methods: {
    setarrangementprcie: function (data) {
      this.form.arrangementPrice = data;
    },

    showArrange(row) {
      this.title = "排场";
      const assresult = Object.assign({}, row);
      this.form.movieId = assresult.movieId;
      this.form.movieName = assresult.movieName;
      this.form.movieTime = parseInt(assresult.movieTime.replace("分钟", ""));
      this.dialogFormVisible = true;
    },
    addArrange(row) {
      this.title = "修改";
      this.form = Object.assign({}, row);
      const date = new Date(row.arrangementTime);
      // this.form.arrangementDate =
      //   date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      this.$set(
        this.form,
        "arrangementDate",
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()
      );
      this.form.arrangementTime = date.getHours() + ":" + date.getMinutes();
      console.log(this.form);
      this.dialogFormVisible = true;
    },

    save() {
      this.$refs["form"].validate(async (valid) => {
        if (valid) {
          console.log(this.form);
          this.form.arrangementTime = new Date(
            this.form.arrangementDate + " " + this.form.arrangementTime
          );
          let msgContent = "";
          console.log(this.title);
          if (this.title == "排场") {
            const msg = await addArrangement(this.form);
            if (msg == "success") {
              msgContent = "排场成功";
              this.$baseMessage(msgContent, msg);
            } else if (msg == "error") {
              msgContent = "排场失败,请重新尝试";
              this.$message.error(msgContent);
            }
          } else if (this.title == "修改") {
            const msg = await doEdit(this.form);
            if (msg == "success") {
              msgContent = "修改成功";
              this.$baseMessage(msgContent, msg);
            } else if (msg == "error") {
              msgContent = "修改失败,请重新尝试";
              this.$message.error(msgContent);
            }
          }
          this.$emit("fetch-data");
          this.close();
        } else {
          return false;
        }
      });
    },

    close() {
      this.$refs["form"].resetFields();
      this.form = this.$options.data().form;
      this.dialogFormVisible = false;
    },
    chageDate() {
      this.isSelected = true;
      // this.selectTimeFormat.selectableRange = [
      //   "09:30:00 - 12:00:00",
      //   "14:30:00 - 18:30:00",
      // ];
      this.setDisableTime();
      this.$delete(this.form, "arrangementTime");
    },
    chageInput() {
      this.$delete(this.form, "arrangementTime");
      this.setDisableTime();
    },
    async setDisableTime() {
      console.log("this.form.arrangementPlace=" + this.form.arrangementPlace);
      console.log("this.form.arrangementDate=" + this.form.arrangementPlace);
      if (
        this.form.arrangementPlace != null &&
        this.form.arrangementDate != null &&
        this.form.arrangementPlace != "" &&
        this.form.arrangementDate != ""
      ) {
        const msg = await getDisableTime(this.form);
        console.log(msg);
        this.selectTimeFormat.selectableRange = msg;
      }
      // this.selectTimeFormat.selectableRange = [
      //   "09:30:00 - 12:00:00",
      //   "14:30:00 - 18:30:00",
      // ];
    },
  },
};
</script>
<style>
.MovieManagement-container .el-form-item {
  margin: 8px 10px 0 0 !important;
}

.el-input__icon {
  line-height: 33px;
}
</style>
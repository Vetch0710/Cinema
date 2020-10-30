<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogFormVisible"
    width="500px"
    @close="close"
  >
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="当前为：">
        <el-input v-model="form.value" disabled></el-input>
      </el-form-item>
      <el-form-item v-if="title === '性别'" label="修改为：" prop="newValue">
        <el-radio v-model="form.newValue" label="男">男</el-radio>
        <el-radio v-model="form.newValue" label="女">女</el-radio>
      </el-form-item>
      <el-form-item v-else-if="title !== '手机号'" label="修改为：" prop="newValue">
        <el-input v-model="form.newValue" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item v-else label="修改为：" prop="phone">
        <el-input
          v-model="form.phone"
          autocomplete="off"
          maxlength="11"
        ></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="save">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import { doEdit } from "@/api/roleManagement";
  import { isPhone } from "@/utils/validate";

  export default {
    name: "RoleManagementEdit",
    props: {
      mid: {
        type: [Number, String],
        default: 9999,
      },
    },
    data() {
      const validatePhone = (rule, value, callback) => {
        if (!isPhone(value)) {
          callback(new Error("请输入正确的手机号"));
        } else {
          callback();
        }
      };
      return {
        form: {
          id: "",
          value: "",
          newValue: "",
          title: "",
          phone: "",
        },
        rules: {
          newValue: [
            { required: true, trigger: "blur", message: "请输入数据" },
          ],
          phone: [
            { required: true, trigger: "blur", message: "请输入手机号码" },
            { validator: validatePhone, trigger: "blur" },
          ],
        },
        title: "",
        dialogFormVisible: false,
      };
    },
    created() {},
    methods: {
      showEdit(row) {
        this.form.title = this.title = row.type;
        this.form.id = this.mid;
        this.form.value = row.value;
        this.dialogFormVisible = true;
      },
      close() {
        this.$refs["form"].resetFields();
        this.form = this.$options.data().form;
        this.dialogFormVisible = false;
      },
      save() {
        this.$refs["form"].validate(async (valid) => {
          if (valid) {
            const msg = await doEdit(this.form);

            if (msg === 'success') {
              this.$baseMessage("修改成功", "success");
            } else {
              this.$baseMessage(msg, "error");
            }

            this.$emit("fetch-data");
            this.close();
          } else {
            return false;
          }
        });
      },
    },
  };
</script>

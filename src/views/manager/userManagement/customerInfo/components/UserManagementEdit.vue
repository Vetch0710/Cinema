<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogFormVisible"
    width="500px"
    @close="close"
  >
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="用户编号">
        <el-input v-model.trim="form.customerId" autocomplete="off" disabled></el-input>
      </el-form-item>
      <el-form-item label="用户名" prop="customerName">
        <el-input v-model.trim="form.customerName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="customerPassword">
        <el-input
          v-model.trim="form.customerPassword"
          type="password"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="电话" prop="customerPhone">
        <el-input v-model.trim="form.customerPhone" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="save">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import { doEditCus } from "@/api/userManagement";
  import { isPhone } from "@/utils/validate";
  export default {
    name: "UserManagementEdit",
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
          customerId: "",
          customerName: "",
          customerPassword: "",
          customerPhone: "",
        },
        rules: {
          customerName: [
            { required: true, trigger: "blur", message: "请输入用户名" },
          ],
          customerPassword: [
            { required: true, trigger: "blur", message: "请输入密码" },
          ],
          customerPhone: [
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
        console.log(row)
        if (!row) {
          this.title = "添加";
        } else {
          this.title = "编辑";
          this.form = Object.assign({}, row);
        }
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
            const msg = await doEditCus(this.form);
              if (msg==='success'){
                  this.$baseMessage("修改成功", "success");
              }else {
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

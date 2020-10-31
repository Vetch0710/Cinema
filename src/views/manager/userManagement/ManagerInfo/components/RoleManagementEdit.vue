<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogFormVisible"
    width="500px"
    @close="close"
  >
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="工号" prop="managerId">
        <el-input v-model="form.managerId" autocomplete="off" disabled></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="managerName">
        <el-input v-model="form.managerName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="managerSex">
        <el-radio v-model="form.managerSex" label="男">男</el-radio>
        <el-radio v-model="form.managerSex" label="女">女</el-radio>
      </el-form-item>
      <el-form-item label="权限" prop="managerIsBoss" >
        <el-radio-group v-model="form.managerIsBoss">
          <el-radio label="管理员"></el-radio>
          <el-radio label="老板"></el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="save">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import { doEditMan } from "@/api/userManagement";

  export default {
    name: "RoleManagementEdit",
    props: {
      maxListNum: {
        type: [Number, String],
        default: 9999,
      },
    },
    data() {
      return {
        form: {
          id: "",
        },
        rules: {
          managerIsBoss: [
            { required: true, trigger: "blur", message: "请选择权限" },
          ],
          managerName: [{ required: true, trigger: "blur", message: "请输入姓名" }],
          managerSex: [{ required: true, trigger: "blur", message: "请选择性别" }],
        },
        title: "",
        dialogFormVisible: false,
      };
    },
    created() {},
    methods: {
      showEdit(row) {
        if (!row) {
          this.title = "添加";
          this.form.managerId = this.maxListNum;
        } else {
          this.title = "编辑";
          this.form = Object.assign({}, row);

          if (this.form.managerIsBoss === 0){
            this.form.managerIsBoss='管理员'
          }else {
            this.form.managerIsBoss='老板'
          }
          console.log(this.form)
          // if (this.form.permission === "manager") {
          //   this.form.permission = "管理员";
          // }
          // if (this.form.permission === "boss") {
          //   this.form.permission = "老板";
          // }
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
            if (this.form.managerIsBoss === "管理员") {
              this.form.managerIsBoss = 0;
            }
            if (this.form.managerIsBoss === "老板") {
              this.form.managerIsBoss = 1;
            }

            this.form.managerPassword='111111'

            console.log(this.form);
            const  msg  = await doEditMan(this.form);
            if (msg==='success'){
              this.$baseMessage("操作成功", "success");
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

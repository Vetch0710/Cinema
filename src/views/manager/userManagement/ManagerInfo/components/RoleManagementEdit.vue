<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogFormVisible"
    width="500px"
    @close="close"
  >
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="工号" prop="id">
        <el-input v-model="form.id" autocomplete="off" disabled></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="权限" prop="permission">
        <el-radio-group v-model="form.permission">
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
  import { doEdit } from "@/api/roleManagement";

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
          permission: [
            { required: true, trigger: "blur", message: "请选择权限" },
          ],
          name: [{ required: true, trigger: "blur", message: "请输入姓名" }],
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
          this.form.id = this.maxListNum;
        } else {
          this.title = "编辑";
          this.form = Object.assign({}, row);
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
            // if (this.form.permission === "管理员") {
            //   this.form.permission = "manager";
            // }
            // if (this.form.permission === "老板") {
            //   this.form.permission = "boss";
            // }
            console.log(this.form);
            const { msg } = await doEdit(this.form);
            this.$baseMessage(msg, "success");
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

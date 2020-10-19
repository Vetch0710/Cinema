<template>
  <div class="customerEvaluation-container">
    <el-form>
      <el-form-item label="图片">
        <el-upload
          class="avatar-uploader"
          name="file"
          :action="action"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img v-if="imageUrl" :src="imageUrl" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  // import VabUpload from "@/components/VabUpload";
  import { baseURL, tokenName } from "@/config/settings";
  import { setAvatar } from "@/api/user";
  export default {
    name: "CustomerEvaluation",
    components: {},
    data() {
      return {
        imageUrl: "",
        // action: "https://jsonplaceholder.typicode.com/posts/",
        action: baseURL + "/upload",
        headers: { "Content-Type": "multipart/form-data" },
      };
    },
    created() {},
    mounted() {},
    methods: {
      handleShow(data) {
        this.$refs["vabUpload"].handleShow(data);
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === "image/jpeg";
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error("上传头像图片只能是 JPG 格式!");
        }
        if (!isLt2M) {
          this.$message.error("上传头像图片大小不能超过 2MB!");
        }
        // let fd = new FormData(); //通过form数据格式来传
        // fd.append("file", file); //传文件
        // setAvatar(fd);
        return isJPG && isLt2M;
      },
    },
  };
</script>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409eff;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

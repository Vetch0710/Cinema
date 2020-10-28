<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogFormVisible"
    width="500px"
    @close="close"
  >
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="影片编号" prop="movieId" v-if="title == '编辑'">
        <el-input v-model="form.movieId" autocomplete="off" disabled></el-input>
      </el-form-item>
      <el-form-item label="片名" prop="movieName">
        <el-input v-model="form.movieName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item
        label="海报"
        prop="moviePicture"
        class="movie-picture-upload"
      >
        <el-upload
          class="avatar-uploader"
          action="https://jsonplaceholder.typicode.com/posts/"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :accept="imageFormats"
        >
          <img
            v-if="form.moviePicture"
            :src="form.moviePicture"
            class="avatar"
          />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="预告" prop="movieTrailer">
        <el-link
          type="info"
          :href="form.movieTrailer"
          target="_blank"
          v-if="form.movieTrailer"
          >播放预告</el-link
        >
        <el-upload
          class="upload-demo"
          ref="upload"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :multiple="false"
          :limit="1"
          :accept="videoFormats"
          :on-success="handleTrailerSuccess"
          :file-list="fileList"
          :auto-upload="false"
          :on-exceed="handleExceed"
        >
          <el-button slot="trigger" size="small" type="primary"
            >选取文件</el-button
          >
          <el-button
            style="margin-left: 10px"
            size="small"
            type="success"
            @click="submitUpload"
            >上传到服务器</el-button
          >
          <div slot="tip" class="el-upload__tip">
            只能上传视频文件，且不超过30mb
          </div>
        </el-upload>
      </el-form-item>
      <el-form-item label="导演" prop="movieDirector">
        <el-input v-model="form.movieDirector" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="演员" prop="movieActor">
        <el-input v-model="form.movieActor" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="时长" prop="movieTime">
        <el-input v-model.number="form.movieTime" autocomplete="off">
          <template slot="append">分钟</template>
        </el-input>
      </el-form-item>
      <el-form-item label="上映日期" prop="movieReleaseTime">
        <el-date-picker
          v-model="form.movieReleaseTime"
          align="right"
          type="datetime"
          format="yyyy-MM-dd"
          placeholder="选择日期"
          value-format="yyyy-MM-dd"
          :picker-options="pickerOptions"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="类型" prop="movieType">
        <el-select v-model="form.movieType" multiple placeholder="请选择类型">
          <el-option
            v-for="item in types"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="电影简介" prop="movieDes">
        <el-input
          type="textarea"
          placeholder="请输入电影简介"
          v-model="form.movieDes"
          maxlength="500"
          show-word-limit
          :clearable="true"
          :autosize="true"
        >
        </el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="save">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { doEdit, addMovie } from "@/api/MovieManagement";

export default {
  name: "MovieManagementEdit",
  data() {
    return {
      imageFormats:
        ".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF",
      videoFormats:
        ".mp4,.mov,.avi,.wmv,.3gp,.mkv,.flv,.MP4,.MOV,.AVI,.WMV,.3GP,.MKV,.FLV",
      imageUrl: "",
      fileList: [],
      form: {
        movieTrailer: null,
      },
      rules: {
        movieName: [{ required: true, message: "请输入片名" }],
        movieDirector: [{ required: true, message: "请输入导演名" }],
        movieActor: [{ required: true, message: "请输入演员名" }],
        movieType: [{ required: true, message: "请选择类型" }],
        movieTime: [{ required: true, type: "number", message: "请输入数字" }],
        moviePicture: [{ required: true, message: "请上传海报" }],
        movieReleaseTime: [{ required: true, message: "请选择日期" }],
        movieDes: [{ required: true, message: "请输入影片简介" }],
        movieTrailer: [{ required: true, message: "请上传预告片" }],
      },
      types: [
        {
          value: "剧情",
          label: "剧情",
        },
        {
          value: "动作",
          label: "动作",
        },
        {
          value: "奇幻",
          label: "奇幻",
        },
        {
          value: "喜剧",
          label: "喜剧",
        },
        {
          value: "恐怖",
          label: "恐怖",
        },
        {
          value: "冒险",
          label: "冒险",
        },
        {
          value: "爱情",
          label: "爱情",
        },
        {
          value: "警匪",
          label: "警匪",
        },
        {
          value: "科幻",
          label: "科幻",
        },
        {
          value: "战争",
          label: "战争",
        },
        {
          value: "灾难",
          label: "灾难",
        },
        {
          value: "悬疑",
          label: "悬疑",
        },
        {
          value: "动画",
          label: "动画",
        },
        {
          value: "温情",
          label: "温情",
        },
      ],
      title: "",
      dialogFormVisible: false,
      pickerOptions: {
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            },
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            },
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            },
          },
        ],
      },
    };
  },
  created() {},
  methods: {
    handleAvatarSuccess(res, file) {
      this.form.moviePicture = URL.createObjectURL(file.raw);
    },
    handleTrailerSuccess(res, file) {
      this.form.movieTrailer = URL.createObjectURL(file.raw);
      console.log(this.form.movieTrailer);
    },
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 4;
      if (!isLt2M) {
        this.$message.error("上传海报大小不能超过 4MB!");
      }
      return isLt2M;
    },
    showEdit(row) {
      if (!row) {
        this.title = "添加";
      } else {
        this.title = "编辑";
        this.form = Object.assign({}, row);
        const date = new Date(this.form.movieReleaseTime);
        this.form.movieTime = parseInt(this.form.movieTime.replace("分钟", ""));
        this.form.movieReleaseTime =
          date.getFullYear() +
          "-" +
          (date.getMonth() + 1) +
          "-" +
          date.getDate();
      }
      this.dialogFormVisible = true;
    },
    submitUpload() {
      this.$refs.upload.submit();
    },
    close() {
      this.$refs["form"].resetFields();
      this.form = this.$options.data().form;
      this.form.moviePicture = "";
      this.$refs.upload.clearFiles();
      this.dialogFormVisible = false;
    },
    save() {
      this.$refs["form"].validate(async (valid) => {
        if (valid) {
          this.form.movieType = this.form.movieType.join("/");
          if (this.title == "添加") {
            await addMovie(this.form);
          } else if (this.title == "编辑") {
            await doEdit(this.form);
          }
          this.$emit("fetch-data");
          this.close();
        } else {
          return false;
        }
      });
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.form.movieTrailer = null;
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
  },
};
</script>

<style>
.el-form-item__error {
  color: #f56c6c;
  font-size: 12px;
  line-height: 1;
  padding-top: 4px;
  position: relative;
  top: 100%;
  left: 0;
}
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
  width: 106px;
  height: 145px;
  display: block;
}
.el-textarea .el-input__count {
  bottom: unset !important;
}
</style>
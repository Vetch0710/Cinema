<template>
  <div class="Information-container">
    <div class="Information-main">
      <div class="Information-title">个人信息</div>
      <ul v-for="(item, index) in list" id="Info-list" :key="item.type">
        <li v-if="index !== 0">
          <span class="Info-type">{{ item.type }}</span>
          <span v-if="item.type === '密码'" class="Info-value">******</span>
          <span v-else-if="item.type !== '头像'" class="Info-value">
            {{ item.value }}
          </span>

          <span v-else class="Info-value">
            <el-image
              style="
                width: 60px;
                height: 60px;
                vertical-align: middle;
                display: inline-block;
              "
              :src="url"
              :fit="'contain'"
            ></el-image>
          </span>
          <el-button class="Info-edit"  @click="handleEdit(index)" v-if="item.type !== '头像'">
            修改
          </el-button>
          <el-upload
                    v-else
                  style="display: inline-block;width: 70px;"
                  name="file"
                  :action="action"
                  :show-file-list="false"
                  :on-success="handleAvatarSuccess"
                  :before-upload="beforeAvatarUpload"
          >
             <el-button class="Info-edit"   >
            上传图片
          </el-button>
<!--              <img :src="url" alt="">-->

          </el-upload>

        </li>
      </ul>
      <edit ref="edit" :list="list" @fetch-data="fetchData"></edit>
    </div>
  </div>
</template>
<script>
  import { getPersonalInfo } from "@/api/user";
  import Edit from "./components/InformationEdit";
  import { baseURL, tokenName } from "@/config/settings";
  import { mapGetters } from "vuex";

  export default {
    name: "Information",
    components: { Edit },
    computed: {
      ...mapGetters({
        accessToken: "user/accessToken",
      }),
    },
    data() {
      return {
        list: null,
        listLoading: true,
        layout: "total, sizes, prev, pager, next, jumper",
        elementLoadingText: "正在加载...",
        url: "",
        baseU: 'http://39.97.217.243:8089/images/',
        action: baseURL + "/upload",
      };
    },
    created() {
      this.fetchData();
    },
    methods: {
      handleEdit(row) {
        this.$refs["edit"].showEdit(row);
      },
      handleAvatarSuccess(res, file) {
          console.log(res)
          console.log(file)
        // this.url = URL.createObjectURL(file.raw);
        this.url = this.baseU+res;
        console.log(this.url)
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === "image/jpeg" || file.type === "image/png";
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error("上传头像图片只能是 JPG或PNG 格式!");
        }
        if (!isLt2M) {
          this.$message.error("上传头像图片大小不能超过 2MB!");
        }

        return isJPG && isLt2M;
      },
      async fetchData() {
        this.listLoading = true;
        const { data } = await getPersonalInfo({'this.accessToken':this.accessToken});
        console.log("data===="+data)
        console.log(data[0].type);
        this.list = data;
        this.url = data[1].value;
        setTimeout(() => {
          this.listLoading = false;
        }, 300);
      },
    },
  };
</script>
<style>
  .Information-container {
    float: left;
    width: 1000px;
    height: 500px;
    margin-top: 20px;
    /*background-color: #f8f8f8;*/
    background-color: #ffffff;
    /*border: 1px saddlebrown solid;*/
    font-family: "-apple-system", "BlinkMacSystemFont", "Roboto",
      "Helvetica Neue", "MIcrosoft YaHei", sans-serif !important;
    border: 1px #e6e6e6 solid;
    /*box-shadow:inset 1px 1px 2px gray ;*/
  }
  .Information-main {
    margin: 30px 0 0 30px;
  }
  .Information-title {
    margin-bottom: 20px;
    font-size: 16px;
  }
  #Info-list {
    list-style: none;
    padding: 0 30px;
    margin: 0;
  }
  #Info-list li {
    width: 900px;
    height: 70px;
    line-height: 70px;
    border-top: 1px #dfdfdf solid;
  }
  .Info-type {
    display: inline-block;
    width: 100px;
    height: 30px;
    line-height: 30px;
    margin-right: 20px;
    border-right: 1px #dfdfdf solid;
    text-align: center;
  }
  .Info-value {
    width: 650px;
    display: inline-block;
  }
  .Info-edit {
    position: relative;
    width: 100px;
    right: 0;
      text-align: center;
border-radius: 20px 20px;
  }
</style>

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
          <el-button class="Info-edit" round @click="handleEdit(index)">
            修改
          </el-button>
        </li>
      </ul>
      <edit ref="edit" :list="list" @fetch-data="fetchData"></edit>
    </div>
  </div>
</template>
<script>
  import { getPersonalInfo } from "@/api/user";
  import Edit from "./components/InformationEdit";

  export default {
    name: "Information",
    components: { Edit },
    data() {
      return {
        list: null,
        listLoading: true,
        layout: "total, sizes, prev, pager, next, jumper",
        elementLoadingText: "正在加载...",
        url: "",
      };
    },
    created() {
      this.fetchData();
    },
    methods: {
      handleEdit(row) {
        this.$refs["edit"].showEdit(row);
      },
      async fetchData() {
        this.listLoading = true;
        const { data } = await getPersonalInfo();
        console.log(data[0].value);
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
    width: 70px;
    right: 0;
  }
</style>

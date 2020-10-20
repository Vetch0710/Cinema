<template>
  <div class="PersonalInfo-container">
    <div class="PersonalInfo-table">
    <el-table
            style="width: 85%;text-align: center;margin: 0 auto;font-size: 14px !important; border: 1px #e6e6e6 solid;"

            v-loading="listLoading"
      :data="list"
      :element-loading-text="elementLoadingText"
            :header-cell-style="{background:'#f5f7fa',color:'#606266','font-size': '14px','font-weight': '400','padding': '7.5px 0',
    'height': '30px'}"
    >
      <el-table-column prop="type"  align="center" label="个人信息"></el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="value"
      ></el-table-column>
      <el-table-column show-overflow-tooltip label="操作" style="text-align: left" >
        <template #default="{ row }">
<!--          <div></div>-->
          <el-button
            v-if="row.type !== '工号' && row.type !== '职位'"
            type="text"
            style="font-size: 14px"
            @click="handleEdit(row)"
          >
            修改
          </el-button>
        <div v-else style="height: 40px;"></div>
        </template>
      </el-table-column>
    </el-table>
    </div>
    <edit ref="edit" :mid="id" @fetch-data="fetchData"></edit>
  </div>
</template>
<script>
  import { getInfo } from "@/api/userManagement";
  import Edit from "./components/PersonalInfoEdit";

  export default {
    name: "PersonalInfo",
    components: { Edit },
    data() {
      return {
        list: null,
        listLoading: true,
        layout: "total, sizes, prev, pager, next, jumper",
        total: 0,
        selectRows: "",
        elementLoadingText: "正在加载...",
        id: "",
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
        const { data } = await getInfo();
        console.log(data[0].value);
        this.list = data;
        this.id = data[0].value;
        setTimeout(() => {
          this.listLoading = false;
        }, 300);
      },
    },
  };
</script>
<style>
  .PersonalInfo-table{
    margin-top: 30px;
  }
  .PersonalInfo-table .el-table td {
    font-size: 14px;
    padding: 7.5px 0;
    height: 30px;
  }
</style>
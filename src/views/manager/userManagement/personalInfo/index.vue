<template>
  <div class="PersonalInfo-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      :element-loading-text="elementLoadingText"
    >
      <el-table-column prop="type" width="200" align="center"></el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="value"
        width="700"
      ></el-table-column>
      <el-table-column show-overflow-tooltip label="操作" width="200">
        <template #default="{ row }">
          <el-button
            v-if="row.type !== '工号' && row.type !== '职位'"
            type="text"
            style="font-size: 14px"
            @click="handleEdit(row)"
          >
            修改
          </el-button>
        </template>
      </el-table-column>
    </el-table>
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

<template>
  <div class="EvaluationManagement-container">
    <div class="EvaluationManagement-do">
      <div class="EvaluationManagement-do-right">
        <el-form :inline="true" :model="queryForm" @submit.native.prevent>
          <el-form-item>
            <el-input
              v-model.trim="queryForm.permission"
              placeholder="请输入查询内容"
              clearable
              ><el-select
                v-model="queryForm.selectType"
                slot="prepend"
                placeholder="查询条件"
                style="width: 100px"
              >
                <el-option label="片名" value="movieName"></el-option>
                <el-option label="影片编号" value="movieId"></el-option>
                <el-option label="评价编号" value="evaluationId"></el-option>
                <el-option
                  label="用户编号"
                  value="customerId"
                ></el-option> </el-select
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-search" type="primary" @click="queryData">
              查询
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="EvaluationManagement-table">
      <el-table
        style="width: 95%; text-align: center; margin: 0 auto"
        v-loading="listLoading"
        :data="list"
        :element-loading-text="elementLoadingText"
        :header-cell-style="{
          background: '#f5f7fa',
          color: '#606266',
          'font-size': '14px',
          'font-weight': '400',
          padding: '7.5px 0',
          height: '30px',
        }"
      >
        <el-table-column
          show-overflow-tooltip
          prop="evaluationId"
          label="评价编号"
        ></el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="movieName"
          label="片名"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="customerName"
          label="用户名"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="evaluationScore"
          label="评分"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="evaluationTime"
          label="评价时间"
          :formatter="formatTime"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="evaluationContent"
          label="评价内容"
        ></el-table-column>
      </el-table>
    </div>
    <el-pagination
      style="text-align: center; margin: 20px 0"
      background
      :current-page="queryForm.pageNo"
      :page-size="queryForm.pageSize"
      :layout="layout"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    ></el-pagination>
  </div>
</template>
<script>
import { getList } from "@/api/EvaluationList";
import { log } from "util";
import path from "path";

export default {
  name: "EvaluationManagement",
  data() {
    return {
      list: null,
      maxNum: "",
      listLoading: true,
      layout: "total, sizes, prev, pager, next, jumper",
      total: 0,
      selectRows: "",
      elementLoadingText: "正在加载...",
      queryForm: {
        pageNo: 1,
        pageSize: 10,
        title: "",
      },
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    // setSelectRows(val) {
    //   this.selectRows = val;
    // },

    formatTime(row, column) {
      const date = new Date(row.evaluationTime);
      return (
        date.getFullYear() +
        "-" +
        (date.getMonth() + 1) +
        "-" +
        date.getDate() +
        " " +
        date.getHours() +
        ":" +
        date.getMinutes() +
        ":" +
        date.getSeconds()
      );
    },

    handleSizeChange(val) {
      this.queryForm.pageSize = val;
      this.fetchData();
    },

    handleCurrentChange(val) {
      this.queryForm.pageNo = val;
      this.fetchData();
    },

    queryData() {
      this.queryForm.pageNo = 1;
      this.fetchData();
    },

    async fetchData() {
      this.listLoading = true;
      const { data, total } = await getList(this.queryForm);
      this.list = data;
      // console.log(typeof this.list);
      // console.log(parseInt(this.list[this.list.length - 1].id) + 1);
      this.total = total;
      setTimeout(() => {
        this.listLoading = false;
      }, 300);
    },
  },
};
</script>
<style>
/*.EvaluationManagement-container{*/
/*    overflow: auto;*/
/*}*/
.EvaluationManagement-container .el-button {
  font-size: 13px;
  height: 32px;
  line-height: 1;
  text-align: center;
  padding: 9px 10px;
}

.EvaluationManagement-container .el-input__inner {
  height: 32px;
  line-height: 32px;
  font-size: 13px;
  margin: 0;
}

.el-form-item__content {
  line-height: 33px !important;
}

.EvaluationManagement-container .el-form-item {
  margin: 0 10px 0 0;
}

.EvaluationManagement-table .el-table td {
  font-size: 14px;
  padding: 7.5px 0;
  height: 25px;
}

.EvaluationManagement-container .EvaluationManagement-do {
  width: 95%;
  margin: 20px 2.5% 10px 2.5%;
}

.EvaluationManagement-container .EvaluationManagement-do-right {
  height: 40px;
  display: inline-block;
  float: right;
}

.EvaluationManagement-container .EvaluationManagement-do-left {
  height: 40px;
  width: 50%;
  padding-left: 10px;
  display: inline-block;
}
.EvaluationManagement-container .cell {
  text-align: center;
}
.el-form-item__content {
  line-height: 32px;
  position: relative;
  font-size: 14px;
}
</style>

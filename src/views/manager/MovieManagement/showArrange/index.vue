<template>
  <div class="showArrange-container">
    <div class="showArrange-do">
      <div class="showArrange-do-left">
        <el-button icon="el-icon-plus" type="primary" @click="handleEdit">
          添加
        </el-button>
        <el-button icon="el-icon-delete" type="danger" @click="handleDelete">
          批量删除
        </el-button>
      </div>
      <div class="showArrange-do-right">
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
                <el-option label="场次编号" value="arrangementId"></el-option>
                <el-option label="影厅" value="arrangementPlace"></el-option>
                <el-option
                  label="日期"
                  value="arrangementDate"
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
    <div class="showArrange-table">
      <el-table
        style="width: 95%; text-align: center; margin: 0 auto"
        v-loading="listLoading"
        :data="list"
        :element-loading-text="elementLoadingText"
        @selection-change="setSelectRows"
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
          type="selection"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="arrangementId"
          label="场次编号"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="movieId"
          label="影片编号"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="movieName"
          label="片名"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="arrangementPlace"
          label="影厅"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="arrangementDate"
          :formatter="formatArrangeDate"
          label="场次日期"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="arrangementTime"
          :formatter="formatArrangeTime"
          label="场次时间"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="arrangementPrice"
          label="票价"
        ></el-table-column>
        <el-table-column show-overflow-tooltip label="操作" width="200">
          <template #default="{ row }">
            <el-button type="text" @click="handleEdit(row)">编辑</el-button>
            <el-button type="text" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
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
    <arrange ref="arrange" @fetch-data="fetchData"></arrange>
  </div>
</template>
<script>
import { doDelete, doEdit } from "@/api/MovieManagement";
import { getList } from "@/api/Arrangements";
import Arrange from "../addArrangement";
import { log } from "util";
import path from "path";

export default {
  name: "showArrange",
  components: { Arrange },
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
    setSelectRows(val) {
      this.selectRows = val;
    },

    formatArrangeDate(row, column) {
      const date = new Date(row.arrangementTime);
      return (
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()
      );
    },
    formatArrangeTime(row, column) {
      const date = new Date(row.arrangementTime);
      let hours = date.getHours();
      let minutes = date.getMinutes();
      if (hours < 10) {
        hours = "0" + hours;
      }
      if (minutes < 10) {
        minutes = "0" + minutes;
      }
      return hours + ":" + minutes;
    },

    async handleEdit(row) {
      if (row.arrangementId) {
        this.$refs["arrange"].addArrange(row);
      } else {
        this.$router.push({
          path: "/movieManagement/index",
        });
      }
    },

    handleDelete(row) {
      if (row.arrangementId) {
        this.$baseConfirm("你确定要删除当前项吗", null, async () => {
          const { msg } = await doDelete({ arrangementId: row.arrangementId });
          this.$baseMessage(msg, "success");
          this.fetchData();
        });
      } else {
        if (this.selectRows.length > 0) {
          const arrangementIds = this.selectRows.map(
            (item) => item.arrangementId
          );
          this.$baseConfirm("你确定要删除选中项吗", null, async () => {
            const { msg } = await doDelete({ arrangementIds });
            this.$baseMessage(msg, "success");
            this.fetchData();
          });
        } else {
          this.$baseMessage("未选中任何行", "error");
          return false;
        }
      }
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
      this.total = total;
      this.listLoading = false;
    },
  },
};
</script>
<style>
/*.showArrange-container{*/
/*    overflow: auto;*/
/*}*/
.showArrange-container .el-button {
  font-size: 13px;
  height: 32px;
  line-height: 1;
  text-align: center;
  padding: 9px 10px;
}

.showArrange-container .el-input__inner {
  height: 32px;
  line-height: 32px;
  font-size: 13px;
  margin: 0;
}

.el-form-item__content {
  line-height: 33px !important;
}

.showArrange-container .el-form-item {
  margin: 0 10px 0 0;
}

.showArrange-table .el-table td {
  font-size: 14px;
  padding: 7.5px 0;
  height: 25px;
}

.showArrange-container .showArrange-do {
  width: 95%;
  margin: 20px 2.5% 10px 2.5%;
}

.showArrange-container .showArrange-do-right {
  height: 40px;
  display: inline-block;
  float: right;
}

.showArrange-container .showArrange-do-left {
  height: 40px;
  width: 50%;
  padding-left: 10px;
  display: inline-block;
}
.showArrange-container .cell {
  text-align: center;
}
.el-form-item__content {
  line-height: 32px;
  position: relative;
  font-size: 14px;
}
</style>

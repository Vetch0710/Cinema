<template>
  <div class="MovieManagement-container">
    <div class="MovieManagement-do">
      <div class="MovieManagement-do-left">
        <el-button icon="el-icon-plus" type="primary" @click="handleEdit">
          添加
        </el-button>
        <el-button icon="el-icon-delete" type="danger" @click="handleDelete">
          批量删除
        </el-button>
      </div>
      <div class="MovieManagement-do-right">
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
                <el-option
                  label="影片编号"
                  value="movieId"
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
    <div class="MovieManagement-table">
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
          prop="movieId"
          label="影片编号"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="movieName"
          label="片名"
        ></el-table-column>
        <el-table-column show-overflow-tooltip prop="moviePicture" label="海报">
          <template #default="{ row }">
            <el-image
              style="width: 53px; height: 75px"
              :src="row.moviePicture"
              :preview-src-list="imageList"
            ></el-image>
          </template>
        </el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="movieTrailer"
          label="预告片"
        >
          <template #default="{ row }">
            <el-link type="info" :href="row.movieTrailer" target="_blank"
              >播放预告</el-link
            >
          </template>
        </el-table-column>

        <el-table-column
          show-overflow-tooltip
          prop="movieType"
          :formatter="formatType"
          label="类型"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="movieDirector"
          label="导演"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="movieActor"
          label="演员"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="movieTime"
          label="时长"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="movieRealeseTime"
          :formatter="formatTime"
          label="上映日期"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="wantsNum"
          label="想看人数"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="movieScore"
          label="评分"
        ></el-table-column>
        <el-table-column
          show-overflow-tooltip
          prop="movieDes"
          label="电影简介"
        ></el-table-column>
        <el-table-column show-overflow-tooltip label="操作" width="200">
          <template #default="{ row }">
            <el-button type="text" @click="handleEdit(row)">编辑</el-button>
            <el-button type="text" @click="addSession(row)">排场</el-button>
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
    <edit ref="edit" :max-list-num="maxNum" @fetch-data="fetchData"></edit>
    <arrange ref="arrange"></arrange>
  </div>
</template>

<script>
import {
  getMovieList,
  doDelete,
  doDeletes,
  doEdit,
} from "@/api/MovieManagement";
import Edit from "./compoents/MovieManagementEdit";
import Arrange from "./addArrangement";
import { log } from "util";

export default {
  name: "MovieList",
  components: { Edit, Arrange },
  data() {
    return {
      list: null,
      maxNum: "",
      listLoading: true,
      layout: "total, sizes, prev, pager, next, jumper",
      total: 0,
      selectRows: "",
      elementLoadingText: "正在加载...",
      imageList: [],
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

    async handleEdit(row) {
      if (row.movieId) {
        this.$refs["edit"].showEdit(row);
      } else {
        this.$refs["edit"].showEdit();
      }
    },

    async addSession(row) {
      this.$refs["arrange"].showArrange(row);
    },

    formatType(row, column) {
      return row.movieType.join("/");
    },
    formatTime(row, column) {
      const date = new Date(row.movieReleaseTime);
      return (
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()
      );
    },

    handleDelete(row) {
      if (row.movieId >= 0) {
        this.$baseConfirm("你确定要删除当前项吗", null, async () => {
          const { msg } = await doDelete({ movieId: row.movieId });
          let msgContent = "";
          if (msg == "success") {
            msgContent = "删除成功";
            this.$baseMessage(msgContent, msg);
          } else if (msg == "error") {
            msgContent = "删除失败,请重新尝试";
            this.$message.error(msgContent);
          }

          this.fetchData();
        });
      } else {
        if (this.selectRows.length > 0) {
          const movieIds = this.selectRows.map((item) => item.movieId);
          this.$baseConfirm("你确定要删除选中项吗", null, async () => {
            const  msg  = await doDeletes({ movieIds });
            console.log(msg);
            let msgContent = "";
            if (msg == "success") {
              msgContent = "删除成功";
              this.$baseMessage(msgContent, msg);
            } else if (msg == "error") {
              msgContent = "删除失败,请重新尝试";
              this.$message.error(msgContent);
            }
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
      const { data, total } = await getMovieList(this.queryForm);
      // const result = await getMovieList(this.queryForm).then((res) => {
      //   return res;
      // });
      this.list = data;
      this.total = total;
      //  this.total = 14;
      const imageList = [];
      data.forEach((item, index) => {
        imageList.push(item.moviePicture);
      });
      await this.list.forEach((item, index) => {
        this.list[index].movieType = item.movieType.split("/");
      });
      this.imageList = imageList;

      this.listLoading = false;
    },
  },
};
</script>
<style>
/*.MovieManagement-container{*/
/*    overflow: auto;*/
/*}*/
.MovieManagement-container .el-button {
  font-size: 13px;
  height: 32px;
  line-height: 1;
  text-align: center;
  padding: 9px 10px;
}

.MovieManagement-container .el-input__inner {
  height: 32px;
  line-height: 32px;
  font-size: 13px;
  margin: 0;
}

.MovieManagement-container .el-form-item {
  margin: 0 10px 0 0;
}

.MovieManagement-table .el-table td {
  font-size: 14px;
  padding: 7.5px 0;
  height: 25px;
}

.MovieManagement-container .MovieManagement-do {
  width: 95%;
  margin: 20px 2.5% 10px 2.5%;
}

.MovieManagement-container .MovieManagement-do-right {
  height: 40px;
  display: inline-block;
  float: right;
}

.MovieManagement-container .MovieManagement-do-left {
  height: 40px;
  width: 50%;
  padding-left: 10px;
  display: inline-block;
}
.MovieManagement-container .cell {
  text-align: center;
}
</style>
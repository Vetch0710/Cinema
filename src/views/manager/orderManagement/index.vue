<template>
  <div class="OrderManagement-container">
    <div class="OrderManagement-do">
      <div class="OrderManagement-do-right">
        <el-form :inline="true" :model="queryForm" @submit.native.prevent>
          <el-form-item>
            <el-input
                    v-model.trim="queryForm.selectValues"
                    placeholder="请输入查询内容"
                    clearable
            ><el-select
                    v-model="queryForm.selectType"
                    slot="prepend"
                    placeholder="查询条件"
                    style="width: 100px"
            >
              <el-option label="电影名称" value="movieName"></el-option>
              <el-option
                      label="用户名称"
                      value="customerName"
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
    <div class="OrderManagement-table">
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
                prop="orderId"
                label="订单编号"
        ></el-table-column>

        <el-table-column
                show-overflow-tooltip
                prop="movieName"
                label="电影名称"
        ></el-table-column>
        <el-table-column
                show-overflow-tooltip
                prop="orderSeat"
                label="座位信息"
        >
          <template slot-scope="scope">
            <div class="OrderManagement-seatInfo">
              <span v-for="( item, index) in scope.row.orderSeat.split(',')" :key="index" style="display: inline-block;padding: 0 5px">{{item}}</span>
            </div>
          </template>


        </el-table-column>
        <el-table-column
                show-overflow-tooltip
                prop="orderTime"
                label="订单时间"
                :formatter="changeTime"
        ></el-table-column>
        <el-table-column
                show-overflow-tooltip
                prop="orderPrice"
                label="金额"
        ></el-table-column>
        <el-table-column
                show-overflow-tooltip
                prop="orderStatus"
                label="状态"
        ></el-table-column>
        <el-table-column
                show-overflow-tooltip
                prop="customerName"
                label="用户名称"
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
  import {getOrderList} from "@/api/order";
  import path from "path";
  import {thirteenBitTimestamp} from "@/utils/index";

  export default {
    name: "OrderManagement",
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
      changeTime: function (value) {
        // console.log( value)
        return thirteenBitTimestamp(value.orderTime)
      },
      // setSelectRows(val) {
      //   this.selectRows = val;
      // },

      formatMovieTime(row, column) {
        return row.movieTime + "分钟";
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
        console.log(this.queryForm)
        this.queryForm.pageNo = 1;
        this.fetchData();
      },

      async fetchData() {
        this.listLoading = true;
        const { result, totalCount } = await getOrderList(this.queryForm);
        // console.log(data)
        this.list = result;
        // console.log(this.list.orderSeat)
        // this.list.orderSeat=this.list.orderSeat.split(",")
        // console.log(typeof this.list);
        // console.log(parseInt(this.list[this.list.length - 1].id) + 1);
        this.total = totalCount;
        setTimeout(() => {
          this.listLoading = false;
        }, 300);
      },
    },
  };
</script>
<style>
  /*.OrderManagement-container{*/
  /*    overflow: auto;*/
  /*}*/
  .OrderManagement-container .el-button {
    font-size: 13px;
    height: 32px;
    line-height: 1;
    text-align: center;
    padding: 9px 10px;
  }

  .OrderManagement-container .el-input__inner {
    height: 32px;
    line-height: 32px;
    font-size: 13px;
    margin: 0;
  }

  .el-form-item__content {
    line-height: 33px !important;
  }

  .OrderManagement-container .el-form-item {
    margin: 0 10px 0 0;
  }

  .OrderManagement-table .el-table td {
    font-size: 14px;
    padding: 7.5px 0;
    height: 25px;
  }

  .OrderManagement-container .OrderManagement-do {
    width: 95%;
    margin: 20px 2.5% 10px 2.5%;
  }

  .OrderManagement-container .OrderManagement-do-right {
    height: 40px;
    display: inline-block;
    float: right;
  }

  .OrderManagement-container .OrderManagement-do-left {
    height: 40px;
    width: 50%;
    padding-left: 10px;
    display: inline-block;
  }
  .OrderManagement-container .cell {
    text-align: center;
  }
  .el-form-item__content {
    line-height: 32px;
    position: relative;
    font-size: 14px;
  }
  .OrderManagement-container .el-input__icon{
    line-height: 0;
  }
</style>

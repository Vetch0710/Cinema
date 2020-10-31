<template>
    <div class="customerInfo-container">
        <div class="customerInfo-do">
            <div class="customerInfo-do-left">
                <el-button icon="el-icon-delete" type="danger" @click="handleDelete">
                    批量删除
                </el-button>
            </div>
           <!-- <div class="customerInfo-do-right">
                <el-form :inline="true" :model="queryForm" @submit.native.prevent>
                    <el-form-item>
                        <el-input
                                v-model.trim="queryForm.username"
                                placeholder="请输入用户名"
                                clearable
                        />
                    </el-form-item>
                    <el-form-item>
                        <el-button icon="el-icon-search" type="primary" @click="queryData">
                            查询
                        </el-button>
                    </el-form-item>
                </el-form>
            </div>-->
        </div>
        <div class="customerInfo-table">
            <el-table
                    style="width: 95%;text-align: center;margin: 0 auto;font-size: 14px !important;"
                    v-loading="listLoading"
                    :data="list"
                    :element-loading-text="elementLoadingText"
                    @selection-change="setSelectRows"
                    :header-cell-style="{background:'#f5f7fa',color:'#606266','font-size': '14px','font-weight': '400','padding': '7.5px 0',
    'height': '30px'}"
            >
                <el-table-column show-overflow-tooltip type="selection"></el-table-column>
                <el-table-column
                        show-overflow-tooltip
                        prop="customerId"
                        label="id"
                ></el-table-column>
                <el-table-column
                        show-overflow-tooltip
                        prop="customerName"
                        label="用户名"
                ></el-table-column>
                <el-table-column show-overflow-tooltip prop="customerPassword" label="密码">
                    ******
                </el-table-column>
                <el-table-column
                        show-overflow-tooltip
                        prop="customerPhone"
                        label="手机号"
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
                style="text-align: center;margin: 20px 0;"
                background
                :current-page="queryForm.pageNo"
                :page-size="queryForm.pageSize"
                :layout="layout"
                :total="total"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
        ></el-pagination>
        <edit ref="edit" @fetch-data="fetchData"></edit>
    </div>
</template>

<script>
    import {getList, doDelete} from "@/api/userManagement";
    import Edit from "./components/UserManagementEdit";

    export default {
        name: "CustomerInfo",
        components: {Edit},
        data() {
            return {
                list: null,
                listLoading: true,
                layout: "total, sizes, prev, pager, next, jumper",
                total: 0,
                selectRows: "",
                elementLoadingText: "正在加载...",
                queryForm: {
                    pageNo: 1,
                    pageSize: 10,
                    username: "",
                    identity:'customer'
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
            handleEdit(row) {
                if (row.customerId) {
                    this.$refs["edit"].showEdit(row);
                } else {
                    this.$refs["edit"].showEdit();
                }
            },
            handleDelete(row) {
                if (row.customerId) {
                    this.$baseConfirm("你确定要删除当前项吗", null, async () => {
                        const msg = await doDelete({
                            type:'customer',
                            Ids: row.customerId
                        });
                        if ("success"=== msg){
                            this.$baseMessage("删除成功", "success");
                        }else {
                            this.$baseMessage(msg, "error");
                        }
                        this.fetchData();
                    });
                } else {
                    if (this.selectRows.length > 0) {
                        const customerIds = this.selectRows.map((item) => item.customerId);
                        console.log("*-*-*-*-*-*-"+customerIds)
                        this.$baseConfirm("你确定要删除选中项吗", null, async () => {
                            const msg = await doDelete({
                                type:'customer',
                                 Ids:customerIds
                            });
                            if ("success"=== msg){
                                this.$baseMessage("删除成功", "success");
                            }else {
                                this.$baseMessage(msg, "error");
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
                console.log(this.queryForm);
                const {result, count} = await getList(this.queryForm);
                this.list = result;
                this.total = count;
                setTimeout(() => {
                    this.listLoading = false;
                }, 300);
            },
        },
    };
</script>
<style>
    /*.customerInfo-container{*/
    /*    overflow: auto;*/
    /*}*/
    .customerInfo-container .el-button {
        font-size: 13px;
        height: 32px;
        line-height: 1;
        text-align: center;
        padding: 9px 15px;
    }

    .customerInfo-container .el-input__inner {
        height: 32px;
        line-height: 32px;
        font-size: 13px;
        margin: 0;
    }

    .customerInfo-container .el-form-item {
        margin: 0 10px 0 0;
    }

    .customerInfo-table .el-table td {
        font-size: 14px;
        padding: 7.5px 0;
        height: 25px;
    }

    .customerInfo-container .customerInfo-do {
        width: 95%;
        margin: 20px 2.5% 10px 2.5%;

    }

    .customerInfo-container .customerInfo-do-right {
        height: 40px;
        display: inline-block;
        float: right;
    }

    .customerInfo-container .customerInfo-do-left {
        height: 40px;
        width: 50%;
        padding-left: 10px;
        display: inline-block;
    }
</style>
<template>
    <div class="ManagerInfo-container">
        <div class="ManagerInfo-do">
            <div class="ManagerInfo-do-left">
                <el-button icon="el-icon-plus" type="primary" @click="handleEdit">
                    添加
                </el-button>
                <el-button icon="el-icon-delete" type="danger" @click="handleDelete">
                    批量删除
                </el-button>
            </div>
            <div class="ManagerInfo-do-right">
                <el-form :inline="true" :model="queryForm" @submit.native.prevent>
                    <el-form-item>
                        <el-input
                                v-model.trim="queryForm.permission"
                                placeholder="请输入查询条件"
                                clearable
                        />
                    </el-form-item>
                    <el-form-item>
                        <el-button icon="el-icon-search" type="primary" @click="queryData">
                            查询
                        </el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <div class="ManagerInfo-table">
        <el-table
                style="width: 95%;text-align: center;margin: 0 auto;"
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
                    prop="id"
                    label="id"
            ></el-table-column>
            <el-table-column
                    show-overflow-tooltip
                    prop="name"
                    label="姓名"
            ></el-table-column>
            <el-table-column
                    show-overflow-tooltip
                    prop="permission"
                    label="权限"
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
        <edit ref="edit" :max-list-num="maxNum" @fetch-data="fetchData"></edit>
    </div>
</template>

<script>
    import {getList, doDelete, getMaxNum} from "@/api/roleManagement";
    import Edit from "./components/RoleManagementEdit";

    export default {
        name: "ManagerInfo",
        components: {Edit},
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
                    permission: "",
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
            async changeNum() {
                const {num} = await getMaxNum();
                this.maxNum = num;
            },
            async handleEdit(row) {
                if (row.id) {
                    this.$refs["edit"].showEdit(row);
                } else {
                    await this.changeNum();
                    this.$refs["edit"].showEdit();
                }
            },
            handleDelete(row) {
                if (row.id) {
                    this.$baseConfirm("你确定要删除当前项吗", null, async () => {
                        const {msg} = await doDelete({ids: row.id});
                        this.$baseMessage(msg, "success");
                        this.fetchData();
                    });
                } else {
                    if (this.selectRows.length > 0) {
                        const ids = this.selectRows.map((item) => item.id);
                        this.$baseConfirm("你确定要删除选中项吗", null, async () => {
                            const {msg} = await doDelete({ids});
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
                const {data, totalCount} = await getList(this.queryForm);
                this.list = data;
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
    /*.ManagerInfo-container{*/
    /*    overflow: auto;*/
    /*}*/
    .ManagerInfo-container .el-button {
        font-size: 13px;
        height: 32px;
        line-height: 1;
        text-align: center;
        padding: 9px 15px;
    }

    .ManagerInfo-container .el-input__inner {
        height: 32px;
        line-height: 32px;
        font-size: 13px;
        margin: 0;
    }

    .ManagerInfo-container .el-form-item {
        margin: 0 10px 0 0;
    }

    .ManagerInfo-table .el-table td {
        font-size: 14px;
        padding: 7.5px 0;
        height: 25px;
    }

    .ManagerInfo-container .ManagerInfo-do {
        width: 95%;
        margin: 20px 2.5% 10px 2.5%;

    }

    .ManagerInfo-container .ManagerInfo-do-right {
        height: 40px;
        display: inline-block;
        float: right;
    }

    .ManagerInfo-container .ManagerInfo-do-left {
        height: 40px;
        width: 50%;
        padding-left: 10px;
        display: inline-block;
    }
</style>
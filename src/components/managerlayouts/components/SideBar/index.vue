<!--router	是否使用 vue-router 的模式，启用该模式会在激活导航时以 index 作为 path 进行路由跳转	boolean	—	false-->
<template>
    <div class="Side-nav">
        <el-menu
                style="overflow: hidden; height: 975px"
                :default-active="activeMenu"
                background-color="#ffffff"
                :text-color="'black'"
                :active-text-color="'#e65252'"
                router
                class="el-menu-vertical-demo"
        >
            <el-menu-item index="/customerCenter/index">
                <i class="el-icon-menu"></i>
                <span slot="title">我的主页</span>
            </el-menu-item>
            <el-menu-item index="/customerInfo/index">
                <i class="el-icon-menu"></i>
                <span slot="title">个人信息</span>
            </el-menu-item>
            <el-submenu index="2">
                <template slot="title">
                    <i class="el-icon-location"></i>
                    <span>我的订单</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="/customerOrder/totalOrder">
                        全部订单
                    </el-menu-item>
                    <el-menu-item index="/customerOrder/finish">已完成</el-menu-item>
                    <el-menu-item index="/customerOrder/waitingPay">待支付</el-menu-item>
                    <el-menu-item index="/customerOrder/waitingEvaluation">
                        待评价
                    </el-menu-item>
                </el-menu-item-group>
            </el-submenu>

            <el-menu-item index="/customerEvaluation/index">
                <i class="el-icon-document"></i>
                <span slot="title">我的评价</span>
            </el-menu-item>
        </el-menu>
    </div>
</template>

<script>
    module.exports = {
        name: "Side-nav",
        data() {
            return {
                NowPath: this.$route.path,
                i: 0,
            };
        },
        methods: {},
        computed: {
            activeMenu() {
                const route = this.$route;
                const {meta, path} = route;
                if (meta.activeMenu) {
                    return meta.activeMenu;
                }
                return path;
            },
        },
    };
</script>

<style>
    .Side-nav {
        width: 250px;
        height: 800px;
        /*background-color: #ffffff;*/
        /*position: absolute;*/
        float: left;
        margin: 20px;
        /*color: red;*/
        /*left: 50px;*/
        /*top: 200px;*/
        overflow: hidden;
    }

    .is-active {
        font-weight: bolder;
    }
</style>

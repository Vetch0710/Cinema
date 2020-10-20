<template>
    <el-scrollbar class="side-bar-container" :class="{ 'is-collapse': collapse }">
        <logo />
        <el-menu
                :background-color="'rgb(40, 44, 52)'"
                :text-color="'hsla(0, 0%, 100%, 0.95)'"
                :active-text-color="'hsla(0, 0%, 100%, 0.95)'"
                :default-active="activeMenu"
                :collapse="collapse"
                :collapse-transition="false"
                :default-openeds="defaultOpens"
                :unique-opened="uniqueOpened"
                mode="vertical"
        >
            <template v-for="route in routes">
                <side-bar-item
                        :key="route.path"
                        :full-path="route.path"
                        :item="route"
                ></side-bar-item>
            </template>
        </el-menu>
    </el-scrollbar>
</template>
<script>
    import SideBarItem from "./components/SideBarItem";
    import  Logo  from "./Logo";
    import { mapGetters } from "vuex";
    import { defaultOopeneds, uniqueOpened } from "@/config/settings";

    export default {
        name: "SideBar",
        components: { SideBarItem ,Logo},
        data() {
            return {
                uniqueOpened,
            };
        },
        computed: {
            ...mapGetters({
                collapse: "settings/collapse",
                routes: "routes/routes",
            }),
            defaultOpens() {
                if (this.collapse) {
                }
                return defaultOopeneds;
            },
            activeMenu() {
                const route = this.$route;
                const { meta, path } = route;
                if (meta.activeMenu) {
                    return meta.activeMenu;
                }
                return path;
            },
        },
    };
</script>
<style lang="scss" scoped>
    @mixin active {
        &:hover {
            color: #fff;
            background-color: #1890ff !important;
        }

        &.is-active {
            color: #fff;
            background-color: #1890ff !important;
        }
    }

    .side-bar-container {
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
        z-index: 999;
        width: 240px;
        height: 100vh;
        overflow: hidden;
        background: rgb(40, 44, 52) ;
        box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);
        transition: width 0.2s;

        &.is-collapse {
            width: 65px;
            border-right: 0;

            ::v-deep {
                .el-menu {
                    transition: width 0.2s;
                }

                .el-menu--collapse {
                    border-right: 0;

                    .el-submenu__icon-arrow {
                        right: 10px;
                        margin-top: -3px;
                    }
                }
            }
        }

        ::v-deep {
            .el-scrollbar__wrap {
                overflow-x: hidden;
            }

            .el-menu {
                border: 0;

                .vab-fas-icon {
                    padding-right: 3px;
                    font-size: 14px;
                }

                .vab-remix-icon {
                    padding-right: 3px;
                    font-size: 14px + 2;
                }
            }

            .el-menu-item,
            .el-submenu__title {
                height: 50px;
                overflow: hidden;
                line-height: 50px;
                text-overflow: ellipsis;
                white-space: nowrap;
                vertical-align: middle;
            }

            .el-menu-item {
                @include active;
            }
        }
    }
</style>

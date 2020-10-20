<template>
  <div class="nav-bar-container">
    <el-row :gutter="15">
      <el-col :xs="4" :sm="12" :md="12" :lg="12" :xl="12">
        <div class="left-panel">
          <i
            :class="collapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'"
            :title="collapse ? '展开' : '收起'"
            class="fold-unfold"
            @click="handleCollapse"
          ></i>
          <breadcrumb class="hidden-xs-only" />
        </div>
      </el-col>
      <el-col :xs="20" :sm="12" :md="12" :lg="12" :xl="12">
        <div class="right-panel">
          <!--          <error-log></error-log>-->
<!--          <full-screen-bar @refresh="refreshRoute"></full-screen-bar>-->
          <!--          <theme-bar class="hidden-xs-only"></theme-bar>-->
<!--          <i-->
<!--            title="重载所有路由"-->
<!--            :pulse="pulse"-->
<!--            class="el-icon-refresh-right"-->
<!--            :icon="['fas', 'redo']"-->
<!--            @click="refreshRoute"-->
<!--          ></i>-->
          <Avatar></Avatar>
          <!--  <vab-icon
            title="退出系统"
            :icon="['fas', 'sign-out-alt']"
            @click="logout"
          />-->
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { mapActions, mapGetters } from "vuex";

  // import {Avatar,Breadcrumb} from "@/components/managerlayouts/components";
  import Avatar from "@/components/managerlayouts/components/Avatar";
  import Breadcrumb from "@/components/managerlayouts/components/Breadcrumb";

  export default {
    name: "NavBar",
    components: {
      Avatar,
      Breadcrumb,
      // ErrorLog,
      // FullScreenBar,
      // ThemeBar,
    },
    data() {
      return {
        pulse: false,
      };
    },
    computed: {
      ...mapGetters({
        collapse: "settings/collapse",
        visitedRoutes: "tabsBar/visitedRoutes",
        device: "settings/device",
        routes: "routes/routes",
      }),
    },
    methods: {
      ...mapActions({
        changeCollapse: "settings/changeCollapse",
      }),
      handleCollapse() {
        this.changeCollapse();
      },
      async refreshRoute() {
        this.$baseEventBus.$emit("reload-routerview");
        this.pulse = true;
        setTimeout(() => {
          this.pulse = false;
        }, 1000);
      },
    },
  };
</script>

<style lang="scss" scoped>
  .nav-bar-container {
    position: relative;
    height: 60px;
    padding-right:  20px;
      padding-left: 20px;
    overflow: hidden;
    user-select: none;
    background: #fff;
    box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);;

    .left-panel {
      display: flex;
      align-items: center;
      justify-items: center;
      height: 60px;

      .fold-unfold {
        font-size: 20px;
        color: rgba(0, 0, 0, 0.65);
        cursor: pointer;
      }

      ::v-deep {
        .breadcrumb-container {
          margin-left: 10px;
        }
      }
    }

    .right-panel {
      display: flex;
      align-content: center;
      align-items: center;
      justify-content: flex-end;
      height: 60px;;

      ::v-deep {
        svg {
          width: 1em;
          height: 1em;
          margin-right: 15px;
          font-size: 16px;
          color: rgba(0, 0, 0, 0.65);
          cursor: pointer;
          fill: rgba(0, 0, 0, 0.65);;
        }

        button {
          svg {
            margin-right: 0;
            color: #fff;
            cursor: pointer;
            fill: #fff;
          }
        }

        .el-badge {
          margin-right: 15px;
        }
      }
    }
  }
</style>

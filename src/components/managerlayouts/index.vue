<template>
  <div class="vue-admin-beautiful-wrapper" :class="classObj">
    <div
      class="layout-container-vertical"
    >
      <side-bar></side-bar>
      <div class="vab-main" :class="collapse ? 'is-collapse-main' : ''">
        <div :class="header === 'fixed' ? 'fixed-header' : ''">
          <nav-bar></nav-bar>
          <tabs-bar />
        </div>
        <!--        <ad></ad>-->
        <app-main></app-main>
      </div>
    </div>
    <el-backtop></el-backtop>
  </div>
</template>

<script>
  import { AppMain, NavBar, SideBar, TabsBar} from "./components";
  import { mapActions, mapGetters } from "vuex";
  import { tokenName } from "@/config/settings";
  export default {
    name: "Layout",
    components: {
      NavBar,
      SideBar,
      AppMain,
      TabsBar
    },
    data() {
      return { oldLayout: "" };
    },
    computed: {
      ...mapGetters({
        layout: "settings/layout",
        tabsBar: "settings/tabsBar",
        collapse: "settings/collapse",
        header: "settings/header",
        device: "settings/device",
      }),
      classObj() {
        return {
          mobile: this.device === "mobile",
        };
      },
    },
    beforeMount() {
      window.addEventListener("resize", this.handleResize);
    },
    beforeDestroy() {
      window.removeEventListener("resize", this.handleResize);
    },
    mounted() {
      this.oldLayout = this.layout;
      const userAgent = navigator.userAgent;
      if (userAgent.includes("Juejin")) {
        this.$baseAlert(
          "vue-admin-beautiful不支持在掘金内置浏览器演示，请手动复制以下地址到浏览器中查看http://mpfhrd48.sanxing.uz7.cn/vue-admin-beautiful"
        );
      }
      const isMobile = this.handleIsMobile();
      if (isMobile) {
        if (isMobile) {
          //横向布局时如果是手机端访问那么改成纵向版
          this.$store.dispatch("settings/changeLayout", "vertical");
        } else {
          this.$store.dispatch("settings/changeLayout", this.oldLayout);
        }
        this.$store.dispatch("settings/toggleDevice", "mobile");
        setTimeout(() => {
          this.$store.dispatch("settings/foldSideBar");
        }, 2000);
      } else {
        this.$store.dispatch("settings/openSideBar");
      }
      this.$nextTick(() => {
        window.addEventListener(
          "storage",
          (e) => {
            if (e.key === tokenName || e.key === null) window.location.reload();
            if (e.key === tokenName && e.value === null)
              window.location.reload();
          },
          false
        );
      });
    },
    methods: {
      ...mapActions({
        handleFoldSideBar: "settings/foldSideBar",
      }),
      handleIsMobile() {
        return document.body.getBoundingClientRect().width - 1 < 992;
      },
      handleResize() {
        if (!document.hidden) {
          const isMobile = this.handleIsMobile();
          if (isMobile) {
            //横向布局时如果是手机端访问那么改成纵向版
            this.$store.dispatch("settings/changeLayout", "vertical");
          } else {
            this.$store.dispatch("settings/changeLayout", this.oldLayout);
          }

          this.$store.dispatch(
            "settings/toggleDevice",
            isMobile ? "mobile" : "desktop"
          );
        }
      },
    },
  };
</script>

<style lang="scss" scoped>
  @mixin fix-header {
    position: fixed;
    top: 0;
    right: 0;
    left: 0;
    z-index: 999 - 2;
    width: 100%;
    overflow: hidden;
  }

  .vue-admin-beautiful-wrapper {
    position: relative;
    width: 100%;
    height: 100%;

    .layout-container-horizontal {
      position: relative;

      &.fixed {
        padding-top: calc(65px + 55px);
      }

      &.fixed.no-tabs-bar {
        padding-top: 65px;
      }

      ::v-deep {
        .vab-main {
          width: 88%;
          margin: auto;
        }

        .fixed-header {
          @include fix-header;
        }

        .tag-view-show {
          background: #fff;
          box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);;
        }

        .nav-bar-container {
          .fold-unfold {
            display: none;
          }
        }

        .main-padding {
          .app-main-container {
            margin-top: 20px;
            margin-bottom: 20px;
            background: #fff;
          }
        }
      }
    }

    .layout-container-vertical {
      position: relative;

      .mask {
        position: fixed;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        z-index: 999 - 1;
        width: 100%;
        height: 100vh;
        overflow: hidden;
        background: #000;
        opacity: 0.5;
      }

      &.fixed {
          padding-top: calc(60px+ 55px);
      }

      &.fixed.no-tabs-bar {
        padding-top: 60px;
      }

      .vab-main {
        position: relative;
        min-height: 100%;
        margin-left: 240px;
        background: #f6f8f9;
        transition: all 0.2s;;

        ::v-deep {
          .fixed-header {
            @include fix-header;

            left: 240px;
            width: calc(100% - 240px);;
            box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);;
            transition: all 0.2s;;
          }

          .nav-bar-container {
            position: relative;
            box-sizing: border-box;
          }

          .tabs-bar-container {
            box-sizing: border-box;
          }

          .app-main-container {
            width: calc(100% - 20px - 20px);
            margin: 20px auto;
            background:  #fff;;
            border-radius: 8px;;
          }
        }

        &.is-collapse-main {
          margin-left: 65px;

          ::v-deep {
            .fixed-header {
              left: 65px;;
              width: calc(100% - 65px);
            }
          }
        }
      }
    }

    /* 手机端开始 */
    &.mobile {
      ::v-deep {
        .el-pager,
        .el-pagination__jump {
          display: none;
        }

        .layout-container-vertical {
          .el-scrollbar.side-bar-container.is-collapse {
            width: 0;
          }

          .vab-main {
            width: 100%;
            margin-left: 0;
          }
        }

        .vab-main {
          .fixed-header {
            left: 0 !important;
            width: 100% !important;
          }
        }
      }
    }

    /* 手机端结束 */
  }
</style>

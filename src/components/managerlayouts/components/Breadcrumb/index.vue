<template>
  <el-breadcrumb class="breadcrumb-container" separator=">">
    <el-breadcrumb-item v-for="item in list" :key="item.path">
      {{ item.meta.title }}
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script>
  export default {
    name: "Breadcrumb",
    data() {
      return {
        list: this.getBreadcrumb(),
      };
    },
    watch: {
      $route() {
        this.list = this.getBreadcrumb();
      },
    },
    methods: {
      getBreadcrumb() {
        //返回一个数组对象。这个对象包含了从当前路由开始往上查找到的所有的路由对象集合
        return this.$route.matched.filter(
          (item) => item.name && item.meta.title
        );
      },
    },
  };
</script>

<style lang="scss" scoped>
  .breadcrumb-container {
    height: 60px;
      font-size:14px;
    line-height: 60px;

    ::v-deep {
      .el-breadcrumb__item {
        .el-breadcrumb__inner {
          a {
            display: flex;
            float: left;
            font-weight: normal;
            color: #515a6e;

            i {
              margin-right: 3px;
            }
          }
        }

        &:last-child {
          .el-breadcrumb__inner {
            a {
              color: #999;
            }
          }
        }
      }
    }
  }
</style>

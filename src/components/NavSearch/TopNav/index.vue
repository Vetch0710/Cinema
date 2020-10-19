<template>
  <div class="TopNav-container">
    <div class="topNav">
      <ul class="topLtNav">
        <li>
          <router-link to="/index">首页</router-link>
        </li>
        <li v-if="!this.accessToken">
          <router-link to="/login" class="obviousText">请登录</router-link>
        </li>
        <li v-if="!this.accessToken">
          <router-link to="/register">注册</router-link>
        </li>
      </ul>
      <ul class="topRtNav">
        <li>
          <router-link v-if="identity('customer')" to="/customerCenter">
            {{ this.username !== "" ? this.username : "用户中心" }}
          </router-link>
        </li>
        <li>
          <router-link v-if="identity('manager')" to="/manager">
            管理中心
          </router-link>
        </li>
        <li>
          <router-link
            v-if="identity('customer')"
            to="/customerOrder/totalOrder"
          >
            订单
          </router-link>
        </li>
        <li>
          <router-link v-if="identity('customer')" to="/customerEvaluation">
            评价
          </router-link>
        </li>
        <li v-if="this.accessToken">
          <a href="" @click.prevent="logout">安全退出</a>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
  import { mapGetters } from "vuex";
  import { recordRoute } from "@/config/settings";
  import { routesWhiteList } from "@/config/settings";

  export default {
    name: "TopNav",
    data() {
      return {
        // nodeEnv: process.env.NODE_ENV,
      };
    },
    computed: {
      ...mapGetters({
        username: "user/username",
        accessToken: "user/accessToken",
        permissions: "user/permissions",
      }),
    },

    created() {},
    mounted() {},
    methods: {
      logout() {
        this.$baseConfirm("您确定要退出吗?", null, async () => {
          await this.$store.dispatch("user/logout");
          console.log(this.$route.path);
          if (routesWhiteList.indexOf(this.$route.path) === -1) {
            if (recordRoute) {
              const fullPath = this.$route.fullPath;
              this.$router.push(`/login?redirect=${fullPath}`);
            } else {
              this.$router.push("/login");
            }
          }
        });
      },
      identity(item) {
        if (this.permissions.length === 0){
          return true;
        }else {
          if (item === "customer"){
            return this.permissions.indexOf("customer") !== -1  ;
          }else {
            return this.permissions.indexOf("manager") !== -1 || this.permissions.indexOf("boss") !== -1;
          }
        }



      },
    },
  };
</script>
<style>
  .TopNav-container {
    width: 1350px;
    height: 30px;
    background: #eeeeee;
    margin: 0 auto;
  }
  .topNav {
    width: 1300px;
    margin: 0 auto;
    height: 30px;
    overflow: hidden;
  }

  .topNav li {
    list-style-type: none;
    display: inline;
  }

  .topNav a {
    height: 30px;
    line-height: 30px;
    display: block;
    float: left;
    margin: 0 15px;
    color: #6c6c98;
    text-decoration: none;
    font-size: 14px;
  }

  .topNav .topLtNav {
    padding: 0;
    margin: 0 0 0 60px;
    width: 20%;
    height: 30px;
    line-height: 30px;
    float: left;
  }

  .topNav .obviousText {
    color: #ec515f;
  }

  .topNav .topRtNav {
    padding: 0;
    margin: 0 40px 0 0;
    float: right;
  }
</style>

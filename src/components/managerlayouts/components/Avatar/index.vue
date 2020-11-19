<template>
  <el-dropdown @command="handleCommand">
    <span class="avatar-dropdown">
      <img class="user-avatar" src="http://8.131.77.164:8089/CinemaData/img/manager.jpeg" alt="" />
      <div class="user-name">
        {{ username }}
        <i class="el-icon-arrow-down el-icon--right"></i>
      </div>
    </span>

    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item command="personalCenter" divided>
       <i class="el-icon-user"></i>
        个人中心
      </el-dropdown-item>
      <el-dropdown-item command="logout" divided>
       <i class="el-icon-switch-button"></i>
        退出登录
      </el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>
  import { mapGetters } from "vuex";
  import { recordRoute } from "@/config/settings";

  export default {
    name: "Avatar",
    computed: {
      ...mapGetters({
        avatar: "user/avatar",
        username: "user/username",
      }),
    },
    methods: {
      handleCommand(command) {
        switch (command) {
          case "logout":
            this.logout();
            break;
          case "personalCenter":
            this.personalCenter();
            break;
        }
      },
      personalCenter() {
        this.$router.push("/userManagement/personalInfo");
      },
      logout() {
        this.$baseConfirm(
          "您确定要退出" + this.$baseTitle + "吗?",
          null,
          async () => {
            await this.$store.dispatch("user/logout");
            if (recordRoute) {
              const fullPath = this.$route.fullPath;
              this.$router.push(`/login?redirect=${fullPath}&&type=manager`);
            } else {
              this.$router.push("/login");
            }
          }
        );
      },
    },
  };
</script>
<style lang="scss" scoped>
  .avatar-dropdown {
    display: flex;
    align-content: center;
    align-items: center;
    justify-content: center;
    justify-items: center;
    height: 50px;
    padding: 0;

    .user-avatar {
      width: 40px;
      height: 40px;
      cursor: pointer;
      border-radius: 50%;
    }

    .user-name {
      position: relative;
      margin-left: 5px;
      margin-left: 5px;
      font-weight: 600;
      cursor: pointer;
    }
  }
</style>

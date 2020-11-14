<template>
  <div class="login-container">
    <div class="login-logo">
      <router-link to="/index">
        <el-image
          style="width: 180px; height: 100px"
          :src="require('@/assets/logo2.png')"
          :fit="'contain'"
          v-cloak
        >
          <div slot="placeholder" class="image-slot"></div>
        </el-image>
      </router-link>
    </div>
    <div class="cap">
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        class="login-form"
        label-position="left"
      >
        <div class="title">欢迎登陆</div>
        <div class="title-tips">
          {{ type === "customer" ? "用户" : "管理员" }}登陆！
        </div>
        <div class="login-type" @click="change">
          {{ loginType === "userName" ? "短信登陆" : "用户名登陆" }}
        </div>
        <div v-if="loginType === 'userName'">
          <el-form-item
            v-if="type === 'customer'"
            style="margin-top: 40px"
            prop="username"
          >
            <el-input
              v-model.trim="form.username"
              placeholder="请输入用户名"
              tabindex="1"
              type="text"
              autocomplete="on"
              prefix-icon="el-icon-user-solid"
            />
          </el-form-item>
          <el-form-item
            v-if="type === 'manager'"
            style="margin-top: 40px"
            prop="managerId"
          >
            <el-input
              v-model.trim="form.managerId"
              placeholder="请输入管理员账号"
              tabindex="1"
              type="text"
              prefix-icon="el-icon-user-solid"
            />
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              :key="passwordType"
              ref="password"
              v-model.trim="form.password"
              :type="passwordType"
              tabindex="2"
              placeholder="请输入密码"
              @keyup.enter.native="handleLogin"
              prefix-icon="el-icon-lock"
            />
            <span
              v-if="passwordType === 'password'"
              class="show-password"
              @click="handlePassword"
            >
              <i class="el-icon-view"></i>
            </span>
            <span v-else class="show-password" @click="handlePassword"> </span>
          </el-form-item>
        </div>
        <div v-if="loginType === 'phoneCode'">
          <el-form-item style="margin-top: 40px" prop="phone">
            <el-input
              v-model.trim="form.phone"
              type="text"
              placeholder="请输入手机号"
              maxlength="11"
              autocomplete="off"
              prefix-icon="el-icon-mobile-phone"
            ></el-input>
          </el-form-item>
          <el-form-item prop="phoneCode" style="position: relative">
            <el-input
              v-model.trim="form.phoneCode"
              type="text"
              placeholder="手机验证码"
              style="width: 300px"
              prefix-icon="el-icon-postcard"
            ></el-input>
            <el-button
              style="height: 45px"
              type="primary"
              class="show-pwd phone-code"
              :disabled="isGetphone"
              @click="getPhoneCode"
            >
              {{ phoneCode }}
            </el-button>
          </el-form-item>
        </div>
        <div class="cusDo">
          <router-link to="/resetPassword">忘记密码</router-link>
          <router-link to="/register">注册</router-link>
        </div>

        <el-button
          :loading="loading"
          class="login-btn"
          type="primary"
          @click="handleLogin"
        >
          登录
        </el-button>
      </el-form>
    </div>
  </div>
</template>

<script>
import { isPassword } from "@/utils/validate";
import { isNumber, isPhone } from "@/utils/validate";
import { getPhoneCode } from "@/api/user";
import Vue from "vue";

export default {
  name: "Login",
  // directives: {
  //   focus: {
  //     inserted(el) {
  //       el.querySelector("input").focus();
  //     },
  //   },
  // },
  data() {
    const validateusername = (rule, value, callback) => {
      if ("" === value) {
        callback(new Error("用户名不能为空"));
      } else {
        callback();
      }
    };
    const validateNum = (rule, value, callback) => {
      if (!isNumber(value)) {
        callback(new Error("请输入数字"));
      } else {
        callback();
      }
    };
    const validatePassword = (rule, value, callback) => {
      if (!isPassword(value)) {
        callback(new Error("密码不能少于6位"));
      } else {
        callback();
      }
    };
    const validatePhoneCode = (rule, value, callback) => {
      if (value !== this.realPhoneCode) {
        callback(new Error("验证码不正确"));
      } else {
        callback();
      }
    };
    const validatePhone = (rule, value, callback) => {
      if (!isPhone(value)) {
        callback(new Error("请输入正确的手机号"));
      } else {
        callback();
      }
    };
    return {
      nodeEnv: process.env.NODE_ENV,
      title: this.$baseTitle,
      type: this.$route.query.type === "manager" ? "manager" : "customer",
      loginType: "userName",
      isGetphone: false,
      getPhoneIntval: null,
      phoneCode: "获取验证码",
      realPhoneCode: "真实的验证码",
      form: {},
      rules: {
        username: [
          { required: true, trigger: "blur", message: "请输入用户名" },
          { validator: validateusername, trigger: "blur" },
        ],
        managerId: [
          { required: true, trigger: "blur", message: "请输入账号" },
          { required: true, trigger: "blur", validator: validateNum },
          { min: 1, max: 8, message: "账号为8位数字", trigger: "blur" },
        ],
        password: [
          { required: true, trigger: "blur", message: "请输入密码" },
          { validator: validatePassword, trigger: "blur" },
        ],
        phone: [
          { required: true, trigger: "blur", message: "请输入手机号码" },
          { validator: validatePhone, trigger: "blur" },
        ],
        phoneCode: [
          { required: true, trigger: "blur", message: "请输入手机验证码" },
          { required: true, trigger: "blur", validator: validateNum },
          { min: 1, max: 6, message: "验证码为6为数字", trigger: "blur" },
          { validator: validatePhoneCode, trigger: "blur" },
        ],
      },
      loading: false,
      passwordType: "password",
      redirect: undefined,
    };
  },
  watch: {
    $route: {
      handler(route) {
        this.redirect = (route.query && route.query.redirect) || "/";
      },
      immediate: true,
    },
  },

  created() {},
  beforeDestroy() {
    this.getPhoneIntval = null;
    clearInterval(this.getPhoneIntval);
  },
  mounted() {},
  methods: {
    change() {
      if (this.loginType === "userName") {
        this.loginType = "phoneCode";
      } else {
        this.loginType = "userName";
      }
      this.$refs["form"].clearValidate();
    },
    handlePassword() {
      this.passwordType === "password"
        ? (this.passwordType = "")
        : (this.passwordType = "password");
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },
    async getPhoneCode() {
      console.log(isPhone(this.form.phone));
      if (isPhone(this.form.phone)) {
        this.isGetphone = true;
        let n = 60;
        console.log(this.form.phone);
        const { result } = await getPhoneCode(this.form.phone);
        this.realPhoneCode = result;
        this.getPhoneIntval = setInterval(() => {
          if (n > 0) {
            n--;
            this.phoneCode = "重新获取(" + n + "s)";
          } else {
            clearInterval(this.getPhoneIntval);
            this.getPhoneIntval = null;
            this.phoneCode = "获取验证码";
            this.isGetphone = false;
          }
        }, 1000);
      } else {
        Vue.prototype.$baseMessage(`请先查手机号是否正确`, "error");
      }
    },
    handleLogin() {
      this.$refs.form.validate((valid) => {
        //验证表单数据
        if (valid) {
          this.loading = true;
          let param = "";
          if (this.loginType === "userName") {
            if (this.type === "customer") {
              param = {
                type: this.type,
                logintype: "userName",
                username: this.form.username,
                password: this.form.password,
              };
            } else {
              param = {
                type: this.type,
                logintype: "userName",
                managerId: this.form.managerId,
                password: this.form.password,
              };
            }
          } else if (this.loginType === "phoneCode") {
            param = {
              type: this.type,
              logintype: "phoneCode",
              phone: this.form.phone,
              phoneCode: this.form.phoneCode,
            };
          }
          console.log(param);
          this.$store
            .dispatch("user/login", param)
            .then(() => {
              console.log(this.redirect);
              const routerPath =
                this.redirect === "/404" || this.redirect === "/401"
                  ? "/"
                  : this.redirect;
              this.$router
                .push({
                  path: "/",
                })
                .catch(() => {});
              this.loading = false;
            })
            .catch(() => {
              this.loading = false;
            });
        } else {
          return false;
        }
      });
    },
  },
};

</script>

<style>
.login-container {
  width: 100%;
  height: 100vh;
  background: url("~assets/login_images/background.jpg") center center fixed
    no-repeat;
  background-size: cover;
  font-family: PingFang-SC-Regular, Helvetica, "Microsoft Yahei", 微软雅黑,
    serif;
}

.login-logo {
  position: absolute;
  top: 35px;
  left: 80px;
}

.cap {
  position: absolute;
  width: 470px;
  height: 520px;
  margin-top: calc((100vh - 550px) / 2);
  overflow: hidden;
  background-color: rgba(255, 255, 255, 0.9);
  right: 10%;
  border-radius: 10px 10px;
}

.title {
  font-size: 36px;
  font-weight: 600;
  color: rgba(14, 18, 26, 1);
  margin-top: 20px;
}

.title-tips {
  margin: 10px 0 0 10px;
  font-size: 14px;
  color: #9b9b9b;
  /*text-overflow: ellipsis;*/
  /*white-space: nowrap;*/
}

.login-type {
  margin: 10px 0 20px 10px;
  font-size: 14px;
  color: rgba(92, 102, 240, 1);
  float: right;
  cursor: pointer;
}

.login-btn {
  display: inherit;
  width: 220px;
  height: 50px;
  margin: 30px auto;
  border: 0;
}

.login-btn:hover {
  opacity: 0.9;
}

.login-form {
  position: relative;
  width: 400px;
  height: 480px;
  margin: 35px 35px;
  overflow: hidden;
}

.forget-password {
  width: 100%;
  margin-top: 40px;
  text-align: left;
}

.forget-pass {
  width: 129px;
  height: 19px;
  font-size: 20px;
  font-weight: 400;
  color: rgba(92, 102, 240, 1);
}

/*}*/

.cusDo {
  margin-top: 40px;
  margin-left: 20px;
}

.cusDo a:nth-of-type(2) {
  display: block;
  float: right;
  margin-right: 25px;
}

/*}*/
.svg-container {
  position: absolute;
  top: 11px;
  left: 20px;
  z-index: 999;
  font-size: 16px;
  color: #d7dee3;
  cursor: pointer;
  user-select: none;
}

.show-password {
  position: absolute;
  top: 5px;
  right: 25px;
  font-size: 16px;
  color: #d7dee3;
  cursor: pointer;
  user-select: none;
}

.phone-code {
  position: absolute;
  width: 120px;
  height: 45px;
  font-size: 15px;
  color: #fff;
  border-radius: 8px;
  cursor: pointer;
  user-select: none;
  right: 7px;
  text-align: center;
  /*top: -1px;*/
}

.login-container .el-input__inner {
  width: 95%;
  margin-left: 10px;
  height: 45px;
  border-radius: 8px;
}

.login-container .el-input__prefix,
.el-input__suffix {
  margin-left: 10px;
  margin-right: 10px;
}
</style>

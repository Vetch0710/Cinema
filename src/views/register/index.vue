<template>
  <div class="register-container">
    <div class="register-logo">
      <router-link to="/index">
        <el-image
          style="width: 180px; height: 100px"
          :src="require('@/assets/logo2.png')"
          :fit="'contain'"
        >
          <div slot="placeholder" class="image-slot">
          </div>
        </el-image>
      </router-link>
    </div>
    <div class="register-cap">
      <el-form
        ref="registerForm"
        :model="form"
        class="register-form"
        :rules="registerRules"
        size="mini"
      >
        <div class="register-title">欢迎注册</div>
        <div class="register-title-tips">
          <router-link to="/login">已有账号？请登录</router-link>
        </div>
        <el-form-item prop="username">
          <el-input
            v-model.trim="form.username"
            type="text"
            v-focus
            placeholder="请输入用户名"
            auto-complete="off"
            prefix-icon="el-icon-user-solid"
          ></el-input>
        </el-form-item>

        <el-form-item prop="phone">

          <el-input
            v-model.trim="form.phone"
            type="text"
            placeholder="请输入手机号"
            maxlength="11"
            show-word-limit
            autocomplete="off"
            prefix-icon="el-icon-mobile-phone"
          ></el-input>
        </el-form-item>
        <el-form-item prop="phoneCode" style="position: relative">

          <el-input
            v-model.trim="form.phoneCode"
            type="text"
            placeholder="手机验证码"
            prefix-icon="el-icon-postcard"
          ></el-input>
          <el-button
            type="primary"
            class="show-pwd phone-code"
            :disabled="isGetphone"
            @click="getPhoneCode"
          >
            {{ phoneCode }}
          </el-button>
        </el-form-item>
        <el-form-item prop="password">

          <el-input
            v-model.trim="form.password"
            type="password"
            placeholder="设置密码"
            autocomplete="new-password"
            prefix-icon="el-icon-lock"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            class="register-btn"
            type="primary"
            @click.native.prevent="handleReister"
          >
            注册
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
  import { isPassword, isPhone } from "@/utils/validate";
  import { register } from "@/api/user";
  import { isNumber } from "../../utils/validate";
  export default {
    username: "Register",
    directives: {
      focus: {
        inserted(el) {
          el.querySelector("input").focus();
        },
      },
    },
    data() {
      const validateusername = (rule, value, callback) => {
        if ("" == value) {
          callback(new Error("用户名不能为空"));
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
      const validatePhone = (rule, value, callback) => {
        if (!isPhone(value)) {
          callback(new Error("请输入正确的手机号"));
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
      return {
        isGetphone: false,
        getPhoneIntval: null,
        phoneCode: "获取验证码",
        showRegister: false,
        nodeEnv: process.env.NODE_ENV,
        title: this.$baseTitle,
        form: {},
        registerRules: {
          username: [
            { required: true, trigger: "blur", message: "请输入用户名" },
            { max: 20, trigger: "blur", message: "最多不能超过20个字" },
            { validator: validateusername, trigger: "blur" },
          ],
          phone: [
            { required: true, trigger: "blur", message: "请输入手机号码" },
            { validator: validatePhone, trigger: "blur" },
          ],
          password: [
            { required: true, trigger: "blur", message: "请输入密码" },
            { validator: validatePassword, trigger: "blur" },
          ],
          phoneCode: [
            { required: true, trigger: "blur", message: "请输入手机验证码" },
            { required: true, trigger: "blur", validator: validateNum },
          ],
        },
        loading: false,
        passwordType: "password",
      };
    },
    created() {},
    beforeDestroy() {
      this.getPhoneIntval = null;
      clearInterval(this.getPhoneIntval);
    },
    methods: {
      getPhoneCode() {
        this.isGetphone = true;
        let n = 60;
        this.getPhoneIntval = setInterval(() => {
          if (n > 0) {
            n--;
            this.phoneCode = "重新获取(" + n + "s)";
          } else {
            this.getPhoneIntval = null;
            clearInterval(this.getPhoneIntval);
            this.phoneCode = "获取验证码";
            this.isGetphone = false;
          }
        }, 1000);
      },
      handleReister() {
        this.$refs["registerForm"].validate(async (valid) => {
          if (valid) {
            const param = {
              username: this.form.username,
              phone: this.form.phone,
              password: this.form.password,
              phoneCode: this.form.phoneCode,
            };
            console.log(param);
            const { msg } = await register(param);
            this.$baseMessage(msg, "success");
            this.$router.push("/login").catch(() => {});
          }
        });
      },
    },
  };
</script>
<style >
  .register-container {
    width: 100%;
    height: 100vh;
    background: url("~assets/login_images/background.jpg") center center fixed no-repeat;
    background-size: cover;
    font-family: PingFang-SC-Regular, Helvetica, "Microsoft Yahei", 微软雅黑,
    serif;
  }

  .register-logo {
    position: absolute;
    top: 35px;
    left: 80px;
  }

  .register-cap {
    position: absolute;
    width: 470px;
    height: 520px;
    margin-top: calc((100vh - 550px) / 2);
    overflow: hidden;
    background-color: rgba(255, 255, 255, 0.9);
    right: 10%;
    border-radius: 10px 10px;

  }

  .register-title {
    font-size: 36px;
    font-weight: 600;
    color: rgba(14, 18, 26, 1);
    margin-top: 20px;
  }

  .register-title-tips {
    width: 150px;
    margin: 10px 0 20px 10px;
    font-size: 14px;
    color: #ec515f  ;
    position: relative;
    left: 250px;
  }


  .register-btn {
    display: inherit;
    width: 220px;
    height: 50px;
    margin: 30px auto;
    border: 0;
  }

  .register-btn:hover {
      opacity: 0.9;
    }


  .register-form {
    position: relative;
    width: 400px;
    height: 480px;
    margin: 35px 35px;
    overflow: hidden;
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

  .register-container .el-input__inner {
    width: 95% ;
    margin-left: 10px;
    height: 45px;
    border-radius: 8px;
    font-size: 14px;
  }

  .register-container .el-input__prefix, .el-input__suffix {
    margin-left: 10px;
    margin-right: 10px;
  }



</style>

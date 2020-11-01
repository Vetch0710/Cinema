<template>
  <div class="resetPassword-container">
    <div class="resetPassword-logo">
      <router-link to="/index">
        <el-image
          style="width: 180px; height: 100px"
          :src="require('@/assets/logo2.png')"
          :fit="'contain'"
        >
          <div slot="placeholder" class="image-slot"></div>
        </el-image>
      </router-link>
    </div>
    <div class="resetPassword-cap">
      <el-form
        ref="resetPasswordForm"
        :model="form"
        class="resetPassword-form"
        :rules="resetPasswordRules"
        size="mini"
      >
        <div class="resetPassword-title">密码找回</div>
        <div class="resetPassword-title-tips">
          <router-link to="/login">已有账号？请登录</router-link>
        </div>
        <el-form-item
          label="请选择您的身份:"
          prop="resource"
          style="margin-left: 5px"
        >
          <el-radio-group v-model="form.identity">
            <el-radio label="顾客"></el-radio>
            <el-radio label="管理员"></el-radio>
          </el-radio-group>
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
            placeholder="设置新密码"
            autocomplete="new-password"
            prefix-icon="el-icon-lock"
          ></el-input>
        </el-form-item>
        <el-form-item prop="checkPassword">
          <el-input
            v-model.trim="form.checkPassword"
            type="password"
            placeholder="确认密码"
            autocomplete="new-password"
            prefix-icon="el-icon-lock"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            class="resetPassword-btn"
            type="primary"
            @click.native.prevent="handleReset"
          >
            确认
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
  import { isPassword, isPhone } from "@/utils/validate";
  import { resetPassword ,getPhoneCode } from "@/api/user";
  import { isNumber } from "../../utils/validate";
  import Vue from "vue";
  export default {
    username: "resetPassword",
    directives: {
      focus: {
        inserted(el) {
          el.querySelector("input").focus();
        },
      },
    },
    data() {
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
      const validatePhoneCode = (rule, value, callback) => {
        if (value !== this.realPhoneCode) {
          callback(new Error("验证码不正确"));
        } else {
          callback();
        }
      };
      const validateCheckPassword = (rule, value, callback) => {
        if (value === "") {
          callback(new Error("请再次输入密码"));
        } else if (value !== this.form.password) {
          callback(new Error("两次输入密码不一致!"));
        } else {
          callback();
        }
      };
      return {
        isGetphone: false,
        getPhoneIntval: null,
        phoneCode: "获取验证码",
        realPhoneCode:"真实的验证码",
        showresetPassword: false,
        nodeEnv: process.env.NODE_ENV,
        title: this.$baseTitle,
        form: {
          identity: "顾客",
        },
        resetPasswordRules: {
          checkPassword: [
            {
              required: true,
              trigger: "blur",
              validator: validateCheckPassword,
            },
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
            {min: 1, max: 6, message: '验证码为6为数字', trigger: 'blur'},
            {validator: validatePhoneCode, trigger: "blur"},
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
    async  getPhoneCode() {
        console.log(isPhone(this.form.phone))
        if (isPhone(this.form.phone)) {
          this.isGetphone = true;
          let n = 60;
          console.log(this.form.phone)
          const { result } = await getPhoneCode(this.form.phone)
          this.realPhoneCode=result;
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
        }else {
          Vue.prototype.$baseMessage( `请先查手机号是否正确`, "error");
        }
      },
      handleReset() {
        this.$refs["resetPasswordForm"].validate(async (valid) => {
          if (valid) {
            const param = {
              identity: this.form.identity,
              phone: this.form.phone,
              password: this.form.password,
            };
            console.log(param);
            const msg = await resetPassword(param);
            if (msg==='success'){
              this.$baseMessage("修改成功", "success");
              this.$router.push("/login").catch(() => {});
            }else {
              this.$baseMessage(msg, "error");
            }

          }
        });
      },
    },
  };
</script>
<style >
    .resetPassword-container {
        width: 100%;
        height: 100vh;
        background: url("~assets/login_images/background.jpg") center center fixed no-repeat;
        background-size: cover;
        font-family: PingFang-SC-Regular, Helvetica, "Microsoft Yahei", 微软雅黑,
        serif;
    }

    .resetPassword-logo {
        position: absolute;
        top: 35px;
        left: 80px;
    }

    .resetPassword-cap {
        position: absolute;
        width: 470px;
        height: 520px;
        margin-top: calc((100vh - 550px) / 2);
        overflow: hidden;
        background-color: rgba(255, 255, 255, 0.9);
        right: 10%;
        border-radius: 10px 10px;

    }

    .resetPassword-title {
        font-size: 36px;
        font-weight: 600;
        color: rgba(14, 18, 26, 1);
        margin-top: 20px;
    }

    .resetPassword-title-tips {
        width: 150px;
        margin: 10px 0 10px 10px;
        font-size: 14px;
        color: #ec515f  ;
        position: relative;
        left: 250px;
    }


    .resetPassword-btn {
        display: inherit;
        width: 220px;
        height: 50px;
        margin: 20px auto;
        border: 0;
    }

    .resetPassword-btn:hover {
        opacity: 0.9;
    }


    .resetPassword-form {
        position: relative;
        width: 400px;
        height: 480px;
        margin: 20px 35px;
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
        right: 10px;
        text-align: center;
        /*top: -1px;*/
    }

    .resetPassword-container .el-input__inner {
        width: 95% ;
        margin-left: 10px;
        height: 45px;
        border-radius: 8px;
        font-size: 14px;
    }

    .resetPassword-container .el-input__prefix, .el-input__suffix {
        margin-left: 10px;
        margin-right: 10px;
    }

</style>

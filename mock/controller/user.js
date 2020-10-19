const accessTokens = {
  customer: "customer-accessToken",
  editor: "editor-accessToken",
  123456: "manager-accessToken",
  111111: "boss-accessToken",
};
const inf = [
  { type: "id", value: "754406394" },
  { type: "头像", value: "oasdsadsa.xx" },
  { type: "姓名", value: "张三" },
  { type: "性别", value: "男" },
  { type: "密码", value: "123465" },
  { type: "手机号", value: "13752114812" },
];
module.exports = [
  {
    url: "/publicKey",
    type: "post",
    response() {
      return {
        code: 200,
        msg: "success",
        data: {
          mockServer: true,
          publicKey:
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBT2vr+dhZElF73FJ6xiP181txKWUSNLPQQlid6DUJhGAOZblluafIdLmnUyKE8mMHhT3R+Ib3ssZcJku6Hn72yHYj/qPkCGFv0eFo7G+GJfDIUeDyalBN0QsuiE/XzPHJBuJDfRArOiWvH0BXOv5kpeXSXM8yTt5Na1jAYSiQ/wIDAQAB",
        },
      };
    },
  },
  {
    url: "/login",
    type: "post",
    response(config) {
      const { type } = config.body;
      let accessToken;
      if (type === "manager") {
        const { managerId } = config.body;
        accessToken = "0710-boss";
      } else {
        const { username } = config.body;
        accessToken = "0710-customer";
      }
      // if (!accessToken) {
      //   return {
      //     code: 500,
      //     msg: "用户不存在。",
      //   };
      // }
      return {
        code: 200,
        msg: "success",
        data: {
          accessToken,
          username: "Vetch",

        },
      };
    },
  },
  {
    url: "/register",
    type: "post",
    response(config) {
      const { phoneCode } = config.body;
      if (phoneCode === "111111") {
        return {
          code: 200,
          msg: "模拟注册成功",
        };
      }
      return {
        code: 500,
        msg: "验证码不正确。",
      };
    },
  },
  {
    url: "/resetPassword",
    type: "post",
    response(config) {
      const { phoneCode } = config.body;
      if (phoneCode === "111111") {
        return {
          code: 200,
          msg: "重置密码成功",
        };
      }
      return {
        code: 500,
        msg: "验证码不正确。",
      };
    },
  },
  {
    url: "/userInfo",
    type: "post",
    response(config) {
      const { accessToken } = config.body;
      // let permissions = ["manager"];
      // let permissions = ["customer"];
      let permissions = accessToken.substring(accessToken.indexOf("-")+1);
      let username = "admin";
      if ("customer" === permissions) {
        permissions = ["customer"];
        username = "customer";
      }
      if ("manager" === permissions) {
        permissions = ["manager"];
        username = "manager";
      }
      if ("boss" === permissions) {
        permissions = ["boss"];
        username = "boss";
      }
      return {
        code: 200,
        msg: "success",
        data: {
          permissions,
          username,
          "avatar|1": [
            "https://i.gtimg.cn/club/item/face/img/2/15922_100.gif",
            "https://i.gtimg.cn/club/item/face/img/8/15918_100.gif",
          ],
        },
      };
    },
  },
  {
    url: "/logout",
    type: "post",
    response() {
      return {
        code: 200,
        msg: "success",
      };
    },
  },
  {
    url: "/getPersonalInfo",
    type: "post",
    response() {
      return {
        code: 200,
        msg: "success",
        data: inf,
      };
    },
  },
  {
    url: "/upload",
    type: "post",
    response(config) {
      console.log(config.data);
      return {
        code: 200,
        msg: "success",
        data: inf,
      };
    },
  },
];

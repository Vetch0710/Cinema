/**
 * @author chuzhixin 1204505056@qq.com （不想保留author可删除）
 * @description 登录、获取用户信息、退出登录、清除accessToken逻辑，不建议修改
 */

import Vue from "vue";
import { getUserInfo, login, logout } from "@/api/user";
import {
  getAccessToken,
  removeAccessToken,
  setAccessToken,
} from "@/utils/accessToken";
import { resetRouter } from "@/router";
import { title, tokenName } from "@/config/settings";

const state = {
  accessToken: getAccessToken(),
  username: "",
  avatar: "",
  permissions: [],
};
const getters = {
  accessToken: (state) => state.accessToken,
  username: (state) => state.username,
  avatar: (state) => state.avatar,
  permissions: (state) => state.permissions,
};
const mutations = {
  setAccessToken(state, accessToken) {
    state.accessToken = accessToken;
    setAccessToken(accessToken);
  },
  setUsername(state, username) {
    state.username = username;
  },
  setAvatar(state, avatar) {
    state.avatar = avatar;
  },
  setPermissions(state, permissions) {
    state.permissions = permissions;
  },
};
const actions = {
  setPermissions({ commit }, permissions) {
    commit("setPermissions", permissions);
  },
  async login({ commit }, userInfo) {
    const { data } = await login(userInfo); //给后端接口发送用户信息
    const accessToken = data[tokenName]; //获取token
    console.log(accessToken + "===============");
    if (accessToken) {
      commit("setAccessToken", accessToken);
      const hour = new Date().getHours();
      const thisTime =
          hour < 8
              ? "早上好"
              : hour <= 11
              ? "上午好"
              : hour <= 13
                  ? "中午好"
                  : hour < 18
                      ? "下午好"
                      : "晚上好";
      Vue.prototype.$baseNotify(`欢迎登录`, `${thisTime}！`);
    } else {
      Vue.prototype.$baseMessage(
          `后台返回信息异常，未正确返回${tokenName}...`,
          "error"
      );
    }
  },
  async getUserInfo({ commit, state }) {
    const { data } = await getUserInfo(state.accessToken);
    if (!data) {
      Vue.prototype.$baseMessage("验证失败，请重新登录...", "error");
      return false;
    }
    let { permissions, username, avatar } = data;
    console.log(typeof permissions)
    if (permissions && username && Array.isArray(permissions)) {
      commit("setPermissions", permissions);
      commit("setUsername", username);
      commit("setAvatar", avatar);
      return permissions;
    } else {
      Vue.prototype.$baseMessage("后台返回用户信息格式异常", "error");
      return false;
    }
  },
  async logout({ dispatch }) {
    await logout(state.accessToken);
    await dispatch("resetAccessToken");
    await resetRouter();
  },
  resetAccessToken({ commit }) {
    commit("setUsername", "");
    commit("setPermissions", []);
    commit("setAccessToken", "");
    removeAccessToken();
  },
};
export default { state, getters, mutations, actions };

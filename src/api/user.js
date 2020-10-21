import request from "@/utils/request";
import {  tokenName } from "@/config/settings";

export async function login(data) {
  console.log("sdsdsd")
  return request({
    url: "/login",
    method: "post",
    data,
  });
}

export function getUserInfo(accessToken) {
  return request({
    url: "/userInfo",
    method: "post",
    data: {
      [tokenName]: accessToken,
    },
  });
}

export function logout() {
  return request({
    url: "/logout",
    method: "post",
  });
}
export function getPersonalInfo(data) {
  return request({
    url: "/getPersonalInfo",
    method: "post",
    data
  });
}

export function register(data) {
  return request({
    url: "/register",
    method: "post",
    data,
  });
}
export function resetPassword(data) {
  return request({
    url: "/resetPassword",
    method: "post",
    data,
  });
}

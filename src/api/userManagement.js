import request from "@/utils/request";

export function getList(data) {
  return request({
    url: "/UserInfo/getList",
    method: "get",
    params:data,
  });
}
export function getInfo() {
  return request({
    url: "/UserInfo/getInfo",
    method: "get",
  });
}

export function doEditCus(data) {
  return request({
    url: "/UserInfo/doEditCus",
    method: "post",
    data,
  });
}
export function doEditMan(data) {
  return request({
    url: "/UserInfo/doEditMan",
    method: "post",
    data,
  });
}
export function doEdit(data) {
  return request({
    url: "/UserInfo/doEdit",
    method: "post",
    data,
  });
}


export function doDelete(data) {
  console.log(data)
  return request({
    url: "/UserInfo/doDelete",
    method: "post",
    data
  });
}
export function getPersonalInfo() {
  return request({
    url: "/UserInfo/getPersonalInfo",
    method: "get",
  });
}

export function getMaxNum() {
  return request({
    url: "/UserInfo/getMaxNum",
    method: "get",
  });
}

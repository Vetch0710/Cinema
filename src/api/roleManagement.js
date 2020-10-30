import request from "@/utils/request";

export function getList(data) {
  return request({
    url: "/UserInfo/getList",
    method: "get",
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
  return request({
    url: "/UserInfo/doDelete",
    method: "post",
    data,
  });
}

export function getMaxNum() {
  return request({
    url: "/UserInfo/getMaxNum",
    method: "get",
  });
}

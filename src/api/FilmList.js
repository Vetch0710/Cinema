import request from "@/utils/request";

export function getList(data) {
  return request({
    url: "/filmList/getList",
    method: "post",
    data,
  });
}
export function getRecommendList() {
  return request({
    url: "/filmList/getRecommendList",
    method: "post",
  });
}

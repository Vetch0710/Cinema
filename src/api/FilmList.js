import request from "@/utils/request";

export function getList(data) {
  return request({
    url: "/filmList/getList",
    method: "post",
    data,
  });
}

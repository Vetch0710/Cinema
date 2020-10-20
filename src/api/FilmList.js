import request from "@/utils/request";

export function getList(data) {
  console.log(data)
  return request({
    url: "/filmList/getList",
    method: "post",
    data,
  });
}
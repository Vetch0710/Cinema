import request from "@/utils/request";

export function getInfo(data) {
  console.log(data)
  return request({
    url: "/film/getInfo",
    method: "post",
    data,
  });
}
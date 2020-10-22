import request from "@/utils/request";

export function getList(data) {
  console.log(data)
  return request({
    url: "/evaluationList/getList",
    method: "post",
    data,
  });
}
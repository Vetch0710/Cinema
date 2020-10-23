import request from "@/utils/request";

export function selectSession(data) {
  console.log(data)
  return request({
    url: "/arrangement/selectSession",
    method: "get",
    data,
  });
}
export function arrangementInfo(data) {
  console.log(data)
  return request({
    url: "/arrangement/arrangementInfo",
    method: "get",
    data,
  });
}

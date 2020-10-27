import request from "@/utils/request";

export function selectSession(data) {
  console.log(data)
  return request({
    url: "/arrangement/selectSession",
    method: "post",
    data,
  });
}
export function arrangementInfo(data) {
  console.log(data)
  return request({
    url: "/arrangement/arrangementInfo",
    method: "post",
    data,
  });
}
export function getList(data) {
  console.log(data)
  return request({
    url: "/arrangement/arrangeList",
    method: "get",
    params: {
      pageNo: data.pageNo,
      pageSize: data.pageSize,
      selectType: data.selectType,
      permission: data.permission,
    },
  });
}

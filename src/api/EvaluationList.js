import request from "@/utils/request";

export function getList(data) {
  console.log(data)
  return request({
    url: "/evaluation/evaluationList",
    method: "get",
    params: {
      pageNo: data.pageNo,
      pageSize: data.pageSize,
      selectType: data.selectType,
      permission: data.permission,
    },
  });
}
export function getCusEva() {
  return request({
    url: "/evaluationList/getCusEva",
    method: "post",
  });
}
export function saveEvaluation(data) {
  return request({
    url: "/evaluationList/saveEvaluation",
    method: "post",
    data
  });
}
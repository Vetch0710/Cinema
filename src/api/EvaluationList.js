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
export function getMovieEvaluation(data) {
  console.log(data)
  return request({
    url: "/evaluation/getMovieEvaluation",
    method: "get",
    params: {
      pageNo: data.pageNo,
      pageSize: data.pageSize,
      movieId: data.movieId
    },
  });
}
export function getEvaluation() {
  return request({
    url: "/evaluation/getEvaluation",
    method: "get",
  });
}
export function saveEvaluation(data) {
  return request({
    url: "/evaluation/saveEvaluation",
    method: "post",
    data
  });
}

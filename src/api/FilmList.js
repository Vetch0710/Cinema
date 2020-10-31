import request from "@/utils/request";

export function getList(data) {
  return request({
    url: "/movie/movieList",
    method: "get",
    params: {
      pageNo: data.pageNo,
      pageSize: data.pageSize,
      selectType: data.selectType,
      permission: data.permission,
    },
  });
}
export function indexList(data) {
  return request({
    url: "/movie/indexList",
    method: "get",
    params: {
      pageNo: data.pageNo,
      pageSize: data.pageSize,
      selectType: data.selectType,
      timeType: data.timeType,
    },
  });
}


export function getRecommendList() {
  return request({
    url: "/filmList/getRecommendList",
    method: "post",
  });
}

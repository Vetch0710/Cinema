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

export function addArrangement(form) {
  return request({
    url: "/arrangement/addArrangement",
    method: "post",
    data: {
      arrangement: form,
    }
  });
}
export function doEdit(data) {
  return request({
    url: "/arrangement/edit",
    method: "put",
    data,
  });
}

export function doDelete(form) {
  return request({
    url: "/arrangement/" + form.arrangementId,
    method: "delete",
  });
}

export function doDeletes(data) {
  console.log("=============");
  console.log(data.arrangementIds);
  return request({
    url: "/arrangement/batchDelete/" + data.arrangementIds.join("&"),
    method: "delete",
  });
}

export function getDisableTime(form) {
  return request({
    url: "/arrangement/" + form.arrangementPlace + "/" + form.arrangementDate+"/"+form.movieId,
    method: "get",
    
  });
}

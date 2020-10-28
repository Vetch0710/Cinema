import request from "@/utils/request";
import axios from "axios";
// import instance from "../utils/request";



// export function getMovieList(queryForm) {
//    return instance.get("/movie/movieList", {
//       params: queryForm
//    })
// }
export function getMovieList(data) {
  console.log("============");
  console.log(data);
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
export function addMovie(form) {
  return request({
    url: "/movie//addMovie",
    method: "post",
    data: {
      movie: form,
    }
  });
}


export function doDelete(data) {
  return request({
    url: "/movie/" + data.movieId,
    method: "delete",
  });
}
export function doDeletes(data) {
  return request({
    url: "/movie/batchDelete/" + data.movieIds.join("&"),
    method: "delete",
  });
}
export function doEdit(data) {
  return request({
    url: "/movie/edit",
    method: "put",
    data,
  });
}

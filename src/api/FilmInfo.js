import request from "@/utils/request";

export function getInfo(data) {
  console.log(data)
  return request({
    url: "/movie/" + data.movieId,
    method: "get",
  });
}

export function getRelativeMovie(data) {
  console.log(data)
  return request({
    url: "/movie/getRelativeMovie",
    method: "get",
    params: {
      movieType: data.movieType,
      movieActor: data.movieActor,
      movieDirector: data.movieDirector
    }
  });
}

export function getFlag(data) {
  console.log(data)
  return request({
    url: "/movie/getFlag/" + data.movieId,
    method: "get",
  });
}
export function changeWant(data) {
  console.log(data)
  return request({
    url: "/movie/changeWant/" + data.wantsFlag + "/" + data.movieId,
    method: "put",
  });
}

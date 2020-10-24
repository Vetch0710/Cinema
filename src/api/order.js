import request from "@/utils/request";

export function getOrderList(data) {
  return request({
    url: "/Order/getOrderList",
    method: "post",
    data,
  });
}
export function getOrder(data) {
  return request({
    url: "/Order/getOrder",
    method: "post",
    data,
  });
}

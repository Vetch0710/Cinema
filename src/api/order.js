import request from "@/utils/request";

export function getOrderList(data) {
  return request({
    url: "/Order/getOrderList",
    method: "get",
    data,
  });
}
export function getOrderInfo(data) {
  return request({
    url: "/order/getOrderInfo/"+data.orderId,
    method: "get",
  });
}
export function getCusOrder(data) {
  return request({
    url: "/Order/getCusOrder",
    method: "post",
    data,
  });
}
export function generateOrder(data) {
  return request({
    url: "/order/generateOrder",
    method: "post",
    data: {
      sqlOrder: data.sqlOrder,
      selectedSeat: data.selectedSeat,
    }
  });
}

import request from "@/utils/request";

export function getOrderList(data) {
  return request({
    url: "/order/getOrderList",
    method: "get",
    params:data,
  });
}
export function getOrderInfo(data) {
  return request({
    url: "/order/getOrderInfo/"+data.orderId,
    method: "get",
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

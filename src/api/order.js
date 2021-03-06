import request from "@/utils/request";

export function getOrderList(data) {
  return request({
    url: "/order/getOrderList",
    method: "get",
    params: data,
  });
}
export function getOrderInfo(data) {
  return request({
    url: "/order/" + data.orderId,
    method: "get",
  });
}

export function generateOrder(data) {
  return request({
    url: "/order/generateOrder",
    method: "post",
    data: {
      sqlOrder: data.sqlOrder,
      // arrangementId: data.sqlOrder.arrangementId,
      // customerId: data.sqlOrder.customerId,
      // movieId: data.sqlOrder.movieId,
      // orderPrice: data.sqlOrder.orderPrice,
      selectedSeat: data.selectedSeat,
    }
  });
}

export function jumpToPay(data) {
  return request({
    url: "/alipay",
    method: "post",
    data,
  });
}

export function getOrderStatus(data) {
  return request({
    url: "/order/getOrderStatus/" + data,
    method: "get",
  });
}

export function delOrder(data) {
  return request({
    url: "/order/" + data,
    method: "delete",
  });
}

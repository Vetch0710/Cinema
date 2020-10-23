import request from "@/utils/request";

export function getOrderList(data) {
    return request({
        url: "/Order/getOrderList",
        method: "post",
        data,
    });
}
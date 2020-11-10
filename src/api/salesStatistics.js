import request from "@/utils/request";

export async function getStatistics(data) {
    console.log("获取销售额++++data:+++"+data)
    return request({
        url: "/getStatistics",
        method: "get",
        params:data,
    });
}export async function getStatisticsYear(data) {
    console.log("获取销售额++++data:+++"+data)
    return request({
        url: "/getStatisticsYear",
        method: "get",
        params:data,
    });
}

package com.Cinema_Management_System.Order.entity;

/**
 * @Description 订单信息实体类
 * @Auther GaoYi
 * @Date 2020/10/19 1:35 下午
 * * @param orderId         订单编号
 * * @param movieId         影片编号
 * * @param customerId      顾客id
 * * @param arrangementId   场次id
 * * @param seatId          座位号
 * * @param orderStatus     订单状态(待支付/已支付/待评价/已完成)
 */
public class Order {
    private int orderId, movieId, customerId, arrangementId, seatId;
    private String orderStatus;

    public Order() {
    }

    public Order(int orderId, int movieId, int customerId, int arrangementId, int seatId, String orderStatus) {
        this.orderId = orderId;
        this.movieId = movieId;
        this.customerId = customerId;
        this.arrangementId = arrangementId;
        this.seatId = seatId;
        this.orderStatus = orderStatus;
    }
}

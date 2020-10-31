package com.Cinema_Management_System.Order.entity;

import java.util.Date;

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
    private long orderId;
    private int arrangementId;
    private float orderPrice;
    private String orderStatus, orderSeat;
    private Date orderTime;

    public Order() {
    }

    public Order(long orderId, int arrangementId, float orderPrice, String orderStatus, String orderSeat, Date orderTime) {
        this.orderId = orderId;
        this.arrangementId = arrangementId;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
        this.orderSeat = orderSeat;
        this.orderTime = orderTime;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(int arrangementId) {
        this.arrangementId = arrangementId;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderSeat() {
        return orderSeat;
    }

    public void setOrderSeat(String orderSeat) {
        this.orderSeat = orderSeat;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", arrangementId=" + arrangementId +
                ", orderPrice=" + orderPrice +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderSeat='" + orderSeat + '\'' +
                ", orderTime=" + orderTime +
                '}';
    }
}

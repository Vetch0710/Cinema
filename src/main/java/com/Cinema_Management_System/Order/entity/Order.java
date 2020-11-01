package com.Cinema_Management_System.Order.entity;

import lombok.Data;
import lombok.experimental.Accessors;

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
@Data
@Accessors(chain = true)
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

}

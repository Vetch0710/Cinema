package com.Cinema_Management_System.Order.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/30 1:46 下午
 */
@Data
@Accessors(chain = true)
public class PayOrder extends Order {
    private int arrangementPlace;
    private String movieName;
    private Date arrangementTime;

    public PayOrder() {
    }

    public PayOrder(long orderId, int arrangementId, float orderPrice, String orderStatus, String orderSeat, Date orderTime, int arrangementPlace, String movieName, Date arrangementTime) {
        super(orderId, arrangementId, orderPrice, orderStatus, orderSeat, orderTime);
        this.arrangementPlace = arrangementPlace;
        this.movieName = movieName;
        this.arrangementTime = arrangementTime;
    }


}

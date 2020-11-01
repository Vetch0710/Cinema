package com.Cinema_Management_System.Order.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/30 1:55 下午
 */
@Data
@Accessors(chain = true)
public class SqlOrder extends Order {
    private int customerId, movieId;

    public SqlOrder() {
    }

    public SqlOrder(long orderId, int arrangementId, float orderPrice, String orderStatus, String orderSeat, Date orderTime, int customerId, int movieId) {
        super(orderId, arrangementId, orderPrice, orderStatus, orderSeat, orderTime);
        this.customerId = customerId;
        this.movieId = movieId;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SqlOrder) {
            SqlOrder order1 = (SqlOrder) obj;
            String[] seats = order1.getOrderSeat().split(",");
            for (String seat : seats) {
                if (seat.indexOf(this.getOrderSeat()) != -1) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}

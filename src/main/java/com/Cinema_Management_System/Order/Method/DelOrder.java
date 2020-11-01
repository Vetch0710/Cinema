package com.Cinema_Management_System.Order.Method;

import com.Cinema_Management_System.Order.dao.OrderDao;

import java.util.concurrent.Callable;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/31 4:34 下午
 */
public class DelOrder implements Callable<String> {
    private long orderId;
    private OrderDao orderDao;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public DelOrder(long orderId, OrderDao orderDao) {
        this.orderId = orderId;
        this.orderDao = orderDao;
    }

    @Override
    public String call() throws Exception {
        System.out.println("订单"+orderId+"超时,已被删除");
        int row = orderDao.delOrder(orderId);
        String message = "";
        if (row > 0) {
            message = "删除成功!";
        } else {
            message = "订单不存在!";
        }
        return message;
    }
}

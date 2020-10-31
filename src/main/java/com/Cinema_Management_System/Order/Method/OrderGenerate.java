package com.Cinema_Management_System.Order.Method;


import com.Cinema_Management_System.Order.dao.OrderDao;
import com.Cinema_Management_System.Order.entity.SqlOrder;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/30 11:20 上午
 */
public class OrderGenerate implements Callable<String> {
    private OrderDao orderDao;
    private List<String> selectedSeats;
    private SqlOrder sqlOrder;

    public OrderGenerate(OrderDao orderDao, List<String> selectedSeats, SqlOrder sqlOrder) {
        this.orderDao = orderDao;
        this.selectedSeats = selectedSeats;
        this.sqlOrder = sqlOrder;
    }

    public String generateOrder() throws Exception {
        synchronized (sqlOrder) {
            if (orderDao.soldSeats(selectedSeats, sqlOrder.getArrangementId()) == 0) {
                orderDao.generateOrder(sqlOrder);
                return "success";
            } else {
                return "error";
            }
        }
    }


    @Override
    public String call() throws Exception {
        String message = generateOrder();
        return message;
    }
}

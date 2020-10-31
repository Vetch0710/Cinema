package com.Cinema_Management_System.Order.service.serviceimpl;

import com.Cinema_Management_System.Order.Method.OrderGenerate;
import com.Cinema_Management_System.Order.dao.OrderDao;
import com.Cinema_Management_System.Order.entity.PayOrder;
import com.Cinema_Management_System.Order.entity.SqlOrder;
import com.Cinema_Management_System.Order.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Transactional(rollbackFor = Exception.class)
@Service(value = "OrderService")
public class OrderServiceImpl implements OrderService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final int cpuNum = Runtime.getRuntime().availableProcessors();
    @Autowired
    private OrderDao orderDao;
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> generateOrder(SqlOrder sqlOrder, String[] selectedSeat) throws ExecutionException, InterruptedException {
        Date orderTime = new Date();
        long orderId = orderTime.getTime();
        sqlOrder.setOrderId(orderId);
        sqlOrder.setOrderStatus("待支付");
        sqlOrder.setOrderSeat(StringUtils.join(selectedSeat, ","));
        sqlOrder.setOrderTime(orderTime);
        List<String> selectedSeats = Arrays.asList(selectedSeat);
        OrderGenerate orderGenerate = new OrderGenerate(orderDao, selectedSeats, sqlOrder);
        Future<String> future = executorService.submit(orderGenerate);
        String result = future.get();
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("message", result);
        if (result.equals("success")) {
            resultData.put("orderId", orderId);
        }
        return resultData;
    }

    @Override
    public PayOrder getOrderInfo(long orderId) {
        return orderDao.getOrderInfo(orderId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyStatus(long orderId, String status) throws Exception {
        int row = orderDao.modifyStatus(orderId, status);
        if (row == 0) {
            throw new Exception("支付异常，请重新支付!");
        }
    }

//    public New getById(long bookId) {
//        return bookDao.queryById(bookId);
//    }
}

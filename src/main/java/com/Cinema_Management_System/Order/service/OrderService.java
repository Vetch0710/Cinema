package com.Cinema_Management_System.Order.service;

import com.Cinema_Management_System.Order.entity.PayOrder;
import com.Cinema_Management_System.Order.entity.SqlOrder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public interface OrderService {
    //Book getById(long bookId);
    Map<String, Object> generateOrder(SqlOrder sqlOrder, String[] selectedSeat) throws ExecutionException, InterruptedException;

    PayOrder getOrderInfo(long orderId);

    void modifyStatus(long orderId, String status) throws Exception;

    String getOrderStatus(long orderId);
    //获取用户订单/全部订单
    Map<String, Object> getAllOrder(Integer id, String identity, String type,Integer pageNo,Integer pageSize,String selectType,String selectValues) ;

}

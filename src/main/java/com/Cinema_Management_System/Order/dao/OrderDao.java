package com.Cinema_Management_System.Order.dao;

import com.Cinema_Management_System.Order.entity.PayOrder;
import com.Cinema_Management_System.Order.entity.SqlOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {
    //Book queryById(@Param("book_id") long book_id);
    void generateOrder(SqlOrder sqlOrder);

    PayOrder getOrderInfo(@Param("orderId") long orderId);

    int soldSeats(@Param("selectedSeats") List<String> selectedSeats, @Param("arrangementId") int arrangementId);

    int modifyStatus(@Param("orderId") long orderId,@Param("status") String status);
}

package com.Cinema_Management_System.Order.dao;

import com.Cinema_Management_System.Order.entity.PayOrder;
import com.Cinema_Management_System.Order.entity.SqlOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    //Book queryById(@Param("book_id") long book_id);
    int generateOrder(SqlOrder sqlOrder);

    PayOrder getOrderInfo(@Param("orderId") long orderId);

    int soldSeats(@Param("selectedSeats") List<String> selectedSeats, @Param("arrangementId") int arrangementId);

    int modifyStatus(@Param("orderId") long orderId, @Param("status") String status);

    int delOrder(@Param("orderId") long orderId);

    String getOrderStatus(@Param("orderId") long orderId);
}

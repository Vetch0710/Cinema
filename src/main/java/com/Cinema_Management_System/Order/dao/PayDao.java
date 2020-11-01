package com.Cinema_Management_System.Order.dao;

import com.Cinema_Management_System.Order.entity.PayOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/31 10:53 下午
 */
@Repository
public interface PayDao {
    PayOrder getOrderInfo(@Param("orderId") long orderId);

}

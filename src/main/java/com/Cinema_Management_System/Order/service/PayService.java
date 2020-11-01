package com.Cinema_Management_System.Order.service;

import com.Cinema_Management_System.Order.entity.PayOrder;
import org.springframework.stereotype.Service;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/31 10:54 下午
 */
@Service
public interface PayService {
    PayOrder getOrderInfo(long orderId);

}

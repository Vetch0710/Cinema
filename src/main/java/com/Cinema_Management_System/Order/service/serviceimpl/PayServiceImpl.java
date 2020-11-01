package com.Cinema_Management_System.Order.service.serviceimpl;

import com.Cinema_Management_System.Order.dao.PayDao;
import com.Cinema_Management_System.Order.entity.PayOrder;
import com.Cinema_Management_System.Order.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/31 10:54 下午
 */

@Service(value = "PayService")
public class PayServiceImpl implements PayService {
    @Autowired
    private PayDao payDao;

    @Override
    public PayOrder getOrderInfo(long orderId) {
        return payDao.getOrderInfo(orderId);
    }
}

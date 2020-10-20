package com.Cinema_Management_System.Order.service.serviceimpl;

import com.Cinema_Management_System.Order.dao.OrderDao;
import com.Cinema_Management_System.Order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service(value = "OrderService")
public class OrderServiceImpl implements OrderService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderDao orderDao;

//    public New getById(long bookId) {
//        return bookDao.queryById(bookId);
//    }
}

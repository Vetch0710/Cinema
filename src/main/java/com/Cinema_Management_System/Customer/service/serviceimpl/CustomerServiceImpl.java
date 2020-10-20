package com.Cinema_Management_System.Customer.service.serviceimpl;

import com.Cinema_Management_System.Customer.dao.CustomerDao;
import com.Cinema_Management_System.Customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service(value = "CustomerService")
public class CustomerServiceImpl implements CustomerService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CustomerDao customerDao;

//    public New getById(long bookId) {
//        return bookDao.queryById(bookId);
//    }
}

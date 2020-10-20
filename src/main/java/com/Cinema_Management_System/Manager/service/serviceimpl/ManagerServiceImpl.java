package com.Cinema_Management_System.Manager.service.serviceimpl;

import com.Cinema_Management_System.Manager.dao.ManagerDao;
import com.Cinema_Management_System.Manager.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service(value = "ManagerService")
public class ManagerServiceImpl implements ManagerService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ManagerDao managerDao;

//    public New getById(long bookId) {
//        return bookDao.queryById(bookId);
//    }
}

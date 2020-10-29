package com.Cinema_Management_System.Inform.serviceimpl;

import com.Cinema_Management_System.Inform.dao.InformDao;
import com.Cinema_Management_System.Inform.service.InformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service(value = "InformService")
public class InformServiceImpl implements InformService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InformDao informDao;

//    public New getById(long bookId) {
//        return bookDao.queryById(bookId);
//    }
}

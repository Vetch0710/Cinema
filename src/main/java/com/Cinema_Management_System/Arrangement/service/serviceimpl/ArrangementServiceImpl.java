package com.Cinema_Management_System.Arrangement.service.serviceimpl;

import com.Cinema_Management_System.Arrangement.dao.ArrangementDao;
import com.Cinema_Management_System.Arrangement.entity.Arrangement;
import com.Cinema_Management_System.Arrangement.service.ArrangementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service(value = "ArrangementService")
public class ArrangementServiceImpl implements ArrangementService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ArrangementDao arrangementDao;


    public Arrangement queryAll() {
        return arrangementDao.queryAll();
    }
}

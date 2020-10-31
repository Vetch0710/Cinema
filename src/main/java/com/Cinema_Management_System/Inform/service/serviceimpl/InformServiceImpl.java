package com.Cinema_Management_System.Inform.service.serviceimpl;


import com.Cinema_Management_System.Inform.dao.InformDao;
import com.Cinema_Management_System.Inform.entity.Inform;
import com.Cinema_Management_System.Inform.service.InformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service(value = "InformService")
public class InformServiceImpl implements InformService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InformDao informDao;
    @Override
    public List<Inform> selectAllInfo(int customerId) {
        return  informDao.selectAllInfo(customerId);
    }

    @Override
    public List<Inform> selectInfoByType() {
        return informDao.selectInfoByType();
    }

    @Override
    public int addInfo(Inform message) {
        return   informDao.addInfo(message);
    }

    @Override
    public int updateStatus(int customerId) {
        return informDao.updateStatus(customerId);
    }

    @Override
    public int updateStatusById(int infoId) {
        return informDao.updateStatusById(infoId);
    }

    @Override
    public String selectCustomerName(int customerId) {
        return informDao.selectCustomerName(customerId);
    }
}

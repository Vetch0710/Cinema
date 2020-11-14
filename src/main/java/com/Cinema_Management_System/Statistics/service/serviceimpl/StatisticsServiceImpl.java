package com.Cinema_Management_System.Statistics.service.serviceimpl;

import com.Cinema_Management_System.Statistics.dao.StatisticsDao;
import com.Cinema_Management_System.Statistics.entity.Statistics;
import com.Cinema_Management_System.Statistics.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service(value = "ManagerService")
public class StatisticsServiceImpl implements StatisticsService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StatisticsDao statisticsDao;

    @Override
    public List<Float> selectStaByMonth(String day) {
        return statisticsDao.selectStaByMonth(day);
    }

    @Override
    public List<Float> selectStaByYear(String year) {
        return statisticsDao.selectStaByYear(year);
    }

//    public New getById(long bookId) {
//        return bookDao.queryById(bookId);
//    }
}

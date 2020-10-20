package com.Cinema_Management_System.Evaluation.service.serviceimpl;

import com.Cinema_Management_System.Evaluation.dao.EvaluationDao;
import com.Cinema_Management_System.Evaluation.service.EvaluationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service(value = "EvaluationService")
public class EvaluationServiceImpl implements EvaluationService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluationDao evaluationDao;

//    public New getById(long bookId) {
//        return bookDao.queryById(bookId);
//    }
}

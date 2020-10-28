package com.Cinema_Management_System.Evaluation.controller;

import com.Cinema_Management_System.Evaluation.entity.Evaluation;
import com.Cinema_Management_System.Evaluation.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/evaluation")
@RestController
public class EvaluationController {

    @Qualifier("EvaluationService")
    @Autowired
    private EvaluationService evaluationService;
    private List<Evaluation> evaluationList;
    private Map<String,Object> returnData;
    @RequestMapping(value = "/evaluationList",method = RequestMethod.GET)
    public Map<String, Object> getEvaluations(String selectType, String permission, Integer pageNo, Integer pageSize) {
        try {
            permission = new String(permission.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {

        }
        Integer pageStart = (pageNo - 1) * pageSize;
        System.out.println("===================" + pageNo + "=============" + pageSize + "=============" + permission);
        evaluationList = evaluationService.getEvaluations(selectType, permission, pageStart, pageSize);
        int count = evaluationService.getCount(selectType, permission);
        returnData = new HashMap<>();
        returnData.put("data", evaluationList);
        returnData.put("total", count);
        return returnData;
    }
}

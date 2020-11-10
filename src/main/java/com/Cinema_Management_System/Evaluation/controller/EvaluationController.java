package com.Cinema_Management_System.Evaluation.controller;

import com.Cinema_Management_System.Evaluation.entity.Evaluation;
import com.Cinema_Management_System.Evaluation.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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
    private Map<String, Object> returnData;

    @RequestMapping(value = "/evaluationList", method = RequestMethod.GET)
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

    @RequestMapping(value = "/getMovieEvaluation", method = RequestMethod.GET)
    public Map<String, Object> getMovieEvaluation(int movieId, Integer pageNo, Integer pageSize) {
        Integer pageStart = (pageNo - 1) * pageSize;
        returnData = new HashMap<>();
        evaluationList = evaluationService.getMovieEvaluation(movieId, pageStart, pageSize);
        returnData.put("data", evaluationList);
        returnData.put("totalCount", evaluationService.getMovieEvaluationCount(movieId));
        return returnData;
    }

    @RequestMapping(value = "/getEvaluation", method = RequestMethod.GET)
    public List<Evaluation> getEvaluation(@RequestHeader("accessToken") String accessToken) {
        String identity = accessToken.substring(accessToken.indexOf("-") + 1);
        int userId = Integer.parseInt(accessToken.substring(0, accessToken.indexOf("-")));
        List<Evaluation> allCusEvaluations = evaluationService.getAllCusEvaluations(userId);
        return allCusEvaluations;
    }

    @RequestMapping(value = "/saveEvaluation", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String saveEvaluation(@RequestBody Evaluation evaluation) {
        System.out.println(evaluation);
        if (evaluationService.updateEvaluation(evaluation)) {
            return "success";
        }

        return "评价失败，请检查您的信息是否正确";
    }

    @RequestMapping(value = "/{evaluationId}",method = RequestMethod.DELETE)
    public String delEavluation(@PathVariable int evaluationId){
        String message = evaluationService.delEavluation(evaluationId);
        return message;
    }

}

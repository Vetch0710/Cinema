package com.Cinema_Management_System.Evaluation.controller;

import com.Cinema_Management_System.Evaluation.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EvaluationController {

    @Qualifier("EvaluationService")
    @Autowired
    private EvaluationService evaluationService;

    @RequestMapping("login3")
    public void test() throws Exception {

    }
}

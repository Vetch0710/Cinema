package com.Cinema_Management_System.Arrangement.controller;

import com.Cinema_Management_System.Arrangement.service.ArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArrangeController {

    @Qualifier(value = "ArrangementService")
    @Autowired
    private ArrangementService ArrangementService;

    @RequestMapping("login1")
    public void test() throws Exception {

    }
}

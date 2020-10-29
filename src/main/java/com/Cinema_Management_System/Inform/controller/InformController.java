package com.Cinema_Management_System.Inform.controller;

import com.Cinema_Management_System.Inform.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InformController {

    @Qualifier("InformService")
    @Autowired
    private InformService informService;

    @RequestMapping("login4")
    public void test() throws Exception {

    }
}

package com.Cinema_Management_System.Manager.controller;

import com.Cinema_Management_System.Manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerController {

    @Qualifier("ManagerService")
    @Autowired
    private ManagerService managerService;

    @RequestMapping("login5")
    public void test() throws Exception {

    }
}

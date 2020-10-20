package com.Cinema_Management_System.Customer.controller;

import com.Cinema_Management_System.Customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

    @Qualifier("CustomerService")
    @Autowired
    private CustomerService customerService;

    @RequestMapping("login2")
    public void test() throws Exception {

    }
}

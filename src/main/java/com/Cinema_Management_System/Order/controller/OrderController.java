package com.Cinema_Management_System.Order.controller;

import com.Cinema_Management_System.Order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

    @Qualifier("OrderService")
    @Autowired
    private OrderService orderService;

    @RequestMapping("login6")
    public void test() throws Exception {

    }
}

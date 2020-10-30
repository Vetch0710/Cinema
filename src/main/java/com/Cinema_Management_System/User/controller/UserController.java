package com.Cinema_Management_System.User.controller;


import com.Cinema_Management_System.User.entity.Customer;
import com.Cinema_Management_System.User.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Qualifier("UserService")
    @Autowired
    private UserService userService;

    //登陆
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, String> login(HttpServletRequest req) throws Exception {
        System.out.println("**************login***************");

        Map<String, String> result = new HashMap<>();
        String loginType = req.getParameter("logintype");
        String type = req.getParameter("type");
        String token = "fail";
        if ("userName".equals(loginType)) {
            if ("customer".equals(type)) {
                String username = new String(req.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
                token = userService.loginByName(username, null, req.getParameter("password"), type);
            } else {
                token = userService.loginByName(null, Integer.valueOf(req.getParameter("managerId")), req.getParameter("password"), type);
            }

        } else {
            token = userService.loginByPhone(req.getParameter("phone"), type);
        }
        if (!"fail".equals(token)) {
            result.put("accessToken", token);
        } else {
            result.put("result", token);

        }
        System.out.println(result);
        return result;
    }

    //获取验证码
    @RequestMapping(value = "/getPhoneCode", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, String> getPhoneCode(String phone) throws Exception {
        System.out.println("**************getPhoneCode***************");

        Map<String, String> result = new HashMap<>();

        int phoneCode = 0;
        if (phone != null) {
            phoneCode = userService.getPhoneCode(phone);
        }
        result.put("result", String.valueOf(phoneCode));
        return result;
    }

    //获取个人信息----姓名，头像，权限
    @RequestMapping(value = "/userInfo", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> userInfo(@RequestHeader("accessToken") String accessToken) throws Exception {
        System.out.println("**************userInfo***************");
        System.out.println("accessToken-------" + accessToken);

        String identity = accessToken.substring(accessToken.indexOf("-") + 1);
        int userId = Integer.parseInt(accessToken.substring(0, accessToken.indexOf("-")));
        Map<String, Object> result = new HashMap<>();
        List<String> permissions = new ArrayList<>();
        permissions.add(identity);
        if ("customer".equals(identity)) {
            result = userService.getInfoById(userId, null);
        } else {
            result = userService.getInfoById(null, userId);
        }
        result.put("permissions", permissions);
        return result;
    }


    //退出登陆
    @RequestMapping(value = "/logout")
    @ResponseBody
    public String logout() throws Exception {
        System.out.println("**************logout***************");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "success");

        return jsonObject.toJSONString();
    }

    //注册
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String register(@RequestBody Customer customer) throws Exception {
        System.out.println("**************register***************");

        if (!userService.isExistCustomerName(customer.getCustomerName(), customer.getCustomerPhone())) {
            if (userService.register(customer)) {
                return "success";
            }
        } else {
            return "该用户已注册，请登录";
        }
        System.out.println(customer);

        return "注册失败，请重新注册";
    }


    //重置密码
    @RequestMapping(value = "/resetPassword", method = {RequestMethod.PUT}, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String resetPassword(@RequestBody Map<String, String> params) throws Exception {
        System.out.println("**************resetPassword***************");
        System.out.println(params);
        if (userService.resetPassword(params.get("phone"), params.get("password"), params.get("identity")))
            return "success";

        return "重置失败，请查看信息是否正确";
    }


}

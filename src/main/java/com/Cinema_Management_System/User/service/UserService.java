package com.Cinema_Management_System.User.service;


import com.Cinema_Management_System.User.entity.Customer;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    //登陆
    String loginByName(String customerName, Integer managerId, String password,String identity);
    String loginByPhone(String phone,String identity);

    //获取管理员或用户信息
    Map<String,Object> getInfoById(Integer customerId, Integer managerId);

    //检查是否重名
    boolean isExistCustomerName(String customerName,String customerPhone);

    //注册
    boolean register(Customer customer);

    //重置密码
    boolean resetPassword(String phone, String password, String identity);

    //获取短信验证码
    int getPhoneCode(String customerPhone);

    //获取个人信息
    List<JSONObject> getPersonalInfo(Integer customerId, Integer managerId);
}

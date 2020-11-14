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


    //修改个人信息
    boolean updateInfoById( Integer id, String value, String type, String identity);

    //获取用户和管理员信息
    Map<String,Object> getAllUser(String identity,int pageNo,int pageSize);

    //修改用户和管理员信息
    boolean UpdateUserInfo(String type,String title,Object info);

    //获取管理员最大编号
    int getMaxCount();


    //删除用户
    boolean deleteUser(String type,List<Integer> Ids) throws Exception;
}

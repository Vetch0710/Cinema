package com.Cinema_Management_System.User.dao;



import com.Cinema_Management_System.User.entity.Customer;
import com.Cinema_Management_System.User.entity.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
   //查询管理员密码/用户信息
   Customer selectCusInfoById(@Param(value = "customerId") Integer customerId);
   Manager selectManInfoById( @Param(value = "managerId") Integer managerId);

   //登陆----查询管理员和用户id
   Customer selectCustomer(@Param(value = "customerName") String customerName,@Param(value = "customerPhone") String customerPhone);
   Manager selectManager(@Param(value = "managerId") Integer managerId,@Param(value = "managerPhone") String managerPhone);

   //检查是否重名
   int isExistCustomer(@Param(value = "customerName") String customerName,@Param(value = "customerPhone") String customerPhone);

   //注册----添加用户
   int addUser(Customer customer);

   //重置密码----重置管理员密码/用户密码
   int resetPassword(@Param(value = "phone") String phone, @Param(value = "password") String password, @Param(value = "identity") String identity);
}

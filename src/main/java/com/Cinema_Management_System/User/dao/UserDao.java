package com.Cinema_Management_System.User.dao;



import com.Cinema_Management_System.User.entity.Customer;
import com.Cinema_Management_System.User.entity.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
   //是否有重 手机号
   int isExistManager(@Param(value = "customerPhone") String customerPhone);

   //注册----添加用户
   int addUser(Customer customer);
   //添加管理员
   int addManager(Manager manager);
   //获取最大的管理员编号
   int selectMax();



   //重置密码----重置管理员密码/用户密码
   int resetPassword(@Param(value = "phone") String phone, @Param(value = "password") String password, @Param(value = "identity") String identity);


   //修改个人信息
   int updateInfoById(@Param(value = "id") Integer id, @Param(value = "value") String value, @Param(value = "type") String type,@Param(value = "identity") String identity);

   //查询所有的用户和管理员
   List<Manager> selectAllManager(@Param(value = "pageNo") int pageNo, @Param(value = "pageSize") int pageSize);
   List<Customer> selectAllCustomer(@Param(value = "pageNo") int pageNo, @Param(value = "pageSize") int pageSize);

   //查询所有的用户和管理员数量
   int selectAllManagerCount();
   int selectAllCustomerCount();


   //修改管理员/用户信息
   int updateCusInfo(Customer customer);
   int updateManInfo(Manager manager);

   //删除用户
   int deleteCustomer(@Param(value = "list")List<Integer> customerId);
   int deleteManager(@Param(value = "list")List<Integer> managerId);

   int selectOrderStatusCount(@Param(value = "list")List<Integer> customerId);
}

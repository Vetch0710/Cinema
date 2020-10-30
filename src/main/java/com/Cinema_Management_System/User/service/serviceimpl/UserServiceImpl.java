package com.Cinema_Management_System.User.service.serviceimpl;


import com.Cinema_Management_System.User.dao.UserDao;
import com.Cinema_Management_System.User.entity.Customer;
import com.Cinema_Management_System.User.entity.Manager;
import com.Cinema_Management_System.User.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

@PropertySource(value = {"classpath:properties/sms.properties"})
@Transactional(rollbackFor = Exception.class)
@Service(value = "UserService")
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @Value("${apiUrl}")
    private String apiUrl;
    @Value("${appId}")
    private String appId;
    @Value("${appSecret}")
    private String appSecret;
    @Value("${templateId}")
    private String templateId;

    @Override
    public String loginByName(String customerName, Integer managerId, String password, String identity) {
        System.out.println("customerName：" + customerName + "managerId：" + managerId + "password：" + password + "identity：" + identity);
        if ("customer".equals(identity)) {
            Customer customer = userDao.selectCustomer(customerName, null);
            System.out.println(customer);
            try {
                if (password.equals(customer.getCustomerPassword())) {
                    return customer.getCustomerId() + "-customer";
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            Manager manager = userDao.selectManager(managerId, null);
            System.out.println(manager);
            try {
                if (password.equals(manager.getManagerPassword())) {
                    String managerIdentity = manager.getManagerIsBoss() == 1 ? "boss" : "manager";
                    return managerId + "-" + managerIdentity;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return "fail";
    }

    @Override
    public String loginByPhone(String phone, String identity) {
        if ("customer".equals(identity)) {
            Customer customer = userDao.selectCustomer(null, phone);
            System.out.println(customer);
            try {
                return customer.getCustomerId() + "-customer";
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            Manager manager = userDao.selectManager(null, phone);
            System.out.println(manager);
            try {
                String managerIdentity = manager.getManagerIsBoss() == 1 ? "boss" : "manager";
                return manager.getManagerId() + "-" + managerIdentity;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return "fail";
    }

    @Override
    public Map<String, Object> getInfoById(Integer customerId, Integer managerId) {
        Map<String, Object> info = new HashMap<>();
        if (customerId == null) {
            Manager manager = userDao.selectManInfoById(managerId);
            info.put("username", manager.getManagerName());
        } else {
            Customer customer = userDao.selectCusInfoById(customerId);
            info.put("avatar", customer.getCustomerPicture());
            info.put("username", customer.getCustomerName());
        }

        return info;
    }

    @Override
    public boolean isExistCustomerName(String customerName, String customerPhone) {
        try {
            int existCustomerName = userDao.isExistCustomer(customerName, customerPhone);
            if (existCustomerName == 0) {
                return false;
            }
        } catch (Exception e) {

        }
        return true;
    }

    @Override
    public boolean register(Customer customer) {
        return userDao.addUser(customer) != 0;
    }

    @Override
    public boolean resetPassword(String phone, String password, String identity) {
        return userDao.resetPassword(phone, password, identity) != 0;
    }

    @Override
    public int getPhoneCode(String customerPhone) {
        try {

            JSONObject json = null;
            //生成6位验证码
            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
            System.out.print("验证码: " + verifyCode);
            //发送短信
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            Map<String, Object> params = new HashMap<>();
            params.put("number", customerPhone);
            params.put("templateId", templateId);
            String[] templateParams = {verifyCode, "10分钟内有效"};
            params.put("templateParams", templateParams);
            String result = client.send(params);

            json = JSONObject.parseObject(result);
            if (json.getIntValue("code") == 0) {//发送短信失败
                System.out.println("发送成功");
                return Integer.parseInt(verifyCode);
            }
            System.out.println("发送失败");
//            System.out.println("剩余"+client.balance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    @Override
    public List<JSONObject> getPersonalInfo(Integer customerId, Integer managerId) {
        List<JSONObject> datas = new ArrayList<>();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("type", "编号");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("type", "头像");
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("type", "姓名");
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("type", "性别");
        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("type", "密码");
        JSONObject jsonObject6 = new JSONObject();
        jsonObject6.put("type", "手机号");
        JSONObject jsonObject7 = new JSONObject();


        if (customerId == null) {
            Manager manager = userDao.selectManInfoById(managerId);
            jsonObject1.put("value", manager.getManagerId());
            jsonObject3.put("value", manager.getManagerName());
            jsonObject4.put("value", manager.getManagerSex());
            jsonObject5.put("value", manager.getManagerPassword());
            jsonObject6.put("value", manager.getManagerPhone());
            jsonObject7.put("type", "职位");
            jsonObject7.put("value", manager.getManagerIsBoss() == 1 ? "老板" : "管理员");

            datas.add(jsonObject1);
            datas.add(jsonObject3);
            datas.add(jsonObject4);
            datas.add(jsonObject5);
            datas.add(jsonObject6);
            datas.add(jsonObject7);

        } else {
            Customer customer = userDao.selectCusInfoById(customerId);
            System.out.println(customer);
            jsonObject1.put("value", customer.getCustomerId());
            jsonObject2.put("value", customer.getCustomerPicture());
            jsonObject3.put("value", customer.getCustomerName());
            jsonObject4.put("value", customer.getCustomerSex());
            jsonObject5.put("value", customer.getCustomerPassword());
            jsonObject6.put("value", customer.getCustomerPhone());


            datas.add(jsonObject1);
            datas.add(jsonObject2);
            datas.add(jsonObject3);
            datas.add(jsonObject4);
            datas.add(jsonObject5);
            datas.add(jsonObject6);
        }
        return datas;
    }

    @Override
    public boolean updateInfoById(Integer id, String value, String type, String identity) {
        return userDao.updateInfoById(id, value, type, identity) != 0;
    }

    @Override
    public Map<String, Object> getAllUser(String identity, int pageNo, int pageSize) {
        Map<String, Object> result = new HashMap<>();
        if ("customer".equals(identity)) {
            List<Customer> customers = userDao.selectAllCustomer(pageNo, pageSize);
            int count = userDao.selectAllCustomerCount();
            result.put("result", customers);
            result.put("count", count);
        } else {
            List<Manager> managers = userDao.selectAllManager(pageNo, pageSize);
            int count = userDao.selectAllManagerCount();

            result.put("result", managers);
            result.put("count", count);

        }
        return result;
    }

    @Override
    public boolean UpdateUserInfo(String type, String title, Object info) {
        if ("customer".equals(type)) {
            Customer customer = (Customer) info;
            return userDao.updateCusInfo(customer) != 0;
        } else {
            Manager manager = (Manager) info;
            if (userDao.selectManInfoById(manager.getManagerId()) != null) {
                return userDao.updateManInfo(manager) != 0;
            } else {
                return userDao.addManager(manager) != 0;
            }
        }
    }

    @Override
    public int getMaxCount() {
        return userDao.selectMax();
    }

    @Override
    public boolean deleteUser(String type, List<Integer> Ids) {
        if ("customer".equals(type)) {
          return   userDao.deleteCustomer(Ids)!=0;
        }else {
            return userDao.deleteManager(Ids)!=0;
        }
    }
}

package com.Cinema_Management_System.User.entity;

import java.io.Serializable;

/**
 * @Description 顾客信息实体类
 * @Auther GaoYi
 * @Date 2020/10/19 12:29 下午
 * * @param customerId       顾客id
 * * @param customerPassword 密码
 * * @param customerPicture  头像
 * * @param customerName     昵称
 * * @param customerPhone    手机号
 * * @param customerSex      性别
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = 4222222L;
    private Integer customerId;
    private String customerPassword, customerPicture, customerName, customerPhone;
    private char customerSex;

    public Customer() {
    }

    public Customer(Integer customerId, String customerPassword, String customerPicture, String customerName, String customerPhone, char customerSex) {
        this.customerId = customerId;
        this.customerPassword = customerPassword;
        this.customerPicture = customerPicture;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerSex = customerSex;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerPicture() {
        return customerPicture;
    }

    public void setCustomerPicture(String customerPicture) {
        this.customerPicture = customerPicture;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public char getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(char customerSex) {
        this.customerSex = customerSex;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerPassword='" + customerPassword + '\'' +
                ", customerPicture='" + customerPicture + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerSex=" + customerSex +
                '}';
    }
}

package com.Cinema_Management_System.Manager.entity;

/**
 * @Description 管理员信息表
 * @Auther GaoYi
 * @Date 2020/10/19 1:01 下午
 * * @param managerId            管理员id
 * * @param managerPassword      管理员密码
 * * @param managerName          管理员名称
 * * @param managerPhone         手机号
 * * @param managerSex           性别
 * * @param managerIsBoss       是否为老板(0为老板，1为员工)
 */
public class Manager {
    private int managerId;
    private String managerPassword, managerName, managerPhone;
    private char managerSex;
    private byte managerIsBoss;

    public Manager() {
    }

    public Manager(int managerId, String managerPassword, String managerName, String managerPhone, char managerSex, byte managerIsBoss) {
        this.managerId = managerId;
        this.managerPassword = managerPassword;
        this.managerName = managerName;
        this.managerPhone = managerPhone;
        this.managerSex = managerSex;
        this.managerIsBoss = managerIsBoss;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public char getManagerSex() {
        return managerSex;
    }

    public void setManagerSex(char managerSex) {
        this.managerSex = managerSex;
    }

    public byte getManagerIsBoss() {
        return managerIsBoss;
    }

    public void setManagerIsBoss(byte managerIsBoss) {
        this.managerIsBoss = managerIsBoss;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", managerPassword='" + managerPassword + '\'' +
                ", managerName='" + managerName + '\'' +
                ", managerPhone='" + managerPhone + '\'' +
                ", managerSex=" + managerSex +
                ", managerIsBoss=" + managerIsBoss +
                '}';
    }
}

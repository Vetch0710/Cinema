package com.Cinema_Management_System.Inform.entity;

import java.util.Date;

/**
 * @Description 通知信息表
 * @Auther GaoYi
 * @Date 2020/10/19 12:50 下午
 * * @param infoId          通知id
 * * @param customerId      顾客id
 * * @param infoContent     通知内容
 * * @param infoStatus      通知状态
 * * @param infoTime        通知时间
 * * @param infoType        消息发送的类型
 * * @param messageCount        未读消息的数量
 */
public class Inform {
    private int infoId;
    private String infoContent;
    private Date infoTime;
    private String infoStatus;
    private String infoType;
    private int customerId;
    private String customerName;
    private int messageCount;


    public Date getInfoTime() {
        return infoTime;
    }

    public void setInfoTime(Date infoTime) {
        this.infoTime = infoTime;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }


    public String getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "infoId=" + infoId +
                ", infoContent='" + infoContent + '\'' +
                ", infoTime=" + infoTime +
                ", infoStatus='" + infoStatus + '\'' +
                ", infoType='" + infoType + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}

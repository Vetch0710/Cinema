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
 */
public class Inform {
    private int infoId, customerId;
    private String infoContent, infoStatus;
    private Date infoTime;

    public Inform() {
    }

    public Inform(int infoId, int customerId, String infoContent, String infoStatus, Date infoTime) {
        this.infoId = infoId;
        this.customerId = customerId;
        this.infoContent = infoContent;
        this.infoStatus = infoStatus;
        this.infoTime = infoTime;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public Date getInfoTime() {
        return infoTime;
    }

    public void setInfoTime(Date infoTime) {
        this.infoTime = infoTime;
    }

    @Override
    public String toString() {
        return "Inform{" +
                "infoId=" + infoId +
                ", customerId=" + customerId +
                ", infoContent='" + infoContent + '\'' +
                ", infoStatus='" + infoStatus + '\'' +
                ", infoTime=" + infoTime +
                '}';
    }
}

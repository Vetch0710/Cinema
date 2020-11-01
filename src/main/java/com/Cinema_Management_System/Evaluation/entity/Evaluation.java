package com.Cinema_Management_System.Evaluation.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description 评价信息实体类
 * @Auther GaoYi
 * @Date 2020/10/19 12:38 下午
 * * @param evaluationId        评价编号
 * * @param orderId             订单编号
 * * @param evaluationScore     评价得分
 * * @param evaluationContent   评价内容
 * * @param evaluationTime      评价时间
 */
@Data
@Accessors(chain = true)
public class Evaluation {
    private Integer evaluationId, customerId,orderId;
    private float evaluationScore;
    private String evaluationContent, customerName, movieName, customerPicture;
    private Date evaluationTime, orderTime;

    public Evaluation() {
    }

    public Evaluation(Integer evaluationId, Integer customerId, Integer orderId, float evaluationScore, String evaluationContent, String customerName, String movieName, String customerPicture, Date evaluationTime, Date orderTime) {
        this.evaluationId = evaluationId;
        this.customerId = customerId;
        this.orderId = orderId;
        this.evaluationScore = evaluationScore;
        this.evaluationContent = evaluationContent;
        this.customerName = customerName;
        this.movieName = movieName;
        this.customerPicture = customerPicture;
        this.evaluationTime = evaluationTime;
        this.orderTime = orderTime;
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public float getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(float evaluationScore) {
        this.evaluationScore = evaluationScore;
    }

    public String getEvaluationContent() {
        return evaluationContent;
    }

    public void setEvaluationContent(String evaluationContent) {
        this.evaluationContent = evaluationContent;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCustomerPicture() {
        return customerPicture;
    }

    public void setCustomerPicture(String customerPicture) {
        this.customerPicture = customerPicture;
    }

    public Date getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Date evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "evaluationId=" + evaluationId +
                ", customerId=" + customerId +
                ", orderId=" + orderId +
                ", evaluationScore=" + evaluationScore +
                ", evaluationContent='" + evaluationContent + '\'' +
                ", customerName='" + customerName + '\'' +
                ", movieName='" + movieName + '\'' +
                ", customerPicture='" + customerPicture + '\'' +
                ", evaluationTime=" + evaluationTime +
                ", orderTime=" + orderTime +
                '}';
    }
}

package com.Cinema_Management_System.Evaluation.entity;

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
public class Evaluation {
    private int evaluationId, orderId;
    private float evaluationScore;
    private String evaluationContent;
    private Date evaluationTime;

    public Evaluation() {
    }

    public Evaluation(int evaluationId, int orderId, float evaluationScore, String evaluationContent, Date evaluationTime) {
        this.evaluationId = evaluationId;
        this.orderId = orderId;
        this.evaluationScore = evaluationScore;
        this.evaluationContent = evaluationContent;
        this.evaluationTime = evaluationTime;
    }

    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
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

    public Date getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Date evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "evaluationId=" + evaluationId +
                ", orderId=" + orderId +
                ", evaluationScore=" + evaluationScore +
                ", evaluationContent='" + evaluationContent + '\'' +
                ", evaluationTime=" + evaluationTime +
                '}';
    }
}

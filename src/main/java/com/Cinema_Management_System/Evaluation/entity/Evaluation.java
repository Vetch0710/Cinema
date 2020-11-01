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
    private int evaluationId, customerId;
    private float evaluationScore;
    private String evaluationContent, customerName, movieName, customerPicture;
    private Date evaluationTime;

    public Evaluation() {
    }

    public Evaluation(int evaluationId, int customerId, float evaluationScore, String evaluationContent, String customerName, String movieName, String customerPicture, Date evaluationTime) {
        this.evaluationId = evaluationId;
        this.customerId = customerId;
        this.evaluationScore = evaluationScore;
        this.evaluationContent = evaluationContent;
        this.customerName = customerName;
        this.movieName = movieName;
        this.customerPicture = customerPicture;
        this.evaluationTime = evaluationTime;
    }

}

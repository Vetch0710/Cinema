package com.Cinema_Management_System.Order.entity;

import java.util.Date;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/30 1:46 下午
 */
public class PayOrder extends Order {
    private int arrangementPlace;
    private String movieName;
    private Date arrangementTime;

    public PayOrder() {
    }

    public PayOrder(long orderId, int arrangementId, float orderPrice, String orderStatus, String orderSeat, Date orderTime, int arrangementPlace, String movieName, Date arrangementTime) {
        super(orderId, arrangementId, orderPrice, orderStatus, orderSeat, orderTime);
        this.arrangementPlace = arrangementPlace;
        this.movieName = movieName;
        this.arrangementTime = arrangementTime;
    }

    public int getArrangementPlace() {
        return arrangementPlace;
    }

    public void setArrangementPlace(int arrangementPlace) {
        this.arrangementPlace = arrangementPlace;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getArrangementTime() {
        return arrangementTime;
    }

    public void setArrangementTime(Date arrangementTime) {
        this.arrangementTime = arrangementTime;
    }

    @Override
    public String toString() {
        return "PayOrder{" +
                "arrangementPlace=" + arrangementPlace +
                ", movieName='" + movieName + '\'' +
                ", arrangementTime=" + arrangementTime +
                '}';
    }
}

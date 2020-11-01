package com.Cinema_Management_System.Order.entity;

import java.util.Date;

/**
 * @author Vetch
 * @Description
 * @create 2020-10-31-13:44
 */
public class CMOrder extends Order {
    private Integer movieId;
    private String movieName;
    private Integer arrangementPlace;
    private Date arrangementTime;
    private Integer customerId;
    private String customerName;

    public CMOrder() {
    }

    public CMOrder(long orderId, int arrangementId, float orderPrice, String orderStatus, String orderSeat, Date orderTime, Integer movieId, String movieName, Integer arrangementPlace, Date arrangementTime, Integer customerId, String customerName) {
        super(orderId, arrangementId, orderPrice, orderStatus, orderSeat, orderTime);
        this.movieId = movieId;
        this.movieName = movieName;
        this.arrangementPlace = arrangementPlace;
        this.arrangementTime = arrangementTime;
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getArrangementPlace() {
        return arrangementPlace;
    }

    public void setArrangementPlace(Integer arrangementPlace) {
        this.arrangementPlace = arrangementPlace;
    }

    public Date getArrangementTime() {
        return arrangementTime;
    }

    public void setArrangementTime(Date arrangementTime) {
        this.arrangementTime = arrangementTime;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "CMOrder{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", arrangementPlace=" + arrangementPlace +
                ", arrangementTime=" + arrangementTime +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}

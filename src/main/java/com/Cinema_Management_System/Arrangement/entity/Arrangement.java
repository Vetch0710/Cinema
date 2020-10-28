package com.Cinema_Management_System.Arrangement.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description 此实体类为电影场次信息实体类
 * @Auther GaoYi
 * @Date 2020/10/19 11:16 上午
 * * @param arrangementId    场次id
 * * @param arrangementPlace 影厅
 * * @param arrangementPrice 票价
 * * @param movieId          影片id
 * * @param movieName        片名
 * * @param arrangementTime  场次时间
 */

@Component(value = "Arrangement")
public class Arrangement {
    private int arrangementId, arrangementPlace, arrangementPrice, movieId;
    private Date arrangementTime;
    private String movieName;

    public Arrangement() {
    }

    public Arrangement(int arrangementId, int arrangementPlace, int arrangementPrice, int movieId, Date arrangementTime, String movieName) {
        this.arrangementId = arrangementId;
        this.arrangementPlace = arrangementPlace;
        this.arrangementPrice = arrangementPrice;
        this.movieId = movieId;
        this.arrangementTime = arrangementTime;
        this.movieName = movieName;
    }

    public int getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(int arrangementId) {
        this.arrangementId = arrangementId;
    }

    public int getArrangementPlace() {
        return arrangementPlace;
    }

    public void setArrangementPlace(int arrangementPlace) {
        this.arrangementPlace = arrangementPlace;
    }

    public int getArrangementPrice() {
        return arrangementPrice;
    }

    public void setArrangementPrice(int arrangementPrice) {
        this.arrangementPrice = arrangementPrice;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Date getArrangementTime() {
        return arrangementTime;
    }

    public void setArrangementTime(Date arrangementTime) {
        this.arrangementTime = arrangementTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public String toString() {
        return "Arrangement{" +
                "arrangementId=" + arrangementId +
                ", arrangementPlace=" + arrangementPlace +
                ", arrangementPrice=" + arrangementPrice +
                ", movieId=" + movieId +
                ", arrangementTime=" + arrangementTime +
                ", movieName='" + movieName + '\'' +
                '}';
    }
}

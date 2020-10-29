package com.Cinema_Management_System.Arrangement.entity;

import java.util.Date;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/28 3:57 下午
 */
public class UsedTime {
    private Date arrangementTime;
    private String movieTime;

    public UsedTime() {
    }

    public UsedTime(Date arrangementTime, String movieTime) {
        this.arrangementTime = arrangementTime;
        this.movieTime = movieTime;
    }

    public Date getArrangementTime() {
        return arrangementTime;
    }

    public void setArrangementTime(Date arrangementTime) {
        this.arrangementTime = arrangementTime;
    }

    public String getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(String movieTime) {
        this.movieTime = movieTime;
    }

    @Override
    public String toString() {
        return "UsedTime{" +
                "arrangementTime=" + arrangementTime +
                ", movieTime='" + movieTime + '\'' +
                '}';
    }
}

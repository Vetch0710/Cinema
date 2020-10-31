package com.Cinema_Management_System.Movie.entity;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/29 2:29 下午
 */
public class UpcomingMovie extends Movie {
    private int wantsNum;

    public UpcomingMovie() {
    }

    public UpcomingMovie(int movieId, String movieName, String moviePicture, float movieScore, int wantsNum) {
        super(movieId, movieName, moviePicture, movieScore);
        this.wantsNum = wantsNum;
    }

    public int getWantsNum() {
        return wantsNum;
    }

    public void setWantsNum(int wantsNum) {
        this.wantsNum = wantsNum;
    }

    @Override
    public String toString() {
        return "UpcomingMovie{" +
                "wantsNum=" + wantsNum +
                '}';
    }
}

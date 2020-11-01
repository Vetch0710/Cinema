package com.Cinema_Management_System.Movie.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/29 2:29 下午
 */
@Data
@Accessors(chain = true)
public class UpcomingMovie extends Movie {
    private int wantsNum;

    public UpcomingMovie() {
    }

    public UpcomingMovie(int movieId, String movieName, String moviePicture, float movieScore, int wantsNum) {
        super(movieId, movieName, moviePicture, movieScore);
        this.wantsNum = wantsNum;
    }


}

package com.Cinema_Management_System.Movie.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/29 1:39 下午
 */
@Data
@Accessors(chain = true)
public class Movie {
    private int movieId;
    private String movieName, moviePicture;
    private float movieScore;

    public Movie() {
    }

    public Movie(int movieId, String movieName, String moviePicture, float movieScore) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.moviePicture = moviePicture;
        this.movieScore = movieScore;
    }


}

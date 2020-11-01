package com.Cinema_Management_System.Movie.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/29 9:05 下午
 */
@Data
@Accessors(chain = true)
public class ArrangementMovie extends Movie {
    private String movieTime, movieActor, movieType;

    public ArrangementMovie() {
    }

    public ArrangementMovie(int movieId, String movieName, String moviePicture, float movieScore, String movieTime, String movieActor, String movieType) {
        super(movieId, movieName, moviePicture, movieScore);
        this.movieTime = movieTime;
        this.movieActor = movieActor;
        this.movieType = movieType;
    }

}

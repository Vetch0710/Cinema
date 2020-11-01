package com.Cinema_Management_System.Movie.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/29 1:46 下午
 */
@Data
@Accessors(chain = true)
public class HitMovie extends Movie {
    private float lowestPrice;

    public HitMovie() {
    }

    public HitMovie(int movieId, String movieName, String moviePicture, float movieScore, float lowestPrice) {
        super(movieId, movieName, moviePicture, movieScore);
        this.lowestPrice = lowestPrice;
    }

}

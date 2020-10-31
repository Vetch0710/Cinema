package com.Cinema_Management_System.Movie.entity;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/29 1:46 下午
 */
public class HitMovie extends Movie {
    private float lowestPrice;

    public HitMovie() {
    }

    public HitMovie(int movieId, String movieName, String moviePicture, float movieScore, float lowestPrice) {
        super(movieId, movieName, moviePicture, movieScore);
        this.lowestPrice = lowestPrice;
    }

    public float getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(float lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    @Override
    public String toString() {
        return "SimpleMovie{" +
                "lowestPrice=" + lowestPrice +
                '}';
    }
}

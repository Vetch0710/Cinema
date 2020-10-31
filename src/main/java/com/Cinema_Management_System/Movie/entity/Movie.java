package com.Cinema_Management_System.Movie.entity;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/29 1:39 下午
 */
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

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMoviePicture() {
        return moviePicture;
    }

    public void setMoviePicture(String moviePicture) {
        this.moviePicture = moviePicture;
    }

    public float getMovieScore() {
        return movieScore;
    }

    public void setMovieScore(float movieScore) {
        this.movieScore = movieScore;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", moviePicture='" + moviePicture + '\'' +
                ", movieScore=" + movieScore +
                '}';
    }
}

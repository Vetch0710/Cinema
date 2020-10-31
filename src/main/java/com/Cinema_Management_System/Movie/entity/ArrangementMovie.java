package com.Cinema_Management_System.Movie.entity;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/29 9:05 下午
 */
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

    public String getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(String movieTime) {
        this.movieTime = movieTime;
    }

    public String getMovieActor() {
        return movieActor;
    }

    public void setMovieActor(String movieActor) {
        this.movieActor = movieActor;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    @Override
    public String toString() {
        return "ArrangementMovie{" +
                "movieTime='" + movieTime + '\'' +
                ", movieActor='" + movieActor + '\'' +
                ", movieType='" + movieType + '\'' +
                '}';
    }
}

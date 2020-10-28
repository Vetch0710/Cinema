package com.Cinema_Management_System.Movie.entity;

import java.util.Date;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/19 2:58 下午
 * * @param movieId             影片id
 * * @param wantsNum            想看人数
 * * @param movieName           影片名称
 * * @param moviePicture        海报
 * * @param movieTrailer        预告
 * * @param movieDes            影片简述
 * * @param movieDirector       导演
 * * @param movieActor          演员
 * * @param movieTime           时长
 * * @param movieType           类型
 * * @param movieReleaseTime    上映日期
 * * @param movieScore          评分
 */
public class Movie {
    private int movieId, wantsNum;
    private String movieName, moviePicture, movieTrailer, movieDes, movieDirector, movieActor, movieTime, movieType;
    private Date movieReleaseTime;
    private float movieScore;

    public Movie() {
    }

    public Movie(int movieId, int wantsNum, String movieName, String moviePicture, String movieTrailer, String movieDes, String movieDirector, String movieActor, String movieTime, String movieType, Date movieReleaseTime, float movieScore) {
        this.movieId = movieId;
        this.wantsNum = wantsNum;
        this.movieName = movieName;
        this.moviePicture = moviePicture;
        this.movieTrailer = movieTrailer;
        this.movieDes = movieDes;
        this.movieDirector = movieDirector;
        this.movieActor = movieActor;
        this.movieTime = movieTime;
        this.movieType = movieType;
        this.movieReleaseTime = movieReleaseTime;
        this.movieScore = movieScore;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getWantsNum() {
        return wantsNum;
    }

    public void setWantsNum(int wantsNum) {
        this.wantsNum = wantsNum;
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

    public String getMovieTrailer() {
        return movieTrailer;
    }

    public void setMovieTrailer(String movieTrailer) {
        this.movieTrailer = movieTrailer;
    }

    public String getMovieDes() {
        return movieDes;
    }

    public void setMovieDes(String movieDes) {
        this.movieDes = movieDes;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieActor() {
        return movieActor;
    }

    public void setMovieActor(String movieActor) {
        this.movieActor = movieActor;
    }

    public String getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(String movieTime) {
        this.movieTime = movieTime;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public Date getMovieReleaseTime() {
        return movieReleaseTime;
    }

    public void setMovieReleaseTime(Date movieReleaseTime) {
        this.movieReleaseTime = movieReleaseTime;
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
                ", wantsNum=" + wantsNum +
                ", movieName='" + movieName + '\'' +
                ", moviePicture='" + moviePicture + '\'' +
                ", movieTrailer='" + movieTrailer + '\'' +
                ", movieDes='" + movieDes + '\'' +
                ", movieDirector='" + movieDirector + '\'' +
                ", movieActor='" + movieActor + '\'' +
                ", movieTime='" + movieTime + '\'' +
                ", movieType='" + movieType + '\'' +
                ", movieReleaseTime=" + movieReleaseTime +
                ", movieScore=" + movieScore +
                '}';
    }
}

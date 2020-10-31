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
public class DetailMovie extends Movie {
    private int wantsNum;
    private String movieTrailer, movieDes, movieDirector, movieActor, movieTime, movieType;
    private Date movieReleaseTime;

    public DetailMovie() {
    }

    public DetailMovie(int movieId, String movieName, String moviePicture, float movieScore, int wantsNum, String movieTrailer, String movieDes, String movieDirector, String movieActor, String movieTime, String movieType, Date movieReleaseTime) {
        super(movieId, movieName, moviePicture, movieScore);
        this.wantsNum = wantsNum;
        this.movieTrailer = movieTrailer;
        this.movieDes = movieDes;
        this.movieDirector = movieDirector;
        this.movieActor = movieActor;
        this.movieTime = movieTime;
        this.movieType = movieType;
        this.movieReleaseTime = movieReleaseTime;
    }

    public int getWantsNum() {
        return wantsNum;
    }

    public void setWantsNum(int wantsNum) {
        this.wantsNum = wantsNum;
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

    @Override
    public String toString() {
        return "DetailMovie{" +
                "wantsNum=" + wantsNum +
                ", movieTrailer='" + movieTrailer + '\'' +
                ", movieDes='" + movieDes + '\'' +
                ", movieDirector='" + movieDirector + '\'' +
                ", movieActor='" + movieActor + '\'' +
                ", movieTime='" + movieTime + '\'' +
                ", movieType='" + movieType + '\'' +
                ", movieReleaseTime=" + movieReleaseTime +
                '}';
    }
}

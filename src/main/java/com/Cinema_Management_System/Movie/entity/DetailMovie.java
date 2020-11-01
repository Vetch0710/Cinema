package com.Cinema_Management_System.Movie.entity;

import lombok.Data;
import lombok.experimental.Accessors;

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

@Data
@Accessors(chain = true)
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


}

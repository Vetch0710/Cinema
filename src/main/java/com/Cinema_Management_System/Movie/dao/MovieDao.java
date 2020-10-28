package com.Cinema_Management_System.Movie.dao;

import com.Cinema_Management_System.Movie.entity.Movie;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MovieDao {
    //查询全部影片信息
    List<Movie> selectMovieAll(@Param("selType") String selType, @Param("selContent") String selContent, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    //通过电影名称查询指定影片信息
    Movie selectMovieByName(@Param("movieName") String movieName);

    //按是否已上映与搜索条件(按时间，按类型)来查询影片
    List<Movie> selectMovieByType(@Param("timeType") String timeType,
                                  @Param("selectType") String selectType,
                                  @Param("pageStart") Integer pageStart,
                                  @Param("pageSize") Integer pageSize,
                                  @Param("nowDate") Date nowDate);

    //按影片id删除指定电影
    int deleteMovie(@Param("movieId") Integer movieId);

    //批量删除影片
    int deleteMovies(List<Integer> movieIds);

    //更新影片信息
    void updateMovie(Movie movie);

    //新增影片信息
    void insertMovie(Movie movie);

    int getCount(@Param("selType") String selType, @Param("selContent") String selContent);
}
package com.Cinema_Management_System.Movie.service;

import com.Cinema_Management_System.Movie.entity.Movie;
import com.Cinema_Management_System.utils.Exception.DeleteException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface MovieService {
    //查询全部影片信息
    List<Movie> selectMovieAll(String selType, String selContent, Integer pageStart, Integer pageSize);

    //通过电影名称查询指定影片信息
    Movie selectMovieByName(String movieName);

    //按是否已上映与搜索条件(按时间，按类型)来查询影片
    List<Movie> selectMovieByType(String timeType, String selectType, Integer pageStart, Integer pageSize, Date nowDate);

    //按影片id删除指定电影
    void deleteMovie(Integer movieId) throws DeleteException;

    //批量删除影片
    void deleteMovies(String movieIds) throws DeleteException;

    //更新影片信息
    void updateMovie(Movie movie);

    //新增影片信息
    void insertMovie(Movie movie);

    int getCount(String selType, String selContent);
}

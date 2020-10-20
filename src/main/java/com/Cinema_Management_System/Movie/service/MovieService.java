package com.Cinema_Management_System.Movie.service;

import com.Cinema_Management_System.Movie.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface MovieService {
    //查询全部影片信息
    List<Movie> selectMovieAll(Integer pageStart, Integer pageNum);

    //通过电影名称查询指定影片信息
    Movie selectMovieByName(String movieName);

    //按是否已上映与搜索条件(按时间，按类型)来查询影片
    List<Movie> selectMovieByType(String timeType, String selectType, Integer pageStart, Integer pageNum, Date nowDate);

    //按影片id删除指定电影
    String deleteMovie(Integer movieId);

    //批量删除影片
    String deleteMovies(List<Integer> movieIds);

    //更新影片信息
    String updateMovie(Movie movie);

    //新增影片信息
    String insertMovie(Movie movie);
}

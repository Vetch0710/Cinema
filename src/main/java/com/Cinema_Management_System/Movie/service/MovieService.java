package com.Cinema_Management_System.Movie.service;

import com.Cinema_Management_System.Movie.entity.DetailMovie;
import com.Cinema_Management_System.Movie.entity.HitMovie;
import com.Cinema_Management_System.Movie.entity.Movie;
import com.Cinema_Management_System.Movie.entity.UpcomingMovie;
import com.Cinema_Management_System.utils.Exception.DeleteException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    //查询全部影片信息
    List<DetailMovie> selectMovieAll(String selType, String selContent, Integer pageStart, Integer pageSize);

    //通过电影名称查询指定影片信息
    DetailMovie selectMovieByName(String movieName);

    //按是否已上映与搜索条件(按时间，按类型)来查询影片
    List<UpcomingMovie> selectUpcomingMovie(String selectType, Integer pageStart, Integer pageSize);

    List<HitMovie> selectHitMovie(String selectType, Integer pageStart, Integer pageSize);

    int HitMovieCount();

    int UpcomingMovieCount();

    //按影片id删除指定电影
    void deleteMovie(Integer movieId) throws DeleteException;

    //批量删除影片
    void deleteMovies(String movieIds) throws DeleteException;

    //更新影片信息
    void updateMovie(DetailMovie movie);

    //新增影片信息
    void insertMovie(DetailMovie movie);

    int getCount(String selType, String selContent);

    DetailMovie getDetailMovie(int movieId);

    List<Movie> getRelativeMovie(String movieType, String movieActor, String movieDirector);

    boolean getFlag(int movieId, int customerId);

    void changeWant(int wantFlag, int movieId, int customerId);

    int getMovieId(String movieName);

    List<Movie> getRecommendMovie();
}

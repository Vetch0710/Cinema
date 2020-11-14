package com.Cinema_Management_System.Movie.dao;

import com.Cinema_Management_System.Movie.entity.DetailMovie;

import com.Cinema_Management_System.Movie.entity.HitMovie;
import com.Cinema_Management_System.Movie.entity.Movie;
import com.Cinema_Management_System.Movie.entity.UpcomingMovie;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDao {
    //查询全部影片信息
    List<DetailMovie> selectMovieAll(@Param("selType") String selType, @Param("selContent") String selContent, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    //通过电影名称查询指定影片信息
    DetailMovie selectMovieByName(@Param("movieName") String movieName);

    //按是否已上映与搜索条件(按时间，按类型)来查询影片
    List<HitMovie> selectHitMovie(@Param("selectType") String selectType,
                                  @Param("pageStart") Integer pageStart,
                                  @Param("pageSize") Integer pageSize);

    DetailMovie getDetailMovie(@Param("movieId") int movieId);


    //按影片id删除指定电影
    int deleteMovie(@Param("movieId") Integer movieId);

    //批量删除影片
    int deleteMovies(List<Integer> movieIds);

    //更新影片信息
    void updateMovie(DetailMovie movie);

    //新增影片信息
    void insertMovie(DetailMovie movie);

    //获取总数
    int getCount(@Param("selType") String selType, @Param("selContent") String selContent);

    List<UpcomingMovie> selectUpcomingMovie(@Param("selectType") String selectType,
                                            @Param("pageStart") Integer pageStart,
                                            @Param("pageSize") Integer pageSize);

    int HitMovieCount();

    int UpcomingMovieCount();

    List<Movie> getRelativeMovie(@Param("typeList") List<String> typeList, @Param("actorList") List<String> actorList, @Param("directorList") List<String> directorList);

    List<Movie> getRecommendMovie();

    int isWant(@Param("movieId") int movieId, @Param("customerId") int customerId);

    int addWant(@Param("movieId") int movieId, @Param("customerId") int customerId);

    int delWant(@Param("movieId") int movieId, @Param("customerId") int customerId);

    int getMovieId(@Param("movieName") String movieName);

    int selectMovieCountSingle( Integer movieId);

    int selectMovieCountList(List<Integer> movieIds);
}

package com.Cinema_Management_System.Movie.service.serviceimpl;

import com.Cinema_Management_System.Movie.dao.MovieDao;
import com.Cinema_Management_System.Movie.entity.DetailMovie;
import com.Cinema_Management_System.Movie.entity.HitMovie;
import com.Cinema_Management_System.Movie.entity.Movie;
import com.Cinema_Management_System.Movie.entity.UpcomingMovie;
import com.Cinema_Management_System.Movie.service.MovieService;
import com.Cinema_Management_System.utils.Exception.DeleteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service(value = "MovieService")
public class MovieServiceImpl implements MovieService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MovieDao movieDao;

    //查询电影
    @Override
    public List<DetailMovie> selectMovieAll(String selType, String selContent, Integer pageStart, Integer pageSize) {
        return movieDao.selectMovieAll(selType, selContent, pageStart, pageSize);
    }
    //查询数量
    @Override
    public int getCount(String selType, String selContent) {
        return movieDao.getCount(selType, selContent);
    }

    @Override
    public DetailMovie getDetailMovie(int movieId) {
        return movieDao.getDetailMovie(movieId);
    }

    @Override
    public List<Movie> getRelativeMovie(String movieType, String movieActor, String movieDirector) {
        String[] types = movieType.split("/");
        String[] actors = movieActor.split("/");
        String[] directors = movieDirector.split("/");
        List<String> typeList = Arrays.asList(types);
        List<String> actorList = Arrays.asList(actors);
        List<String> directorList = Arrays.asList(directors);
        return movieDao.getRelativeMovie(typeList, actorList, directorList);
    }

    @Override
    public boolean getFlag(int movieId, int customerId) {
        boolean isWant = movieDao.isWant(movieId, customerId) > 0 ? true : false;
        return isWant;
    }

    @Override
    public void changeWant(int wantFlag, int movieId, int customerId) {
        int affectRows = wantFlag == 0 ? movieDao.addWant(movieId, customerId) : movieDao.delWant(movieId, customerId);
    }

    @Override
    public int getMovieId(String movieName) {
        return movieDao.getMovieId(movieName);
    }

    @Override
    public List<Movie> getRecommendMovie() {
        return movieDao.getRecommendMovie();
    }

    @Override
    public DetailMovie selectMovieByName(String movieName) {
        return movieDao.selectMovieByName(movieName);
    }

    @Override
    public List<HitMovie> selectHitMovie(String selectType, Integer pageStart, Integer pageSize) {
        return movieDao.selectHitMovie(selectType, pageStart, pageSize);
    }

    @Override
    public List<UpcomingMovie> selectUpcomingMovie(String selectType, Integer pageStart, Integer pageSize) {
        return movieDao.selectUpcomingMovie(selectType, pageStart, pageSize);
    }

    @Override
    public int HitMovieCount() {
        return movieDao.HitMovieCount();
    }

    @Override
    public int UpcomingMovieCount() {
        return movieDao.UpcomingMovieCount();
    }

//删除一个
    @Transactional(rollbackFor = DeleteException.class)
    @Override
    public void deleteMovie(Integer movieId) throws DeleteException {
        if (movieDao.selectMovieCountSingle(movieId)!=0){
            throw new DeleteException();
        }
        int affectRows = movieDao.deleteMovie(movieId);
        if (affectRows == 0) {
            throw new DeleteException();
        }
    }
//批量删除电影
    @Override
    @Transactional(rollbackFor = DeleteException.class)
    public void deleteMovies(String movieIds) throws DeleteException {
        String[] idArray = movieIds.split("&");
        List<Integer> idList = new ArrayList<>();
        for (String str : idArray) {
            idList.add(Integer.parseInt(str));
        }
        if (movieDao.selectMovieCountList(idList)!=0){
            throw new DeleteException();
        }
        int affectRows = movieDao.deleteMovies(idList);
        if (affectRows != idList.size()) {
            throw new DeleteException();
        }
    }
//更新电影
    @Override
    public void updateMovie(DetailMovie movie) {
        movieDao.updateMovie(movie);
    }
//插入电影
    @Override
    public void insertMovie(DetailMovie movie) {
        movieDao.insertMovie(movie);
    }


}

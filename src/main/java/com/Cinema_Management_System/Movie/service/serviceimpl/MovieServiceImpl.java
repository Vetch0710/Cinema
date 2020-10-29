package com.Cinema_Management_System.Movie.service.serviceimpl;

import com.Cinema_Management_System.Movie.dao.MovieDao;
import com.Cinema_Management_System.Movie.entity.Movie;
import com.Cinema_Management_System.Movie.service.MovieService;
import com.Cinema_Management_System.utils.Exception.DeleteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service(value = "MovieService")
public class MovieServiceImpl implements MovieService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MovieDao movieDao;


    public List<Movie> selectMovieAll(String selType, String selContent, Integer pageStart, Integer pageSize) {
        return movieDao.selectMovieAll(selType, selContent, pageStart, pageSize);
    }

    public int getCount(String selType, String selContent) {
        return movieDao.getCount(selType, selContent);
    }

    public Movie selectMovieByName(String movieName) {
        return movieDao.selectMovieByName(movieName);
    }

    public List<Movie> selectMovieByType(String timeType, String selectType, Integer pageStart, Integer pageSize, Date nowDate) {
        return movieDao.selectMovieByType(timeType, selectType, pageStart, pageSize, nowDate);
    }

    public void deleteMovie(Integer movieId) throws DeleteException {
        int affectRows = movieDao.deleteMovie(movieId);
        if (affectRows == 0) {
            throw new DeleteException();
        }
    }

    @Transactional(rollbackFor = DeleteException.class)
    public void deleteMovies(String movieIds) throws DeleteException {
        String[] idArray = movieIds.split("&");
        List<Integer> idList = new ArrayList<>();
        for (String str : idArray) {
            idList.add(Integer.parseInt(str));
        }
        int affectRows = movieDao.deleteMovies(idList);
        if (affectRows != idList.size()) {
            throw new DeleteException();
        }
    }

    public void updateMovie(Movie movie) {
        movieDao.updateMovie(movie);
    }

    public void insertMovie(Movie movie) {
        movieDao.insertMovie(movie);
    }


}

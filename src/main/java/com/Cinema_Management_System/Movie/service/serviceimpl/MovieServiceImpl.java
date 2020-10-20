package com.Cinema_Management_System.Movie.service.serviceimpl;

import com.Cinema_Management_System.Movie.dao.MovieDao;
import com.Cinema_Management_System.Movie.entity.Movie;
import com.Cinema_Management_System.Movie.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service(value = "MovieService")
public class MovieServiceImpl implements MovieService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MovieDao movieDao;


    public List<Movie> selectMovieAll(Integer pageStart, Integer pageNum) {
        return movieDao.selectMovieAll(pageStart, pageNum);
    }

    public Movie selectMovieByName(String movieName) {
        return movieDao.selectMovieByName(movieName);
    }

    public List<Movie> selectMovieByType(String timeType, String selectType, Integer pageStart, Integer pageNum, Date nowDate) {
        return movieDao.selectMovieByType(timeType, selectType, pageStart, pageNum, nowDate);
    }

    public String deleteMovie(Integer movieId) {
        return movieDao.deleteMovie(movieId);
    }

    public String deleteMovies(List<Integer> movieIds) {
        return movieDao.deleteMovies(movieIds);
    }

    public String updateMovie(Movie movie) {
        return movieDao.updateMovie(movie);
    }

    public String insertMovie(Movie movie) {
        return movieDao.insertMovie(movie);
    }
}

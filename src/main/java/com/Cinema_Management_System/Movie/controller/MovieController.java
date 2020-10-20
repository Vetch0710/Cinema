package com.Cinema_Management_System.Movie.controller;

import com.Cinema_Management_System.Movie.entity.Movie;
import com.Cinema_Management_System.Movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MovieController {

    @Qualifier("MovieService")
    @Autowired
    private MovieService movieService;
    private List<Movie> movieList;

    @ResponseBody
    @RequestMapping("selectMovieAll")
    public List<Movie> selectMovieAll(Integer pageNo, Integer pageNum) {
        Integer pageStart = pageNo * pageNum;
        System.out.println("==================="+pageNo+"============="+pageNum);
        movieList = movieService.selectMovieAll(pageStart, pageNum);
        return movieList;
    }
    
    @ResponseBody
    @RequestMapping("selectMovieByName")
    public Movie selectMovieByName(String movieName){
        return movieService.selectMovieByName(movieName);
    }
}

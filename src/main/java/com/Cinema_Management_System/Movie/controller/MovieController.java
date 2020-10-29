package com.Cinema_Management_System.Movie.controller;

import com.Cinema_Management_System.Movie.entity.Movie;
import com.Cinema_Management_System.Movie.service.MovieService;
import com.Cinema_Management_System.utils.Exception.DeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/movie")
@RestController
public class MovieController {

    @Qualifier("MovieService")
    @Autowired
    private MovieService movieService;
    private List<Movie> movieList;
    private Map<String, Object> returnData;

    @RequestMapping(value = "/movieList", method = RequestMethod.GET)
    public Map<String, Object> getMovieList(String selectType, String permission, Integer pageNo, Integer pageSize) {
        try {
            permission = new String(permission.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {

        }
        Integer pageStart = (pageNo - 1) * pageSize;
        System.out.println("===================" + pageNo + "=============" + pageSize + "=============" + permission);
        movieList = movieService.selectMovieAll(selectType, permission, pageStart, pageSize);
        int count = movieService.getCount(selectType, permission);
        returnData = new HashMap<>();
        returnData.put("data", movieList);
        returnData.put("total", count);
        return returnData;
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
    public void addMovie(@RequestBody Map<String, Movie> movie) {
        movieService.insertMovie(movie.get("movie"));
    }

    @RequestMapping(value = "/{movieId}", method = RequestMethod.DELETE)
    public String deleteMovie(@PathVariable int movieId) {
        try {
            movieService.deleteMovie(movieId);
        } catch (DeleteException e) {
            return "error";
        }
        return "success";
    }

    @RequestMapping(value = "/batchDelete/{movieIds}", method = RequestMethod.DELETE)
    public String deleteMovies(@PathVariable String movieIds) {
        try {
            movieService.deleteMovies(movieIds);
        } catch (DeleteException e) {
            return "error";
        }
        return "success";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public void editMovies(@RequestBody Movie movie) {

        movieService.updateMovie(movie);
    }
}

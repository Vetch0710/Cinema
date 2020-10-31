package com.Cinema_Management_System.Movie.controller;


import com.Cinema_Management_System.Movie.entity.DetailMovie;
import com.Cinema_Management_System.Movie.entity.HitMovie;
import com.Cinema_Management_System.Movie.entity.Movie;
import com.Cinema_Management_System.Movie.entity.UpcomingMovie;
import com.Cinema_Management_System.Movie.service.MovieService;
import com.Cinema_Management_System.utils.Exception.DeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/movie")
@RestController
public class MovieController {

    @Qualifier("MovieService")
    @Autowired
    private MovieService movieService;
    private List<DetailMovie> movieList;
    private Map<String, Object> returnData;
    String image = ".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF";
    String video = ".mp4,.mov,.avi,.wmv,.3gp,.mkv,.flv,.MP4,.MOV,.AVI,.WMV,.3GP,.MKV,.FLV";


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


    @RequestMapping(value = "/indexList", method = RequestMethod.GET)
    public Map<String, Object> getIndexList(String timeType, String selectType, int pageNo, int pageSize) {
        Integer pageStart = (pageNo - 1) * pageSize;
        returnData = new HashMap<>();
        if (timeType.equals("zzry")) {
            List<HitMovie> hitMovies = movieService.selectHitMovie(selectType, pageStart, pageSize);
            returnData.put("data", hitMovies);
            returnData.put("totalCount", movieService.HitMovieCount());
        } else if (timeType.equals("jjsy")) {
            List<UpcomingMovie> upcomingMovies = movieService.selectUpcomingMovie(selectType, pageStart, pageSize);
            returnData.put("data", upcomingMovies);
            returnData.put("totalCount", movieService.UpcomingMovieCount());
        }
        return returnData;
    }

    @RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
    public DetailMovie getDetailMovie(@PathVariable int movieId) {
        return movieService.getDetailMovie(movieId);
    }

    @RequestMapping(value = "/getRelativeMovie", method = RequestMethod.GET)
    public List<Movie> getRelativeMovie(String movieType, String movieActor, String movieDirector) {
        try {
            movieType = new String(movieType.getBytes("ISO-8859-1"), "UTF-8");
            movieActor = new String(movieActor.getBytes("ISO-8859-1"), "UTF-8");
            movieDirector = new String(movieDirector.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        System.out.println(movieType + "=======" + movieActor + "=======" + movieDirector);
        return movieService.getRelativeMovie(movieType, movieActor, movieDirector);
    }

    @RequestMapping(value = "/getFlag/{movieId}", method = RequestMethod.GET)
    public boolean getFlag(@PathVariable int movieId, HttpServletRequest request) {
        System.out.println(request.getHeader("accessToken"));
       try {
           String[] str = (request.getHeader("accessToken").split("-"));
           int customerId = Integer.parseInt(str[0]);
           return movieService.getFlag(movieId, customerId);
       }catch (NullPointerException e){
           return false;
       }
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
    public void editMovies(@RequestBody DetailMovie movie) {

        movieService.updateMovie(movie);
    }

    @RequestMapping(value = "/changeWant/{wantFlag}/{movieId}",method = RequestMethod.PUT)
    public void changeWant(@PathVariable int wantFlag,@PathVariable int movieId,HttpServletRequest request){
        System.out.println(request.getHeader("accessToken"));
        String[] str = (request.getHeader("accessToken").split("-"));
        int customerId = Integer.parseInt(str[0]);
        movieService.changeWant(wantFlag,movieId,customerId);
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
    public void addMovie(@RequestBody Map<String, DetailMovie> movie) {
        movieService.insertMovie(movie.get("movie"));
    }


    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upImg(MultipartFile file, HttpServletRequest request) {
        //获取文件在服务器的储存位置
        String path = "";

        //获取原始文件名称(包含格式)
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf("."));
        System.out.println("文件类型：" + type);
        String director = "";
        if (image.indexOf(type) != -1) {
            director = "img";
        } else if (video.indexOf(type) != -1) {
            director = "video";
        } else {
            System.out.println("上传失败！文件类型不符合！");
            return "上传失败！文件类型不符合！请重新上传";
        }
        path = request.getSession().getServletContext().getRealPath("/" + director);
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }
        //获取文件名称（不包含格式）
        String originName = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + originName + type;
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            file.transferTo(targetFile);
            System.out.println("上传成功");
            //将文件在服务器的存储路径返回
            return "http://localhost:8080/Cinema_Management_System/" + director + "/" + fileName;
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return null;
        }
    }
}

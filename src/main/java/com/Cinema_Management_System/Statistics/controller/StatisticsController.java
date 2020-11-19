package com.Cinema_Management_System.Statistics.controller;

import com.Cinema_Management_System.Statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

@Controller
public class StatisticsController {

    @Qualifier("ManagerService")
    @Autowired
    private StatisticsService statisticsService;

    //按月份
    @RequestMapping(value = "/getStatistics",method = {RequestMethod.GET})
    @ResponseBody
    public  List<Float> getStatistics(String yearMonth) throws ParseException {
        System.out.println(yearMonth);
        List<Float> statistics = statisticsService.selectStaByMonth(yearMonth);
        System.out.println(statistics);
        return statistics;
    }
//按年份
    @RequestMapping(value = "/getStatisticsYear",method = {RequestMethod.GET})
    @ResponseBody
    public  List<Float> getStatisticsYear(String yearMonth) throws ParseException {
        System.out.println(yearMonth);
        List<Float> statistics = statisticsService.selectStaByYear(yearMonth);
        System.out.println(statistics);
        return statistics;
    }
}

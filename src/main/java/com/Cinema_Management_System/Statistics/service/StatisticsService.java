package com.Cinema_Management_System.Statistics.service;

import com.Cinema_Management_System.Statistics.entity.Statistics;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatisticsService {
    List<Float> selectStaByMonth(String day);
}

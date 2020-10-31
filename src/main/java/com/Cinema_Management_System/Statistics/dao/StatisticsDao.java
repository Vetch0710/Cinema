package com.Cinema_Management_System.Statistics.dao;

import com.Cinema_Management_System.Statistics.entity.Statistics;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StatisticsDao {
    List<Float> selectStaByMonth(String day);
}

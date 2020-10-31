package com.Cinema_Management_System.Arrangement.service;


import com.Cinema_Management_System.Arrangement.entity.Arrangement;
import com.Cinema_Management_System.Movie.entity.ArrangementMovie;
import com.Cinema_Management_System.utils.Exception.DeleteException;

import java.util.List;
import java.util.Map;

public interface ArrangementService {
    Arrangement getArrange(int arrangementId);

    List<Arrangement> getArranges(String selectType, String permission, Integer pageNo, Integer pageSize);

    int getCount(String selectType, String permission);

    void addArrangement(Arrangement arrangement);

    void deleteArrangement(int arrangementId) throws DeleteException;

    void deleteArrangements(String movieIds) throws DeleteException;

    void updateArrangement(Arrangement arrangement) throws DeleteException;

    List<String> getDisableTime(int arrangementPlace, String arrangementDate,int movieId);

    Map<String,Object> getArrangeMovie(int movieId);

    Map<String,Object> getSeatInfo(int arrangementId);
}

package com.Cinema_Management_System.Arrangement.dao;


import com.Cinema_Management_System.Arrangement.entity.Arrangement;
import com.Cinema_Management_System.Arrangement.entity.UsedTime;
import com.Cinema_Management_System.Movie.entity.ArrangementMovie;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArrangementDao {
    Arrangement getArrange(int arrangementId);

    List<Arrangement> getArranges(@Param("selType") String selType, @Param("selContent") String selContent, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    int getCount(@Param("selType") String selType, @Param("selContent") String selContent);

    String getMovieTime(@Param("movieId") int movieId);

    int addArrangement(Arrangement arrangement);

    int deleteArrangement(int arrangementId);

    int deleteArrangements(List<Integer> idList);

    int updateArrangement(Arrangement arrangement);

    List<UsedTime> getDisableTime(@Param("dayStart") String dayStart, @Param("dayEnd") String dayEnd, @Param("arrangementPlace") int arrangementPlace);

    ArrangementMovie getArrangeMovie(@Param("movieId") int movieId);

    List<Arrangement> getNowArranges(int movieId);

    ArrangementMovie getMovieInfo(@Param("arrangementId")int arrangementId);

    String getSelectedSeats(@Param("arrangementId")int arrangementId);
}

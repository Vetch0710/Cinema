package com.Cinema_Management_System.Evaluation.dao;

import com.Cinema_Management_System.Evaluation.entity.Evaluation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EvaluationDao {
    List<Evaluation> getEvaluations(@Param("selType") String selType, @Param("selContent") String selContent, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    int getCount(@Param("selType") String selType, @Param("selContent") String selContent);

    List<Evaluation> getMovieEvaluation(@Param("movieId") int movieId, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    int getMovieEvaluationCount(@Param("movieId") int movieId);

    List<Evaluation> selectAllEvaluation(@Param("customerId")Integer id);
    int updateEvaluation(Evaluation evaluation);
    int updateOrderStatus(int id);
}

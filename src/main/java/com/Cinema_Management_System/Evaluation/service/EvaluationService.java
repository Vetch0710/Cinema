package com.Cinema_Management_System.Evaluation.service;

import com.Cinema_Management_System.Evaluation.entity.Evaluation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EvaluationService {
    List<Evaluation> getEvaluations(String selectType, String permission, Integer pageStart, Integer pageSize);

    int getCount(String selectType, String permission);

    List<Evaluation> getMovieEvaluation(int movieId, Integer pageStart, Integer pageSize);

    int getMovieEvaluationCount(int movieId);
    //Book getById(long bookId);
}

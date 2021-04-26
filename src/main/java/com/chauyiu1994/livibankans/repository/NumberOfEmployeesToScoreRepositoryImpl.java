package com.chauyiu1994.livibankans.repository;

import com.chauyiu1994.livibankans.context.ScoreContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.TreeMap;

@Component
@AllArgsConstructor
public class NumberOfEmployeesToScoreRepositoryImpl implements NumberOfEmployeesToScoreRepository {

    private ScoreContext scoreContext;

    @Override
    public int getScoreByNumberOfEmployees(int numberOfEmployees) {

        TreeMap<Integer, Integer> scoreMap = scoreContext.getNumberOfEmployees();
        return scoreMap.get(scoreMap.floorKey(numberOfEmployees));
    }
}

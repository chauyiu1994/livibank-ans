package com.chauyiu1994.livibankans.repository;

import com.chauyiu1994.livibankans.context.ScoreContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.TreeMap;

@Component
@AllArgsConstructor
public class NumberOfYearsOperatedToScoreRepositoryImpl implements NumberOfYearsOperatedToScoreRepository {

    private ScoreContext scoreContext;

    @Override
    public int getScoreByNumberOfYearsOperated(int numberOfYearsOperated) {

        TreeMap<Integer, Integer> scoreMap = scoreContext.getNumberOfYearsOperated();
        return scoreMap.get(scoreMap.floorKey(numberOfYearsOperated));
    }
}

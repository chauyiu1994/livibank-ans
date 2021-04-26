package com.chauyiu1994.livibankans.repository;

import com.chauyiu1994.livibankans.context.ScoreContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CompanyTypeToScoreRepositoryImpl implements CompanyTypeToScoreRepository {

    private ScoreContext scoreContext;

    @Override
    public int getScoreByCompanyType(String companyType) {
        return scoreContext.getCompanyType().get(companyType);
    }
}

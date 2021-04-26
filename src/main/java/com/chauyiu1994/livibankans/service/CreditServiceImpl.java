package com.chauyiu1994.livibankans.service;

import com.chauyiu1994.livibankans.models.CreditRequestModel;
import com.chauyiu1994.livibankans.repository.CompanyTypeToScoreRepository;
import com.chauyiu1994.livibankans.repository.NumberOfEmployeesToScoreRepository;
import com.chauyiu1994.livibankans.repository.NumberOfYearsOperatedToScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreditServiceImpl implements CreditService {

    private NumberOfEmployeesToScoreRepository numberOfEmployeesToScoreRepository;
    private CompanyTypeToScoreRepository companyTypeToScoreRepository;
    private NumberOfYearsOperatedToScoreRepository numberOfYearsOperatedToScoreRepository;

    @Override
    public int calculateCredit(CreditRequestModel credit) {

        return
                numberOfEmployeesToScoreRepository.getScoreByNumberOfEmployees(credit.getNumberOfEmployees())
                + companyTypeToScoreRepository.getScoreByCompanyType(credit.getCompanyType())
                + numberOfYearsOperatedToScoreRepository.getScoreByNumberOfYearsOperated(credit.getNumberOfYearsOperated());
    }
}

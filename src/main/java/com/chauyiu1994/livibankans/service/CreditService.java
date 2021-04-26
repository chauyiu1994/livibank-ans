package com.chauyiu1994.livibankans.service;


import com.chauyiu1994.livibankans.models.CreditRequestModel;
import org.springframework.security.access.prepost.PreAuthorize;

public interface CreditService {

    @PreAuthorize("hasAnyRole(T(com.chauyiu1994.livibankans.security.Roles).SCORE_CALCULATOR)")
    int calculateCredit(CreditRequestModel credit);
}

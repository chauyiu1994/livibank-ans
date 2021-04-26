package com.chauyiu1994.livibankans.controller.impl;

import com.chauyiu1994.livibankans.controller.CreditController;
import com.chauyiu1994.livibankans.models.CreditRequestModel;
import com.chauyiu1994.livibankans.models.CreditResponseModel;
import com.chauyiu1994.livibankans.service.CreditService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
public class CreditControllerImpl implements CreditController {

    private CreditService creditService;

    public ResponseEntity<CreditResponseModel> calculateCredit(CreditRequestModel creditRequestModel) {

        int creditScore = creditService.calculateCredit(creditRequestModel);
        return ResponseEntity.ok(new CreditResponseModel(creditScore));
    }


}

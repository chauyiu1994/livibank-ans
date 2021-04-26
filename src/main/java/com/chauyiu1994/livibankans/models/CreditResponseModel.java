package com.chauyiu1994.livibankans.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreditResponseModel {

    @Schema(example = "7")
    private int creditScore;
}

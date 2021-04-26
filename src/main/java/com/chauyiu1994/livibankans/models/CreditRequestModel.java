package com.chauyiu1994.livibankans.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@Getter
@Validated
public class CreditRequestModel {

    @Min(value = 1, message = "Min numberOfEmployees is 1")
    @NotNull
    @Schema(example = "6")
    private int numberOfEmployees;

    @NotEmpty(message = "companyType is required")
    @Schema(example = "Sole Proprietorship")
    private String companyType;

    @Min(value = 0, message = "Min numberOfYearsOperated is 0")
    @NotNull
    @Schema(example = "5")
    private int numberOfYearsOperated;
}

package com.chauyiu1994.livibankans.controller;

import com.chauyiu1994.livibankans.models.CreditRequestModel;
import com.chauyiu1994.livibankans.models.CreditResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Tag(name = "credit-endpoints")
@RequestMapping("/creditservice/v1")
public interface CreditController {

    @Parameters({
            @Parameter(name = "Authorization", required = true, description = "Authorization token, either JWT or access token, the CALCULATOR Role is required", in = ParameterIn.HEADER, examples = {
                    @ExampleObject(name = "Access Token", description = "access token, currently only support the Github provider", value = "Github Access Token $token"),
                    @ExampleObject(name = "JWT Token", description = "JWT token, retrieved from the authenticate endpoint", value = "Bearer $token"),
            })
    })
    @Operation(summary = "calculate credit score")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "input invalid", content = @Content(schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "401", description = "unauthenticated", content = @Content(schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "403", description = "unauthorized", content = @Content(schema = @Schema(implementation = Object.class)))
    })
    @PostMapping(value = "/calculator", produces = { "application/json" })
    ResponseEntity<CreditResponseModel> calculateCredit(@Valid @RequestBody CreditRequestModel creditRequestModel);
}

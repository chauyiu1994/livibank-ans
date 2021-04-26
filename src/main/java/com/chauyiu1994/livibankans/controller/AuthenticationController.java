package com.chauyiu1994.livibankans.controller;

import com.chauyiu1994.livibankans.models.JwtRequest;
import com.chauyiu1994.livibankans.models.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Tag(name = "authentication")
public interface AuthenticationController {

    @Operation(summary = "authenticate using username and password, return jwt token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "input invalid", content = @Content(schema = @Schema(implementation = Object.class))),
    })
    @PostMapping(value = "/authenticate", produces = { "application/json" })
    ResponseEntity<JwtResponse> createAuthenticationToken(@Valid @RequestBody JwtRequest authenticationRequest) throws Exception;
}

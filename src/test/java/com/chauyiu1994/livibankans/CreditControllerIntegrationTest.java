package com.chauyiu1994.livibankans;

import com.chauyiu1994.livibankans.models.CreditRequestModel;
import com.chauyiu1994.livibankans.models.CreditResponseModel;
import com.chauyiu1994.livibankans.models.JwtRequest;
import com.chauyiu1994.livibankans.models.JwtResponse;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class CreditControllerIntegrationTest extends SpringIntegrationTest {

    @LocalServerPort
    private int randomServerPort;

    private String testHost;
    private String bearerToken;
    private ResponseEntity<CreditResponseModel> response;
    private int errorStatusCode;
    RestTemplate restTemplate = new RestTemplate();

    @Before
    public void init() {
        this.restTemplate = new RestTemplate();
        this.testHost = "http://localhost:" + randomServerPort;
    }

    @After
    public void cleanUp() {
        bearerToken = null;
        response = null;
        errorStatusCode = 0;
    }

    @Given("The User is authenticated with the calculator role")
    public void theUserIsAuthenticatedWithTheCalculatorRole() {

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUsername("normal");
        jwtRequest.setPassword("password");

        ResponseEntity<JwtResponse> jwtResponseResponseEntity = restTemplate.postForEntity(testHost + "/authenticate", jwtRequest, JwtResponse.class);

        assertNotNull(jwtResponseResponseEntity.getBody());
        bearerToken = jwtResponseResponseEntity.getBody().getJwttoken();
    }

    @Given("The User is authenticated without the calculator role")
    public void theUserIsAuthenticatedWithoutTheCalculatorRole() {

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUsername("hello");
        jwtRequest.setPassword("password");

        ResponseEntity<JwtResponse> jwtResponseResponseEntity = restTemplate.postForEntity(testHost + "/authenticate", jwtRequest, JwtResponse.class);

        assertNotNull(jwtResponseResponseEntity.getBody());
        bearerToken = jwtResponseResponseEntity.getBody().getJwttoken();
    }

    @When("The calculateCreditAssessmentScore API is called with {int}, {string}, {int}")
    public void whenTheCalculateCreditAssessmentScoreAPIIsCalledWith(
            int numberOfEmployees,
            String companyType,
            int numberOfYearsOperated
    ) {

        CreditRequestModel creditRequestModel = new CreditRequestModel(numberOfEmployees, companyType, numberOfYearsOperated);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(bearerToken);

        HttpEntity<CreditRequestModel> requestEntity = new HttpEntity<>(creditRequestModel, headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            response = restTemplate.exchange("http://localhost:" + randomServerPort + "/creditservice/v1/calculator", HttpMethod.POST, requestEntity, CreditResponseModel.class);
        } catch (HttpStatusCodeException e) {
            errorStatusCode = e.getRawStatusCode();
        }
    }

    @Then("The credit assessment score should match {int}")
    public void theCreditAssessmentScoreShouldMatch(int creditScore) {

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(creditScore, response.getBody().getCreditScore());
    }

    @Then("The response http status code should be {int}")
    public void theResponseHttpStatusCodeShouldBe(int statusCode) {

        assertEquals(statusCode, errorStatusCode);
    }

}

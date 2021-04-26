Feature: Credit Assessment Calculator

  Scenario: Calculate Credit Assessment Score Success 1
    Given The User is authenticated with the calculator role
    When The calculateCreditAssessmentScore API is called with 6, "Sole Proprietorship", 5
    Then The credit assessment score should match 7

  Scenario: Calculate Credit Assessment Score Success 2
    Given The User is authenticated with the calculator role
    When The calculateCreditAssessmentScore API is called with 10, "Limited Liability Company", 8
    Then The credit assessment score should match 13

  Scenario: Calculate Credit Assessment Score Input Number of Employee Lower than 1
    Given The User is authenticated with the calculator role
    When The calculateCreditAssessmentScore API is called with 0, "Limited Liability Company", 8
    Then The response http status code should be 400

  Scenario: Calculate Credit Assessment Score Input Company Type is empty
    Given The User is authenticated with the calculator role
    When The calculateCreditAssessmentScore API is called with 10, "", 8
    Then The response http status code should be 400

  Scenario: Calculate Credit Assessment Score Input Number of Years Operated Lower than 0
    Given The User is authenticated with the calculator role
    When The calculateCreditAssessmentScore API is called with 10, "Limited Liability Company", -1
    Then The response http status code should be 400

  Scenario: Calculate Credit Assessment Score Input Number of Years Operated Lower than 0
    When The calculateCreditAssessmentScore API is called with 10, "Limited Liability Company", 8
    Then The response http status code should be 401

  Scenario: Calculate Credit Assessment Score Input Number of Years Operated Lower than 0
    Given The User is authenticated without the calculator role
    When The calculateCreditAssessmentScore API is called with 10, "Limited Liability Company", 8
    Then The response http status code should be 403

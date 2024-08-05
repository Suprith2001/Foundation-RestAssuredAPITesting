Feature: restful api test

  Scenario: verify user is able to create a token
    When user wants to call "/auth" endpoint
    And set "Content-Type" to "application/json"
    And send body from file "/createToken.json"
    And user perform post call
    Then verify status code is 200
    And store token as "token.id"

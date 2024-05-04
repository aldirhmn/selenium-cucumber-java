Feature: Login Functionality

Scenario: Login Successful with Valid Credentials
    Given User open browser and navigate to Url
    When User input username with "tomsmith" and password with "SuperSecretPassword!"
    And User click button Login
    Then User successfully direct to HomePage

Scenario: Failed Login with Invalid Credentials
    Given User open browser and navigate to Url
    When User input username with "johndoe" and password with "WrongSecretPassword"
    And User click button Login
    Then User failed to Login and get Error Message
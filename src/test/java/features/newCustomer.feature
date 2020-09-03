Feature: newCustomer

  In order to create new customer
  As a Bank Manager
  I wan to create new user and open new account

  Scenario Outline: In order to verify create new customer
    Given As a Bank Manager I want to log in on website "http://www.way2automation.com/angularjs-protractor/banking/#/login"
    When I create new customer with data "<firstName>", "<lastName>" and "<postCode>"
    And I create new account for "<firstName>" "<lastName>" in "<currency>"
    Then New customer "<firstName>" "<lastName>", post code: "<postCode>" and new account is in customers table
    Examples:
      |firstName  |lastName |postCode | currency  |
      |Anna       |Kowalska |123456   |Dollar     |
      |Jan        |Nowak    |223344   |Pound      |
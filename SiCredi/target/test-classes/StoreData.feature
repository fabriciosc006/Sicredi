
Feature: Store Data

  Scenario: Store Data sucessfull in Database
    Given acess database Url
    And Select Bootstrap V4 Theme
    And add costumer
    When enter with data user informations
    And save
    Then check data stored sucessfully
    
  
  Scenario: Delete a Data previous stored
  
  Given acess database Url
    And Select Bootstrap V4 Theme
    And add costumer
    When enter with data user informations
    And click save and go back to list
    And search for Teste Sicred
    And Click in actions
    And Delete data
    Then user confirm if delete item
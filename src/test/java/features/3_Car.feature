Feature: Car rentals scenarios include next cases:

  @Flights
  Scenario Outline: Cars positive scenario

    Given a user reads test data from "TestData[GoogleSheets]" "Cars" by id "<TC_ID>"
    And the booking page is opened
    And the language is chosen
    And the booking car page is opened
    When a user enters pickup location
    And a user clicks on calendar
    When a user add pickup and dropoff time
    And a user click search car button

    Examples:
      | TC_ID  |
      | TC_003 |

  @Flights
  Scenario Outline: Cars negative scenario when pickup location is not fulfilled

    Given a user reads test data from "TestData[GoogleSheets]" "Cars" by id "<TC_ID>"
    And the booking page is opened
    And the language is chosen
    And the booking car page is opened
    And a user click search car button
    Then the pickup location is empty, search is not possible should be verified

    Examples:
      | TC_ID  |
      | TC_001 |

  @Flights
  Scenario Outline: Cars negative scenario when ages are beyond of valid range

    Given a user reads test data from "TestData[GoogleSheets]" "Cars" by id "<TC_ID>"
    And the booking page is opened
    And the language is chosen
    And the booking car page is opened
    When a user enters pickup location
    And I choose invalid age
    And a user click search car button
    Then the ages are beyond of valid range, search is not possible should be verified

    Examples:
      | TC_ID  |
      | TC_002 |


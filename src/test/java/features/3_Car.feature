Feature: Car rentals scenarios include next cases:

  @Flights
  Scenario Outline: Cars

    Given I read test data from "Booking" "Flights" by id "<TC_ID>"
    Given I verify that the booking page is open
    And I choose language
    Given I am on the booking car page
    When I enter pickup location
    And I choose pickup date


    Examples:
      | TC_ID  |
      | FL_001 |

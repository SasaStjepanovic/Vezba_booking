Feature: Car rentals scenarios include next cases:

  @Flights
  Scenario Outline: Cars

    Given I read test data from "Booking" "Flights" by id "<TC_ID>"
    Given I verify that the booking page is open
    And I choose language
    Given I am on the booking car page
    When I enter pickup location
    And I choose calendar
    And I choose pickup and dropoff time
    And I choose search option

    Examples:
      | TC_ID  |
      | FL_004 |

  @Flights
  Scenario Outline: Cars negative scenario when pickup location is not fulfilled

    Given I read test data from "Booking" "Cars" by id "<TC_ID>"
    Given I verify that the booking page is open
    And I choose language
    Given I am on the booking car page
    And I verify that I on car page
    And I choose search option
    And I verify search is not possible

    Examples:
      | TC_ID  |
      | TC_001 |

  @Flights
  Scenario Outline: Cars negative scenario when ages are beyond of valid range

    Given I read test data from "Booking" "Cars" by id "<TC_ID>"
    Given I verify that the booking page is open
    And I choose language
    Given I am on the booking car page
    And I verify that I on car page
    When I enter pickup location
    And I choose invalid age
    And I choose search option
    And I verify ages are beyond of valid range

    Examples:
      | TC_ID  |
      | TC_001 |


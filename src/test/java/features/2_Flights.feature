Feature: Flights scenarios include next cases: Round-trip, One-way and Multi-city

  @Flights
  Scenario Outline: Book a Round-trip destination flight

    Given I read test data from "Booking" "Flights" by row "<row>"
    Given I verify that the booking page is open
    And I choose language
    Given I am on the booking flights page
    And I enter destination for round flight
    And Check date for round flight
    And I click search flights button
    Then I verify round results

    Examples:
      | row  |
      |  1   |

  @Flights
  Scenario Outline: Book a multiple destination flight

    Given I read test data from "Booking" "Flights" by id "<TC_ID>"
    Given I verify that the booking page is open
    And I choose language
    Given I am on the booking flights page
    And I verify that I on flights page
    And I select multiple destination option
    And I enter destinations
    And I click search flights button
    Then I verify multiple results



    Examples:
      | TC_ID  |
      | FL_001 |


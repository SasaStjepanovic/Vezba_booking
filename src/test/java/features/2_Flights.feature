#Feature: Flights scenarios include next cases: Round-trip, One-way, Multi-city, different flight classes
#
#  @Flights
#  Scenario Outline: Book a Round-trip destination flight
#
#    Given I read test data from "Booking" "Flights" by row "<row>"
#    Given I verify that the booking page is open
#    And I choose language
#    Given I am on the booking flights page
#    And I verify that I on flights page
#    And Add adults and children
#    And Check date for round flight
#    And I enter destination for round flight
#    And I click search flights button
#    Then I verify round results
#
#    Examples:
#      | row  |
#      |  3   |
#
#  @Flights
#  Scenario Outline: Book a one-way destination flight
#
#    Given I read test data from "Booking" "Flights" by id "<TC_ID>"
#    Given I verify that the booking page is open
#    And I choose language
#    Given I am on the booking flights page
#    And I verify that I on flights page
#    And I enter destination for one way flight
#    And Check data for one way flight
#    And I click search flights button
#    Then I verify one way results
#
#    Examples:
#      | TC_ID  |
#      | FL_001 |
#
#  @Flights
#  Scenario Outline: Book a multiple destination flight
#
#    Given I read test data from "Booking" "Flights" by id "<TC_ID>"
#    Given I verify that the booking page is open
#    And I choose language
#    Given I am on the booking flights page
#    And I verify that I on flights page
#    And I select multiple destination option
#    And I enter destinations
#    And I click search flights button
#    Then I verify multiple results
#
#    Examples:
#      | TC_ID  |
#      | FL_001 |
#
#  @Flights
#  Scenario Outline: Choose different flight class
#
#    Given I read test data from "Booking" "Flights" by row "<row>"
#    Given I verify that the booking page is open
#    And I choose language
#    Given I am on the booking flights page
#    And I verify that I on flights page
#    And I enter destination for round flight
#    And Check date for round flight
#    And I verify flight class I
#    And I click search flights button
#    Then I verify round results
#    And I change flight class
#    And I verify flight class II
#    And I click search flights button
#    Then I verify negative round results
#    And I change flight class III
#    And I verify flight class III
#    And I click search flights button
#    And I change flight class IV
#    And I click search flights button
#    And I verify negative round results II
#
#    Examples:
#      | row  |
#      |   3  |
#

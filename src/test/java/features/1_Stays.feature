#Feature: Stays scenarios include next cases: Set location, start/end date, number of adults/children, secr button and result verification
#
#  @Flights
#  Scenario Outline: Book a stays
#
#    Given I read test data from "Booking" "Stays" by id "<TC_ID>"
#    Given I verify that the booking page is open
#    And I choose language
#    When I enter destination location
#    And I enter check in date
#    And I enter check out date
#    And I add adults
#    And I add children
#    And I add rooms
#    And I click search button
#    And I verify found stays
#
#
#    Examples:
#      | TC_ID  |
#      | TC_001 |
#      | TC_002 |
#

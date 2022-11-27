Feature: Stays scenarios include next cases: Set location, start/end date, number of adults/children, secr button and result verification

  @Flights
  Scenario Outline: Book a stays

    Given a user reads test data from "Booking[Excel]" "Stays" by id "<TC_ID>"
    And the booking page is opened
    And the language is chosen
    When a user enters destination location
    And a user enters check in and checkout date
    And a user add number of adults and children
    And a user add number of rooms
    And a user click search button
    Then stay should be verified

    Examples:
      | TC_ID  |
      | TC_003 |
      | TC_002 |


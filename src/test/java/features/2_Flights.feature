Feature: Flights scenarios include next cases: Round-trip, One-way, Multi-city, different flight classes

  @Flights
  Scenario Outline: Book a Round-trip destination flight
    Given a user reads test data from "Booking[Excel]" "Flights" by row "<row>"
    And the booking page is opened
    And the language is chosen
    And the booking flight page is opened
    When a user add number of adult and children at the flights card
    When a user enters check in and checkout date for flights card
    And a user enters destination location for round-trip on flights card
    And a user clicks search button on the flights card
    Then round-trip should be verified

    Examples:
      | row  |
      |  3   |

  @Flights
  Scenario Outline: Book a one-way destination flight

    Given a user reads test data from "Booking[Excel]" "Flights" by row "<row>"
    And the booking page is opened
    And the language is chosen
    And the booking flight page is opened
    When a user enters destination location for one-way on flights card
    When a user sets date for one-way on flights card
    And a user clicks search button on the flights card
    Then one-way should be verified

    Examples:
      | row  |
      | 1 |

  @Flights
  Scenario Outline: Book a multiple destination flight

    Given a user reads test data from "Booking[Excel]" "Flights" by row "<row>"
    And the booking page is opened
    And the language is chosen
    And the booking flight page is opened
    And a user chosen multiple option
    When a user add destinations
    Then multiple should be verified

    Examples:
      | row  |
      | 1 |

  @Flights
  Scenario Outline: Choose different flight class

    Given a user reads test data from "Booking[Excel]" "Flights" by row "<row>"
    And the booking page is opened
    And the language is chosen
    And the booking flight page is opened
    When a user add number of adult and children at the flights card
    When a user enters check in and checkout date for flights card
    And a user enters destination location for round-trip on flights card
    And a user chosen I class
    And a user clicks search button on the flights card
    When a user chosen II class
    And a user clicks search button on the flights card
    And chosen II clas should be verified
    When a user chosen III class
    And a user clicks search button on the flights card
    And chosen III clas should be verified
    When a user chosen IV class
    And a user clicks search button on the flights card
    Then chosen IV clas should be verified

    Examples:
      | row  |
      |   3  |


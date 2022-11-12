Feature: Flights scenarios include next cases: Round-trip, One-way and Multi-city

  @Flights
  Scenario Outline: Book a Round-trip destination flight

    Given I read test data from "Booking" "Flights" by id "<TC_ID>"


    Examples:
      | TC_ID  |
      | FL_001 |


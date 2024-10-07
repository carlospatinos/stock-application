Feature: Retrieve Stocks from API
  As a user
  I want to request stocks from the app
  So that I know which stocks are available
  Scenario: Get stocks from the API
    When I call stocks "/stocks"
    Then a list should be returned aslist
    And the response code is 200

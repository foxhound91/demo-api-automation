Feature: Short url redirect Test

  Scenario: Short url redirects to valid location
    Given a valid short url request
    When the request is executed
    Then it redirect to the correct location

  Scenario: HttpClient does not redirect location (MOVED_PERMANENTLY response)
    Given a valid short url request
    When the request is executed without redirect
    Then the response status is "301"
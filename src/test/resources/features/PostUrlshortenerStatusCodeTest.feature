Feature: Post urlshortener response Status Test

  Scenario: Post request without key response FORBIDDEN
    Given a urlshortener post request without key
    When the request is executed
    Then the response status is "403"

  Scenario: Post request without body response is BAD_REQUEST
    Given a urlshortener post request without body
    When the request is executed
    Then the response status is "400"

  Scenario: Post request without header response is BAD_REQUEST
    Given a urlshortener post request without header
    When the request is executed
    Then the response status is "400"

  Scenario: Post request with valid data response is OK
    Given a valid urlshortener post request
    When the request is executed
    Then the response status is "200"
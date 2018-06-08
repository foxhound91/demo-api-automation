Feature: Get urlshortener response Status Test

  Scenario: Get request without key response FORBIDDEN
    Given a urlshortener get request without key
    When the request is executed
    Then the response status is "403"

  Scenario: Get request without key response is BAD_REQUEST
    Given a urlshortener get request with empty key
    When the request is executed
    Then the response status is "400"

  Scenario: Get request without shortUrl response is BAD_REQUEST
    Given a urlshortener get request without shortUrl
    When the request is executed
    Then the response status is "400"

  Scenario: Get request with valid data response is OK
    Given a valid urlshortener get request
    When the request is executed
    Then the response status is "200"
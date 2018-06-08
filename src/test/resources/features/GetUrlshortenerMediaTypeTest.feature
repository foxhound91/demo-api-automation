Feature: Get urlshortener MediaType Test

  Scenario: Get request without header retrieves correct content type
    Given a valid urlshortener get request
    When the request is executed
    Then the default response content type is Json
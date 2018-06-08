Feature: Post urlshortener MediaType Test

  Scenario: Post request without header retrieves Json content
    Given a urlshortener post request without header
    When the request is executed
    Then the default response content type is Json

  Scenario: Post request with wrong header retrieves Json content
    Given a urlshortener post request with wrong header
    When the request is executed
    Then the default response content type is Json
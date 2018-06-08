Feature: Post urlshortener Payload Test

  Scenario: Post request response retrieves valid kind
    Given a valid urlshortener post request
    When the request is executed
    Then the retrieved resource content is valid
      | property | kind             |
      | value    | urlshortener#url |

  Scenario: Post request response retrieves valid id
    Given a valid urlshortener post request
    When the request is executed
    Then the retrieved resource content is valid
      | property | id                  |
      | value    | https://goo.gl/fbsS |

  Scenario: Post request response retrieves valid longUrl
    Given a valid urlshortener post request
    When the request is executed
    Then the retrieved resource content is valid
      | property | longUrl                |
      | value    | http://www.google.com/ |
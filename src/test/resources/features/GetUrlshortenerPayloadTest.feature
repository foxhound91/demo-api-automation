Feature: Get urlshortener Payload Test

  Scenario: Get request retrieves valid kind
    Given a valid urlshortener get request
    When the request is executed
    Then the retrieved resource content is valid
      | property | kind             |
      | value    | urlshortener#url |

  Scenario: Get request retrieves valid id
    Given a valid urlshortener get request
    When the request is executed
    Then the retrieved resource content is valid
      | property | id                 |
      | value    | http://goo.gl/fbsS |

  Scenario: Get request retrieves valid longUrl
    Given a valid urlshortener get request
    When the request is executed
    Then the retrieved resource content is valid
      | property | longUrl                |
      | value    | http://www.google.com/ |

  Scenario: Get request retrieves valid status
    Given a valid urlshortener get request
    When the request is executed
    Then the retrieved resource content is valid
      | property | status |
      | value    | OK     |

  Scenario: Get invalid request retrieves correct status
    Given an invalid urlshortener get request
    When the request is executed
    Then the retrieved resource content is valid
      | property | status  |
      | value    | REMOVED |

#  Scenario: Get fishy request retrieves valid status
#    Given an invalid urlshortener get request
#    When the request is executed
#    Then the retrieved resource content is valid
#      | property | status  |
#      | value    | MALWARE |
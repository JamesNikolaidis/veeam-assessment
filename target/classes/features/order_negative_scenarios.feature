Feature: Create and manage orders scenarios - Negative paths


#  This scenario will always fail but it shouldn;t.
#  The correct outcome of this scenario should be that the step
#  "the user verifies that the order has not been placed "
#  will send a post request by passing -1 as storeId and -1 as petId and invalid values to the other parameters.
#  In real world scenario, the response should have 400 invalid request but swagger has safe point where converts the invalid values to valid

  Scenario: Create new order with invalid pet
    Then the user stores an invalid order
    And  the user verifies that the order has not been placed

  Scenario: Try to fetch not existing order and check response
    Given  the user request for an invalid order
    Then   the user verifies that no results found

  Scenario: Try to fetch not existing order and check response
    Given  the user request for out of bound order id
    Then   the user verifies that no results found

  Scenario: Try to delete a non existing order and check response
    Given  the user request to delete an order with non existing id
    Then   the user verifies that no results found

#    For this scenario, i tried to delete an order by providing the text "test" which is not an valid id
#    Normally, since the provided id is not a valid integer, based on the swagger web page the endpoint should return
#    Code	 | Description
#    400  | Invalid ID supplied
#    ==========================
#    but the actual response is
#    "code": 404,
#    "type": "unknown",
#    "message": "java.lang.NumberFormatException: For input string: \"test\""
#    I've tried different type of values to achieve the 400 response such as -1, 1.0 , "test" but in all cases the response is the same as above

  Scenario: Try to delete an order using invalid order id
    Given  the user request to delete an order with invalid id
    Then   the user verifies that no results found due to invalid order id


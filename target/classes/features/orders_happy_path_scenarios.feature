Feature: Create and manage orders scenarios - Happy paths


  Background: Create new pet for the orders
    Given the user creates a new pet
    |category_name|name          |photo_url |tag_name|
    |pet_category |SwaggerTestPet|test_image|TestPet |
    Then the user verifies that the pet "SwaggerTestPet" has been created

  Scenario: Create new order with placed status
    Given the user verifies that a pet exists
    Then the user stores a new order for the Pet "SwaggerTestPet" with status "placed" and quantity "5"
    And  the user verifies that the order has been placed correctly

  Scenario: Create new order with approved status
    Given the user verifies that a pet exists
    Then the user stores a new order for the Pet "SwaggerTestPet" with status "approved" and quantity "5"
    And  the user verifies that the order has been placed correctly

  Scenario: Create new order with delivered status
    Given the user verifies that a pet exists
    Then the user stores a new order for the Pet "SwaggerTestPet" with status "delivered" and quantity "5"
    And  the user verifies that the order has been placed correctly

  Scenario: Fetch existing order
    Given the user verifies that a pet exists
    And  the user request the order for the Pet "SwaggerTestPet"
    Then the user verifies that the received order contains the correct data

  Scenario: Delete existing order
    Given the user stores a new order for the Pet "SwaggerTestPet" with status "placed" and quantity "5"
    And  the user verifies that the order has been placed correctly
    Then the user delete the order for the Pet "SwaggerTestPet"
    And  the user verifies that the order for pet "SwaggerTestPet" is deleted

Scenario: Verify the Inventory endpoint returns response
    Given the user request to receive the inventory data
    Then the user verifies that the response contains data
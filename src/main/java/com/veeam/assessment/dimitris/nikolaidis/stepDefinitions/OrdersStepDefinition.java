package com.veeam.assessment.dimitris.nikolaidis.stepDefinitions;

import com.veeam.assessment.dimitris.nikolaidis.client.OrderResponse;
import com.veeam.assessment.dimitris.nikolaidis.configuration.CucumberConfiguration;
import com.veeam.assessment.dimitris.nikolaidis.models.Order;
import com.veeam.assessment.dimitris.nikolaidis.models.Status;
import com.veeam.assessment.dimitris.nikolaidis.utils.APIUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;

import java.time.LocalDate;
public class OrdersStepDefinition extends CucumberConfiguration {

    Response response;

    @Then("the user stores a new order for the Pet {string} with status {string} and quantity {string}")
    public void theUserStoresANewOrderForThePetAndQuantity(String petName, String status, String quantity) {
        Order order = new Order(8, storedPets.stream().
                filter(pet -> pet.getName().equalsIgnoreCase(petName)).findFirst().get().getId(),
                Double.parseDouble(quantity), LocalDate.now().toString(), Status.valueOf(status.toUpperCase()).toString(), true );

        String json = convertObjectToJson(order);

        response = apiUtils.post(orderUrl, json, APIUtils.DEFAULT_ACCEPT_JSON_HEADERS);

        if(response.getStatusCode() == HttpStatus.SC_OK) {
            storedOrders.add(order);
        }
    }

    @Then("the user stores an invalid order")
    public void theUserStoresAnInvalidOrder() {
        Order order = new Order(-1, -1 , -1, null, null, false);

        String json = convertObjectToJson(order);

        response = apiUtils.post(orderUrl, json, APIUtils.DEFAULT_ACCEPT_PLAIN_HEADERS);

        if(response.getStatusCode() == HttpStatus.SC_BAD_REQUEST) {
            storedOrders.add(order);
        }
    }

    @Then("the user delete the order for the Pet {string}")
    public void theUserDeletedTheOrderForThePet(String petName) {

        int petId = storedPets.stream().
                filter(pet -> pet.getName().equalsIgnoreCase(petName)).findFirst().get().getId();

        int orderIdForPet = storedOrders.stream().filter(order -> order.getPetId() == petId).findFirst().get().getId();

        response = apiUtils.delete(this.orderUrl, String.valueOf(orderIdForPet),
                APIUtils.DEFAULT_ACCEPT_JSON_HEADERS).extract().response();

    }

    @Given("the user request to delete an order with non existing id")
    public void theUserRequestToDeleteAnOrderWithNonExistingId() {
        response =  apiUtils.delete(this.orderUrl, String.valueOf(-1),
                APIUtils.DEFAULT_ACCEPT_JSON_HEADERS).extract().response();
    }

    @Given("the user request to delete an order with invalid id")
    public void theUserRequestToDeleteAnInvalidOrder() {
        response =  apiUtils.delete(this.orderUrl,"test",
                APIUtils.DEFAULT_ACCEPT_JSON_HEADERS).extract().response();
    }

    @Given("the user request for an invalid order")
    public void theUserRequestForAnInvalidOrder() {
         response =  apiUtils.get(this.orderUrl, String.valueOf(-1),
                APIUtils.DEFAULT_ACCEPT_JSON_HEADERS).extract().response();
    }

    @Given("the user request to receive the inventory data")
    public void theUserRequestToReceiveTheInventoryData() {
        response = apiUtils.get(this.inventoryUrl, APIUtils.DEFAULT_ACCEPT_JSON_HEADERS);
    }

    @Given("the user request for out of bound order id")
    public void theUserRequestForOutOfBoundOrderId() {
        response =  apiUtils.get(this.orderUrl, String.valueOf(11),
                APIUtils.DEFAULT_ACCEPT_JSON_HEADERS).extract().response();
    }

    @And("the user request the order for the Pet {string}")
    public void theUserRequestTheOrderForThePet(String petName) {

        int petId = storedPets.stream().
                filter(pet -> pet.getName().equalsIgnoreCase(petName)).findFirst().get().getId();

        int orderIdForPet = storedOrders.stream().filter(order -> order.getPetId() == petId).findFirst().get().getId();

        response = apiUtils.get(this.orderUrl, String.valueOf(orderIdForPet),
                APIUtils.DEFAULT_ACCEPT_JSON_HEADERS).extract().response();

    }

    @Then("the user verifies that no results found")
    public void theUserVerifiesThatNoResultsFound() {
        Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_NOT_FOUND ,
                String.format("The status code for creating a new pet is not as expected. Expected %s and found %s",HttpStatus.SC_NOT_FOUND,response.getStatusCode()));
    }

    @Then("the user verifies that no results found due to invalid order id")
    public void theUserVerifiesThatNoResultsFoundDueToInvalidOrderId() {
        Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_BAD_REQUEST ,
                String.format("The status code for creating a new pet is not as expected. Expected %s and found %s",HttpStatus.SC_NOT_FOUND,response.getStatusCode()));
    }

    @And("the user verifies that the order has been placed correctly")
    public void theUserVerifiesThatTheOrderHasBeenPlacedCorrectly() {

        Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK ,
                String.format("The status code for creating a new pet is not as expected. Expected %s and found %s",HttpStatus.SC_OK,response.getStatusCode()));

        OrderResponse orderResponse = apiUtils.get(this.orderUrl, String.valueOf(storedOrders.get(0).getId()),
                APIUtils.DEFAULT_ACCEPT_JSON_HEADERS).extract().as(OrderResponse.class);

        Assert.assertEquals(orderResponse.getPetId(), storedPets.get(0).getId() ,
                String.format("The Pet name is not as expected. Expected %s and found %s", storedPets.get(0).getId(), orderResponse.getPetId()));

    }

    @And("the user verifies that the order has not been placed")
    public void theUserVerifiesThatTheOrderHasNotBeenPlaced() {
        Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_BAD_REQUEST ,
                String.format("The status code for creating a new pet is not as expected. Expected %s and found %s",HttpStatus.SC_BAD_REQUEST,response.getStatusCode()));
    }


    @Then("the user verifies that the received order contains the correct data")
    public void theUserVerifiesThatTheReceivedOrderContainsTheCorrectData() {

        Order orderForPet = storedOrders.get(0);
        OrderResponse orderResponse = response.as(OrderResponse.class);


        Assert.assertEquals(orderForPet.getId(),orderResponse.getId() ,
                String.format("The fetch order is not the same as the saved one. Expected id %s and found id %s",orderForPet.getId(), orderResponse.getId()));

        Assert.assertEquals(orderForPet.getPetId(),orderResponse.getPetId() ,
                String.format("The fetch order is not the same as the saved one. Expected pet id %s and found pet id %s",orderForPet.getPetId(), orderResponse.getPetId()));
    }

    @And("the user verifies that the order for pet {string} is deleted")
    public void theUserVerifiesThatTheOrderIsDeleted(String petName) {
        Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK ,
                String.format("The status code for deleted the order is not as expected. Expected %s and found %s",HttpStatus.SC_OK,response.getStatusCode()));

        int petId = storedPets.stream().
                filter(pet -> pet.getName().equalsIgnoreCase(petName)).findFirst().get().getId();

        int orderIdForPet = storedOrders.stream().filter(order -> order.getPetId() == petId).findFirst().get().getId();

        Assert.assertTrue(response.getBody().prettyPrint().contains(String.valueOf(orderIdForPet)) ,
                String.format("The status code for deleted the order is not as expected. Expected %s and found %s",HttpStatus.SC_OK,response.getStatusCode()));
    }

    @Then("the user verifies that the response contains data")
    public void theUserVerifiesThatTheResponseContainsData() {
        Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK ,
                String.format("The status code for deleted the order is not as expected. Expected %s and found %s",HttpStatus.SC_OK,response.getStatusCode()));

        Assert.assertTrue(!response.getBody().prettyPrint().isEmpty() ,
                String.format("The inventory response does not contains data. Expected to have data and found %s",HttpStatus.SC_OK,response.getBody().prettyPrint()));

    }
}

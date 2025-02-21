package com.veeam.assignment.dimitris.nikolaidis.stepDefinitions;

import com.veeam.assignment.dimitris.nikolaidis.client.PetResponse;
import com.veeam.assignment.dimitris.nikolaidis.configuration.CucumberConfiguration;
import com.veeam.assignment.dimitris.nikolaidis.models.Category;
import com.veeam.assignment.dimitris.nikolaidis.models.Pet;
import com.veeam.assignment.dimitris.nikolaidis.models.Tag;
import com.veeam.assignment.dimitris.nikolaidis.utils.APIUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;

import java.util.*;

public class PetsStepDefinition extends CucumberConfiguration {

    Response response;


    @Given("the user creates a new pet")
    public void theUserCreatesANewPet(DataTable data) {

        List<Map<String, String>> rows = data.asMaps(String.class, String.class);
        ArrayList<String> petPhotos = new ArrayList<>();
        ArrayList<Tag> petTags = new ArrayList<>();
        Pet newPet = null;

        Random rand = new Random();
        for (Map<String, String> dataRow : rows) {

        if(dataRow.get("photo_url").contains(",") ) {
            petPhotos.addAll(Arrays.asList(dataRow.get("photo_url").split(",")));
        } else {
            petPhotos.add(dataRow.get("photo_url"));
        }

        if(dataRow.get("tag_name").contains(",") ) {
            for (String tag : dataRow.get("tag_name").split(",")) {
                petTags.add(new Tag(1, tag));
            }
        } else {
            petTags.add(new Tag(1, dataRow.get("tag_name")));
        }

        newPet = new Pet(rand.nextInt(RANDOM_NUMBER_BOUND), new Category(rand.nextInt(RANDOM_NUMBER_BOUND),dataRow.get("category_name")),
                dataRow.get("name"), petPhotos, petTags, "available");
        storedPets.add(newPet);
        }


        String json = convertObjectToJson(newPet);
        response = apiUtils.post(this.petUrl, json, APIUtils.DEFAULT_ACCEPT_JSON_HEADERS);

    }

    @Then("the user verifies that the pet {string} has been created")
    public void theUserVerifiesThatThePetHasBeenCreated(String petName) {
        // verify that the response has the correct status code
        Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK ,
                String.format("The status code for creating a new pet is not as expected. Expected %s and found %s",HttpStatus.SC_OK,response.getStatusCode()));

        PetResponse petResponse = apiUtils.get(this.petUrl, String.valueOf(storedPets.get(0).getId()),
                APIUtils.DEFAULT_ACCEPT_JSON_HEADERS).extract().as(PetResponse.class);

        Assert.assertEquals(petResponse.getName(),petName ,
                String.format("The Pet name is not as expected. Expected %s and found %s",storedPets, petName));
    }

    @Given("the user verifies that a pet exists")
    public void theUserVerifiesThatAPetExists() {
       PetResponse petResponse = apiUtils.get(this.petUrl, String.valueOf(storedPets.get(0).getId()),
               APIUtils.DEFAULT_ACCEPT_JSON_HEADERS).extract().as(PetResponse.class);

       Assert.assertEquals(petResponse.getId(), storedPets.get(0).getId(),
               String.format("The Pet id is not as expected. Expected %s and found %s",petResponse.getId(), storedPets.get(0).getId()));

    }



}

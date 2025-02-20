package com.veeam.assessment.dimitris.nikolaidis.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.veeam.assessment.dimitris.nikolaidis.models.Order;
import com.veeam.assessment.dimitris.nikolaidis.models.Pet;
import com.veeam.assessment.dimitris.nikolaidis.utils.APIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Random;


public class CucumberConfiguration {

    @Autowired
    protected APIUtils apiUtils;

    @Value("${application.url}")
    protected String url;

    @Value("${pet.path}")
    protected String petUrl;

    @Value("${order.path}")
    protected String orderUrl;

    @Value("${inventory.path}")
    protected String inventoryUrl;

    protected final static int RANDOM_NUMBER_BOUND = 1000;
    protected static ArrayList<Pet> storedPets = new ArrayList<>();
    protected static ArrayList<Order> storedOrders = new ArrayList<>();

    protected Random rand = new Random();



    protected String convertObjectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(object) ;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    protected <T> T readValue(String value, TypeReference<T> typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(value, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected <T> T readValue(String value, Class<T> typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(value, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

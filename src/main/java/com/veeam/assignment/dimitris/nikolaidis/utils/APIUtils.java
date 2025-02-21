package com.veeam.assignment.dimitris.nikolaidis.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class APIUtils {

    public static final Map<String,String> DEFAULT_ACCEPT_JSON_HEADERS = new HashMap<>(){{
        put("Content-Type", "application/json");
        put("Accept", "application/json");
    }};

    public static final Map<String,String> DEFAULT_ACCEPT_PLAIN_HEADERS = new HashMap<>(){{
        put("Content-Type", "application/json");
        put("Accept", "text/plain");
    }};


    public Response get(String endpoint, String path) {
        return RestAssured
                .given()
                .baseUri(endpoint)
                .basePath(path)
                .headers(DEFAULT_ACCEPT_PLAIN_HEADERS)
                .when()
                .get();
    }

    public Response get(String endpoint, String path, String parameter) {
        return RestAssured
                .given()
                .baseUri(endpoint)
                .basePath(path+"/"+parameter)
                .headers(DEFAULT_ACCEPT_PLAIN_HEADERS)
                .when()
                .get();
    }

    public ValidatableResponse get(String endpoint, String parameter, Map<String, String> headers) {
        return RestAssured
                .given()
                .baseUri(endpoint)
                .basePath(parameter)
                .headers(headers != null ? headers : DEFAULT_ACCEPT_PLAIN_HEADERS)
                .when()
                .get().then();
    }

    public Response get(String endpoint) {
        return RestAssured
                .given()
                .baseUri(endpoint)
                .headers(DEFAULT_ACCEPT_PLAIN_HEADERS)
                .when()
                .get();
    }

    public Response get(String endpoint, Map<String,String> headers) {
        return RestAssured
                .given()
                .baseUri(endpoint)
                .headers(headers != null ? headers : DEFAULT_ACCEPT_PLAIN_HEADERS)
                .when()
                .get();
    }

    public Response post(String endpoint, String path, Map<String, String> body,  Map<String,String> headers) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return RestAssured
                    .given()
                    .baseUri(endpoint)
                    .basePath(path)
                    .headers(headers != null ? headers : DEFAULT_ACCEPT_PLAIN_HEADERS)
                    .body(objectMapper.writeValueAsString(body))
                    .when()
                    .post();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Response post(String endpoint, String path, String body,  Map<String,String> headers) {
        return RestAssured
                .given()
                .baseUri(endpoint)
                .basePath(path)
                .headers(headers != null ? headers : DEFAULT_ACCEPT_PLAIN_HEADERS)
                .body(body)
                .when()
                .post();
    }

    public Response post(String endpoint, String body,  Map<String,String> headers) {
        return RestAssured
                .given()
                .baseUri(endpoint)
                .headers(headers != null ? headers : DEFAULT_ACCEPT_PLAIN_HEADERS)
                .body(body)
                .when()
                .post();
    }

    public Response put(String endpoint, String path, Map<String, String> body) {
        return  this.put(endpoint, path, body, null);
    }

    public Response put(String endpoint, String path, Map<String, String> body,  Map<String,String> headers) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return RestAssured
                    .given()
                    .baseUri(endpoint)
                    .basePath(path)
                    .headers(headers != null ? headers : DEFAULT_ACCEPT_PLAIN_HEADERS)
                    .body(objectMapper.writeValueAsString(body))
                    .when()
                    .put();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Response put(String endpoint, String path, String id , String body) {
        return this.put(endpoint, path, id, body, null);
    }

    public Response put(String endpoint, String path, String id , String body, Map<String,String> headers) {
        return RestAssured
                .given()
                .baseUri(endpoint)
                .basePath(path + "/" + id)
                .headers(headers != null ? headers : DEFAULT_ACCEPT_PLAIN_HEADERS)
                .body(body)
                .when()
                .put();
    }

    public Response delete(String endpoint, String path, Map<String,String> headers,  Map<String, String> params) {
        return RestAssured
                .given()
                .baseUri(endpoint)
                .basePath(path)
                .headers(headers != null ? headers : DEFAULT_ACCEPT_PLAIN_HEADERS)
                .params(params)
                .when()
                .delete();
    }

    public ValidatableResponse delete(String endpoint,  String parameter,  Map<String,String> headers) {
        return RestAssured
                .given()
                .baseUri(endpoint)
                .basePath(parameter)
                .headers(headers != null ? headers : DEFAULT_ACCEPT_PLAIN_HEADERS)
                .when()
                .delete().then();
    }

    public Response delete(String endpoint, String path, Map<String,String> headers, String parameter) {
        return RestAssured
                .given()
                .baseUri(endpoint)
                .basePath(path + "/" + parameter)
                .headers(headers != null ? headers : DEFAULT_ACCEPT_PLAIN_HEADERS)
                .when()
                .delete();
    }

    public Response delete(String endpoint, String path, String parameter) {
        return this.delete(endpoint, path, null, parameter);
    }



}

package com.veeam.assignment.dimitris.nikolaidis.client;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
        "id",
        "petId",
        "quantity",
        "shipDate",
        "status",
        "complete"
})
public class OrderResponse {


    @JsonProperty("id")
    int id;

    @JsonProperty("petId")
    int petId;

    @JsonProperty("quantity")
    double quantity;

    @JsonProperty("shipDate")
    String shipDate;

    @JsonProperty("status")
    String status;

    @JsonProperty("complete")
    boolean complete;


}

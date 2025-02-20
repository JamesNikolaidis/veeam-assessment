package com.veeam.assessment.dimitris.nikolaidis.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "petId",
        "quantity",
        "shipDate",
        "status",
        "complete"
})
public class Order {

    int id;
    int petId;
    double quantity;
    String shipDate;
    String status;
    boolean complete;


}

package com.veeam.assessment.dimitris.nikolaidis.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "name",
})
public class Category {

    @JsonProperty("id")
    int id;

    @JsonProperty("name")
    String name;

}

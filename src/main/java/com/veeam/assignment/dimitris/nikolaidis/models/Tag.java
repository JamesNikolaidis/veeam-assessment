package com.veeam.assignment.dimitris.nikolaidis.models;

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
public class Tag {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;
}

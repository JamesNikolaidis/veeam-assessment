package com.veeam.assessment.dimitris.nikolaidis.client;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.veeam.assessment.dimitris.nikolaidis.models.Category;
import com.veeam.assessment.dimitris.nikolaidis.models.Tag;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
        "id",
        "category",
        "name",
        "photoUrls",
        "tags",
        "status"
})
public class PetResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("name")
    private String name;

    @JsonProperty("photoUrls")
    private ArrayList<String> photoUrls;

    @JsonProperty("tags")
    private ArrayList<Tag> tags;

    @JsonProperty("status")
    private String status;

}

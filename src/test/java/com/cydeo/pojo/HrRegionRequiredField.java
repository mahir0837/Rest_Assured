package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HrRegionRequiredField {

    @JsonProperty("region_id")
    private int id;

    @JsonProperty("region_name")
    private String regionName;
}

package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Student {

    private String firstName;
    private int batch;
    private String major;
    private Contact contact;
    private Company company;


}

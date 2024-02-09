package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class CydeoAPIBase {

    private static final String BASE_URI="https://api.training.cydeo.com";
    @BeforeAll
    public static void init(){
        baseURI=BASE_URI;
    }
}

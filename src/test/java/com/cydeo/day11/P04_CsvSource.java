package com.cydeo.day11;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P04_CsvSource {

    @ParameterizedTest
    @CsvSource({"1,3,4",
                "2,3,5",
                "3,4,7",
                "5,6,11"})
    public void test(int num1,int num2,int sum) {
        System.out.println(num1 + " + " + num2 + " = " + sum);
        Assertions.assertEquals(sum,(num1+num2));
    }

    //TASK
    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "MA, Boston",
        "MD, Annapolis"
     */
    //verify each of the place name contains your city name
    //print number of places for each request

    @ParameterizedTest
    @CsvSource({ "NY, New York",
                "CO, Denver",
                "VA, Fairfax",
                "MA, Boston",
                "MD, Annapolis",
                "NH,Somersworth"})

    public void task1(String state,String city) {
        int placeNumber =
            given()
                .accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
            .when()
                .get("https://api.zippopotam.us/us/{state}/{city}")
            .then()
                .body("places.'place name'", everyItem(containsString(city)))
                .statusCode(200)
                .extract()
                .jsonPath().getList("places").size();

        System.out.println("placeNumber = " + placeNumber);


    }
}

package com.cydeo.day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class p05_CSVFileSourceTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/math.csv", numLinesToSkip = 1)
    public void test1(int num1, int num2, int total) {
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("total = " + total);
        Assertions.assertEquals(total, (num1 + num2));
    }

    /**
     * // Write a parameterized test for this request
     * // Get the data from csv source called as --> zipcode.csv
     * // state , city , numberOfPlace
     * // GET https://api.zippopotam.us/us/{state}/{city}
     * // After getting response numberOfPlaces needs to be same
     * <p>
     * state , city , numberOfPlace
     * NY,New York,166
     * CO,Denver,76
     * VA,Fairfax,10
     * MA,Boston,56
     * MD,Annapolis,9
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/zipcode.csv", numLinesToSkip = 1)
    void test2(String state, String city, int numberOfPlace) {

        given()
                .accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
        .when()
                .get("https://api.zippopotam.us/us/{state}/{city}")
        .then()
                .statusCode(200)
                .body("places", hasSize(numberOfPlace));


    }
}

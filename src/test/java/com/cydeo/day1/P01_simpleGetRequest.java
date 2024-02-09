package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_simpleGetRequest {

    private static final String BASE_URL="http://54.208.32.156:8000/api/spartans";

    /*
    When user send request to /api/spartans endpoint
    Then user should be able to see status code is 200
    and print out body into screen
     */

    @Test
    public void simpleGetRequest(){
        Response response = RestAssured.get(BASE_URL);
        //Both same no difference, they get the response status code
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        //verify the status code is 200
        int statusCode=response.statusCode();
        //assert that it is 200
        Assertions.assertEquals(200,statusCode);

        //how to print response body on console
        response.prettyPrint();
    }
}

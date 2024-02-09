package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequest {

    private static final String BASE_URL = "http://54.208.32.156:8000";

    /*
     * Given content type is application/json
     * When user sends get request /api/spartans endpoint
     * Then status code should be 200
     * And Content type should be application json
     */

    @Test
    public void getAllSpartans() {
        Response response = RestAssured.given()
                .accept(ContentType.JSON) //it is sending us JSON
                .when().get(BASE_URL + "/api/spartans");

//        response.prettyPrint(); // print the response body

        //how to get status code
        int actualStatusCode = response.statusCode();

        //assertion
        Assertions.assertEquals(200, actualStatusCode);

        //how to get response content type header ?

        String actualContentType = response.contentType();
        System.out.println("actualContentType = " + actualContentType);
        Assertions.assertEquals("application/json", actualContentType);

        // how to get Connection header value
        //if we want to get any response value, we can use header("headerName)"
        //method from response object. It will return header value as a string
        System.out.println(response.header("Content-type"));
        System.out.println(response.header("Connection"));
        System.out.println(response.header("Date"));

        //How to verify header exist

        //Has headerWithName method help us to verify header exist or not
        //it is useful for dynamic header values like Date,
        //we are only verifying header exist or not, not checking the value
        boolean isDate = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDate);
    }

    /*
     * Given content type is application/json
     * When user sends get request /api/spartans/3 endpoint
     * Then status code should be 200
     * And Content type should be application/json
     * And response body needs to contain Fidole
     */

    @Test
    void getSpartan() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(BASE_URL + "/api/spartans/3");

        //verification
        Assertions.assertEquals(200, response.getStatusCode());
        //verify Content type
        Assertions.assertEquals("application/json", response.header("Content-type"));
        Assertions.assertEquals(ContentType.JSON.toString(), response.getContentType());
        //verify response body contains "Fidole"
        Assertions.assertTrue(response.getBody().asString().contains("Fidole"));
        /*
            This is not a good way. in this way we are just converting to String and
            with the help of the String contains we are just looking into Response.
            But we should be able to get json "name" key value then verify that one is equal to "Fidole"
         */
    }
    /*
     * Given no headers provided
     * When Users send GET request to /api/hello
     * Then response status code should be 200
     * And Content type header should be "text/plain;charset=UTF-8"
     * And header should contain Date
     * And Content-Length should be 17
     * And body should be "Hello from Sparta"
     */

    @Test
    public void helloSpartan() {
        Response response = RestAssured.given().get(BASE_URL + "/api/hello");
        //verify status code
        response.prettyPrint();
        Assertions.assertEquals(200, response.getStatusCode());
        //verify content Type
        Assertions.assertEquals("text/plain;charset=UTF-8", response.getContentType());
        //verify header contains date
        Assertions.assertTrue(response.getHeaders().hasHeaderWithName("Date"));
        // And Content-Length should be 17
        Assertions.assertEquals("17",response.header("Content-Length"));
        //body should be "Hello from Sparta
        Assertions.assertTrue(response.getBody().asString().equals("Hello from Sparta"));

    }
}

package com.cydeo.day3;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanWithParameters  extends SpartanTestBase {

     /* Given accept type is Json
        And Id parameter value is 24
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 200
        And response content-type: application/json
        And "Julio" should be in response payload(body)
     */

    @DisplayName("GET Spartan /api/spartans/{id} path param with 24")
    @Test
    public void test1() {
        Response rsp = given().accept(ContentType.JSON)
                .and() //Syntactic sugar--> to increase readability of the code, not affect anything
                .pathParam("id",24)
                .get("/api/spartans/{id}");
        //verify response status
        assertEquals(200,rsp.getStatusCode());
        //verify response status
        assertEquals(ContentType.JSON.toString(),rsp.getContentType());
        //"Julio" should be in response payload(body)
        assertTrue(rsp.getBody().asString().contains("Julio"));
    }

    /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
      */
    @DisplayName("GET Spartan /api/spartans/{id} with invalid ID")
    @Test
    public void test2() {
        Response resp = given().accept(ContentType.JSON)
                .and() //Syntactic sugar--> to increate readability of the code, not affect anything
                .pathParam("id", 500)
                .when().get("/api/spartans/{id}");
        //Then response status code should be 404
        assertEquals(HttpStatus.SC_NOT_FOUND,resp.getStatusCode());
        // response content-type: application/json
        assertEquals(ContentType.JSON.toString(),resp.getContentType());
        // And "Not Found" message should be in response payload
        assertTrue(resp.getBody().asString().contains("Not Found"));
    }

      /*
        Given Accept type is Json
        And query parameter values are:
            gender|Female
            nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
      @DisplayName("GET Request to /api/spartans/search with Query Params")
      @Test
      public void test3(){
          Response rsp = given().accept(ContentType.JSON)
                  .and()
                  .queryParam("gender", "Female")
                  .and()
                  .queryParam("nameContains", "e")
                  .get("/api/spartans/search");
          //Then response status code should be 200
          assertEquals(HttpStatus.SC_OK,rsp.getStatusCode());
          // And response content-type: application/json
          assertEquals(ContentType.JSON.toString(),rsp.getContentType());
          //And "Female" should be in response payload
          assertTrue(rsp.getBody().asString().contains("Female"));
          // And "Janette" should be in response payload
          assertTrue(rsp.getBody().asString().contains("Janette"));
          //assertTrue(response.body().asString().contains("Janette"));
        /*
            We are just doing exerise to verify something in Response. This is not the proper way to verify
            all Spartans genders are Female, or name field is Janette, We will learn different method to get specific data.
         */
      }

    @DisplayName("GET Request to /api/spartans/search with Query Params")
    @Test
    public void test4(){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        //api/spartans/search?gender=Female&nameContains=e
        Response response= given().
                accept(ContentType.JSON)
                .and()
                .queryParams(queryMap)
                .when()
                .get("/api/spartans/search");
        //print response
        response.prettyPrint();

        // Then response status code should be 200
        assertEquals(HttpStatus.SC_OK,response.statusCode());
        // And response content-type: application/json
        assertEquals("application/json",response.contentType());
        // And "Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
        // And "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));

        //        assertTrue(response.body().asString().contains("Janette"));
        /*
            We are just doing exerise to verify something in Response. THi is not the proper way to verify
            all Spartans genders are Female, or name field is Janette, We will learn different method to get specific data.
         */
    }
}

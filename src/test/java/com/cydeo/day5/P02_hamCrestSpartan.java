package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class P02_hamCrestSpartan extends SpartanTestBase {

    /*
  Given accept type is Json
  And path param id is 15
  When user sends a get request to api/spartans/{id}
  Then status code is 200
  And content type is Json
  And json data has following
      "id": 15,
      "name": "Meta",
      "gender": "Female",
      "phone": 1938695106
   */

    @DisplayName("Get Single Spartan with Hamcrest")
    @Test
    public void test1() {
        given()
                .accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", is(15),
                        "name", is("Meta"),
                        "gender", equalTo("Female"),
                        "phone", is(1938695106));

        //.statusCode(is(200)); --> this is optional to increase readability
    }


    @DisplayName("Get Single Spartan with Hamcrest with")
    @Test
    public void test2() {
        given()
                .accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .assertThat() //assertThat(),and() these are syntactic sugar just to increase readability
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .assertThat()
                .body("id", is(15))
                .and()
                .body("name", is("Meta"))
                .body("gender", is("Female"))
                .body("phone", is(1938695106));

        //this test2() and test1() exactly the same testcase/ we just used more filler(syntactic sugar) keywords to increase readability

        //.statusCode(is(200)); --> this is optional to increase readability
    }
    @DisplayName("Get Single Spartan with Hamcrest LOGS")
    @Test
    public void test3(){
        Response response = given()
                .log().ifValidationFails() //to see request all information, if validation fails
                .accept(ContentType.JSON)
                .pathParam("id",15)
                .when()
                .get("/api/spartans/{id}").prettyPeek()
                .then()
                .log().ifValidationFails(LogDetail.HEADERS) //to see response all information, if validation fails
                .statusCode(200)
                .contentType("application/json")
                .body("id",is(15),
                        "name",is("Meta"),
                        "gender",equalTo("Female"),
                        "phone",is(1938695106))
                .extract().response();

        int id = response.path("id");
        System.out.println("id = " + id);


        /*
            REQUEST
                given().
                        log().all() --> it will give all information about your request(path/query params,URI,body etc)
                        .method()
                        .uri()
                        .parameters()
                        .body()....
            RESPONSE
                then()
                        .log().all() --> it will give all response information

                ifValidationFails() --> it will print all information when there is a failure
                ifValidationFails(LogDetail.HEADERS) --> after failure only show me headers
                we can change LogDetail scope to see other information.
                and we can use ifValidationFails for both request and response logs.
         */

        /*
            HOW TO PRINT RESPONSE BODY

                response.prettyPrint() (String) --> it is printing response body into screen
                response.prettyPeek() (Response) --> it will print response intro screen, returns Response
                and allows us to continue chaining
         */
        /*
            HOW TO EXTRACT DATA AFTER DOING VALIDATION WITH THEN() and HAMCREST?
            -- extract() --> method will help us to STORE data after doing verification
            in following type
                response() --> to get response object ex: extract().response();
                OR
                jsonPath() --> to get response body as jsonpath object directly
                   ex: extract().jsonPath();


         */
    }
}

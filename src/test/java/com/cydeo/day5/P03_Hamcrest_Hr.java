package com.cydeo.day5;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class P03_Hamcrest_Hr extends HrTestBase {

      /*
       Given accept type is Json
       And parameters: q = {"job_id":"IT_PROG"}
       When users sends a GET request to "/employees"
       Then status code is 200
       And Content type is application/json
       Verify
           - each employees has manager
           - each employees working as IT_PROG
           - each of them getting salary greater than 3000
           - first names are .... (find proper method to check list against list)
           - emails without checking order (provide emails in different order,just make sure it has same emails)
           List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");
           "DAUSTIN","AHUNOLD","BERNST","VPATABAL","DLORENTZ"
  */

    @DisplayName("GET employees IT PROG with hamcrest ")
    @Test
    void test1() {
        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");
        given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .assertThat()
                .statusCode(is(200))
                .and()
                .contentType(ContentType.JSON)
                .body("items.manager_id", everyItem(notNullValue()))
                .body("items.job_id", everyItem(is(equalTo("IT_PROG"))))
                .body("items.salary", everyItem(is(greaterThan(3000))))
                .body("items.first_name", equalTo(names))
                .body("items.email", containsInAnyOrder("DAUSTIN", "AHUNOLD", "BERNST", "VPATABAL", "DLORENTZ"));
    }

    /*
    Given
             accept type is application/json
     When
             user sends get request to /regions
     Then
             response status code must be 200
             verify Date has values
             first region name is Europe
             first region id is 1
             four regions we have
             region names are not null
             Regions name should be same order as "Europe","Americas","Asia","Middle East and Africa"
             region ids needs to be 1,2,3,4

             print all the regions names
             ...
             ..
             .
  */
    @Test
    void test2() {
        JsonPath jsonPath =
          given()
                .accept(ContentType.JSON)
          .when()
                .get("/regions")
          .then()
                .and()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON.toString())
                .and()
                .headers("Date", is(notNullValue()))
                .body("items[0].region_name", is(equalTo("Europe")))
                .body("items[0].region_id", is(equalTo(1)))
                .body("items",hasSize(4))
                .body("items.region_name", everyItem(is(notNullValue())))
                .body("items.region_name", containsInRelativeOrder("Europe", "Americas", "Asia", "Middle East and Africa"))
                .body("items.region_id", containsInRelativeOrder(1, 2, 3, 4))
                .extract().jsonPath();
        List<String> allRegionName = jsonPath.getList("items.region_name");
        System.out.println(allRegionName);
        //get region names from ui or database
    }
}

package com.cydeo.day4;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanWithJsonPath extends SpartanTestBase {

     /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */

    @DisplayName("Get Spartans with response path")
    @Test
    void name() {
        Response resp = given().accept(ContentType.JSON)
                .and()
                .pathParams("id", 10)
                .get("/api/spartans/{id}");
        assertEquals(ContentType.JSON.toString(),resp.getContentType());
        assertEquals(HttpStatus.SC_OK,resp.getStatusCode());

//        int id = resp.path("id");
//        String name=resp.path("name");
//        String gender=resp.path("gender");
//        Long phone=resp.path("phone");

        System.out.println("resp.path(\"name\").toString() = " + resp.path("name").toString());

        //we saved our response as JSONPATH object
        JsonPath jsonPath = resp.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");
        assertEquals(10,id);
        assertEquals("Lorenza",name);

    }

}

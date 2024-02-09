package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class P04_DeserializationToCollections extends SpartanTestBase {
    
     /*
     Given accept type is application/json
     And Path param id = 10
     When I send GET request to /api/spartans
     Then status code is 200
     And content type is json
     And spartan data matching:
         id > 10
         name>Lorenza
         gender >Female
         phone >3312820936
     */

    @Test
    void test1() {
        Response resp = given()
                .accept(ContentType.JSON)
                .pathParam("id", 10)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();
        //If you want to convert your json to the java collection (map,list,custom class..)
        //this is call the deserialization
        //JSON-->Java
        //We need either Jackson or Gson
        //solution 1 --> using response as method
        Map<String,Object>spartanMap=resp.as(Map.class);
        System.out.println("spartanMap = " + spartanMap);
        Integer id = (Integer) spartanMap.get("id");
        String name=(String) spartanMap.get("name");
        System.out.println("id and name = " + id + " " + name);

        //solution 2 -->JsonPath
        JsonPath jsonPath=resp.jsonPath();
        Map<String,Object>jsonPathMap=jsonPath.getMap("");
        System.out.println("jsonPathMap = " + jsonPathMap);

        int idJson = (int) jsonPathMap.get("id");
        System.out.println("id = " + id);

    }

    @DisplayName("Get All Spartans with Java Collections")
    @Test
    void test2() {
        Response resp = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        //Approach 1 -->With response object
        List<Map<String,Object>> spartanList = resp.as(List.class);
        System.out.println(spartanList);
    }
}

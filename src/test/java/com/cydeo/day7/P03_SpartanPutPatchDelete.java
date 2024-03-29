package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;

public class P03_SpartanPutPatchDelete extends SpartanTestBase {

    @DisplayName("PUT Spartan with Map")
    @Test
    public void test1(){
        //we can provide json request body with map,pojo,string all is valid here too.

        Map<String,Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name","John Doe PUT");
        requestBodyMap.put("gender","Male");
        requestBodyMap.put("phone","8877445596");

        //PUT will update existing record so we choose one the existing ID, make sure it exist in your IP address
        int id = 103;

        given()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(requestBodyMap)
        .when()
                .put("/api/spartans/{id}")
        .then()
                .statusCode(204);

    }
    @DisplayName("PATCH Spartan with Map")
    @Test
    public void test2(){
        //we can provide json request body with map,pojo,string all is valid here too.

        Map<String,Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name","John Doe PATCH");


        //PUT will update existing record so we choose one the existing ID, make sure it exist in your IP address
        int id = 103;

        given()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(requestBodyMap)
                .when()
                .patch("/api/spartans/{id}")
                .then()
                .statusCode(200);

    }

    @DisplayName("DELETE Spartan with Map")
    @Test
    public void test3(){
       //WE CAN DELETE ONE ID ONLY ONE TIME
        int id = 103;

        given()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(200);

    }
}

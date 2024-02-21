package com.cydeo.day10;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P04_JsonSchemaValidation extends SpartanTestBase {

    @Test
    void test1() {
        given()
                .accept(ContentType.JSON)
                .pathParam("id",20)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .log().body()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));

    }

    @DisplayName("GET /api/spartans/search to validate with json schema validator")
    @Test
    void test2() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SearchSpartansSchema.json"));
    }
    @DisplayName("GET /api/spartans/search to validate with json schema validator")
    @Test
    void test3() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/SearchSpartansSchema.json")));
    }

    /**
     *     Do schema validation for ALLSPARTANS and POST SINGLE SPARTAN
     *
     *     ALL SPARTANS
     *      1- Get all spartans by using /api/spartans
     *      2- Validate schema by using  JsonSchemaValidator
     *
     *
     *    POST SINGLE SPARTANS
     *       1- Post single spartan
     *       2- Validate schema by using  JsonSchemaValidator
     *
     */
    @DisplayName("GET api/spartans get all spartans and validate json schema")
    @Test()
    void test4() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("AllSpartansSchema.json"));
    }

    @DisplayName("POST api/spartans post spartans and validate json schema")
    @Test()
    void test5() {
        Map<String,Object>postRequestMap=new HashMap<>();
        postRequestMap.put("name","Mario Icardi");
        postRequestMap.put("gender","Male");
        postRequestMap.put("phone",1234567890);

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(postRequestMap)
                .post("api/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SpartanPostSchema.json"));
    }
}

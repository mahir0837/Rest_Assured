package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class P02_SpartansSpecTest extends SpartanNewTestBase {

    @Test
    public void getAllSpartans(){
        given()
                .accept(ContentType.JSON)
                .auth()
                .basic("admin","admin")
        .when()
                .get("/spartans")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

    @Test
    public void getAllSpartansWithRequestAndResponseSpect(){
        given().
                spec(requestSpec)
        .when()
                .get("spartans")
        .then()
                .spec(responseSpec);
    }

    @Test
    void getOneSpartansWithRequestAndResponseSpect() {
        given()
                .spec(requestSpec)
                .and()
                .pathParam("id",10)
        .when()
                .get("spartans/{id}")
        .then()
                .spec(responseSpec)
                .body("id",equalTo(10));
    }

    @Test
    void getOneSpartansAsAUser() {
        given()
                .spec(dynamicRequestSpec("user"))
                .and()
                .pathParam("id",10)
                .when()
                .get("spartans/{id}")
                .then()
                .spec(dynamicResponseSpec(200))
                .body("id",equalTo(10));
    }

    /**
     *  Create GET_RBAC.csv
     *    username,password,id,statusCode
     *    admin,admin,3,200
     *    editor,editor,3,200
     *    user,user,3,200
     *
     *  Create a parameterized test to check RBAC for GET method
     *
     *
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/GET_RBAC.csv",numLinesToSkip = 1)
    public void getSingleSpartan_GET_RBAC(String role,int id,int statusCode){
      given()
              .spec(dynamicRequestSpec(role))
              .pathParam("id",id)
      .when()
              .get("/spartans/{id}")
      .then()
              .spec(dynamicResponseSpec(statusCode));
    }
    /**
     *  Create DELETE_RBAC.csv
     *   username,password,id,statusCode
     *    editor,editor,3,403
     *    user,user,3,403
     *    admin,admin,3,204
     *
     *  Create a parameterized test to check RBAC for DELETE method
     *
     *
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/DELETE_RBAC.csv",numLinesToSkip = 1)
    public void deleteSingleSpartan_DELETE_RBAC(String role, int id, int statusCode){

        given().spec(dynamicRequestSpec(role))
                .pathParam("id",id)
                .when().delete("/spartans/{id}")
                .then().spec(dynamicResponseSpec(statusCode));

    }

}

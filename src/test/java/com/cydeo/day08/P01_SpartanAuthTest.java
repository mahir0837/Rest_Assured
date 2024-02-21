package com.cydeo.day08;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P01_SpartanAuthTest extends SpartanAuthTestBase {

    @DisplayName("Get /api/spartans as a Guest user -->Expect 401 ")
    @Test
    void test1() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/api/spartans")
        .then()
                .log().all()
                .statusCode(401)
                .body("error",is("Unauthorized"));
    }

    @DisplayName("Get /api/spartans as a user -->Expect 200 ")
    @Test
    void test2() {

        given()
                .accept(ContentType.JSON)
                .auth()
                .basic("user","user")
        .when()
                .get("/api/spartans")
        .then()
                .log().body()
                .statusCode(200);
    }

    @DisplayName("Delete api/spartans/{id} as Editor -->403 FORBIDDEN")
    @Test
    public void test3(){
        given()
                .contentType(ContentType.JSON)
                .auth()
                .basic("editor","editor")
                .pathParam("id",100)
        .when()
                .delete("api/spartans/{id}").prettyPeek()
        .then()
                .statusCode(403)
                .body("error",is("Forbidden"));
    }

    @DisplayName("Delete api/spartans/{id} as Admin -->204")
    @Test
    public void test4(){
        given()
                .auth()
                .basic("admin","admin")
                .pathParam("id",100)
        .when()
                .delete("api/spartans/{id}").prettyPeek()
        .then()
                .statusCode(204);
    }
    /**
     *      HOMEWORKS
     *  	Role Based Control Test --> RBAC
     * 			ADMIN  -->  GET  POST PUT PATCH  DELETE   --> Spartan Flow
     * 			EDITOR -->  GET  POST PUT PATCH   403
     * 			USER   -->  GET  403  403  403    403
     * 			GUEST  -->  401  401  401  401    401
     *   -- Create RBAC Test for all different roles from Spartan Application including with Negative Test cases
     *      public static void  GETSpartans(String role,String password,int statusCode,int id){
     *                 given().pathParam("id",id)
     *                 .auth().basic(role,password).
     *                 when().get("/api/spartans/{id}").then().statusCode(statusCode);
     *               }
     *               **/

}

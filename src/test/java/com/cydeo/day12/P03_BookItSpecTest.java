package com.cydeo.day12;

import com.cydeo.utilities.BookItTestBase;
import com.cydeo.utilities.BookItUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P03_BookItSpecTest extends BookItTestBase {

    /**
     * Send a request /api/users/me endpoint as teacher
     * verify status code 200
     * content Type will be ContentType.JSON
     *
     */

    @Test
    public void test1(){
        given()
                .log().all()
                .accept(ContentType.JSON)
                .header("Authorization",BookItUtil.getTokenByRole("teacher"))
        .when()
                .get("/api/users/me")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void test2(){
        given()
                .spec(dynamicRequestSpec("teacher"))
        .when()
                .get("/api/users/me")
        .then()
                .spec(dynamicResponseSpec(200));
    }
}

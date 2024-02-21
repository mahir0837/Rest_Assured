package com.cydeo.day08;

import com.cydeo.utilities.BookItTestBase;
import com.cydeo.utilities.BookItUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P02_BooktItTest extends BookItTestBase {

String accessToken=BookItUtil.getAccessToken("lfinnisz@yolasite.com","lissiefinnis");
    @DisplayName("GET /api/campuses")
    @Test
    public void test1() {
        given()
                .accept(ContentType.JSON)
                .header("Authorization", BookItUtil.getAccessToken("lfinnisz@yolasite.com"
                        , "lissiefinnis"))
                .get("/api/campuses").prettyPeek()
                .then()
                .statusCode(200);
    }
    /*
    the proper way to handle different token is
    you need to have each user type in you config.properties
    then one method where you pick userType, and it will return token to you

    getTokenByUser(String userType) "TEAM_LEADER",TEAM_MEMBER, "TEACHER"

    we can write method with switch case and it will return email and password base on the role.
     */

    @DisplayName("GET /users/me it returns current user")
    @Test
    void test2() {
        given()
                .accept(ContentType.JSON)
                .header("Authorization",BookItUtil.getAccessToken("lfinnisz@yolasite.com"
                ,"lissiefinnis"))
                .get("/api/users/me").prettyPeek()
                .then()
                .statusCode(200);
    }

    @DisplayName("GET /users/me it returns current user")
    @Test
    void test3() {
        given()
                .accept(ContentType.JSON)
                .auth().oauth2(accessToken)
                .get("/api/users/me").prettyPeek()
                .then()
                .statusCode(200);
    }
}

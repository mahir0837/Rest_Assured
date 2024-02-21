package com.cydeo.utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class BookItUtil {


    public static String getAccessToken(String email, String password) {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password)
                .when()
                .get("/sign")
                .then()
                .statusCode(200).extract().jsonPath();
        return "Bearer " + jsonPath.getString("accessToken");

    }
}

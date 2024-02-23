package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewTestBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P01_OldRestAssured extends SpartanNewTestBase {

    @Test
    void getAllSpartan() {
        given()
                .accept(ContentType.JSON)
                .auth()
                .basic("admin","admin")
        .when()
                .get("/spartans")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",equalTo(1))
                .body("id[1]",equalTo(2))
                .log().body();
    }

    @Test
    void getAllSpartansOldWay() {
        given()
                .accept(ContentType.JSON)
                .auth().basic("admin","admin")
        .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",equalTo(1))
                .body("id[1]",equalTo(2))
        .when()
                .get("/spartans");
    }
    /*
        OLD WAY -->EXPECT()
            -It works like a soft assertion
        NEW WAY -->then() (this is one that we are using)
            -It works like hard assertion
     */
}

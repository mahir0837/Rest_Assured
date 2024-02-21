package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P03_ResponseTimeTest extends SpartanAuthTestBase {

    @Test
    void test1() {
        Response response = given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
//                .time(lessThan(2000L))
//                .time(greaterThan(700L)); //this method is storing actual response time
                .time(both(greaterThan(500L)).and(lessThan(2000L))).extract().response();

        System.out.println("response.getTime() = " + response.getTime());
        System.out.println("response.getTimeIn(TimeUnit.NANOSECONDS) = " + response.getTimeIn(TimeUnit.NANOSECONDS));
        System.out.println("response.getTimeIn(TimeUnit.SECONDS) = " + response.getTimeIn(TimeUnit.SECONDS));
    }
}

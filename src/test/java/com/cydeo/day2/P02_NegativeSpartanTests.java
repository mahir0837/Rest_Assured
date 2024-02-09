package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_NegativeSpartanTests {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://54.208.32.156:8000";
    }

    /*
     * Given accept  content type is application/json
     * When user sends GET request /api/spartans endpoint
     * Then status code should be 200
     */

    @DisplayName("GET - ALL SPARTANS")
    @Test
    void getAllSpartans() {
        Response rsp =given().accept(ContentType.JSON)
                .when().get("/api/spartans");
        assertEquals(200,rsp.getStatusCode());

        rsp.prettyPrint();
    }

      /*
        Given Accept type application/xml
        When user send GET request to /api/spartans/10 end point
        Then status code must be 406
        And response Content Type must be application/xml;charset=UTF-8;
        */

    @DisplayName("GET- All Spartans -Accept, application/xml - 406")
    @Test
    void xmlTest() {
        Response rsp =given().accept(ContentType.XML)
                .when().get("/api/spartans/10");
        //verify status code is 406
        assertEquals(406,rsp.getStatusCode());
        //response Content Type must be application/xml;charset=UTF-8;
        assertEquals("application/xml;charset=UTF-8",rsp.getContentType());
    }
}

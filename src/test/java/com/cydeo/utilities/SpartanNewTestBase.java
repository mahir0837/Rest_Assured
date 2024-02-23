package com.cydeo.utilities;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanNewTestBase {

    public static RequestSpecification requestSpec;
    //    public static RequestSpecification requestUserSpec;
    public static ResponseSpecification responseSpec;
    private static String username;
    private static String password;
    @BeforeAll
    public static void init() {
        baseURI = "http://54.208.32.156";
        port = 7000;
        basePath = "/api";



//        requestSpec = given()
//                .accept(ContentType.JSON)
//                .auth()
//                .basic("admin", "admin");

//        requestUserSpec = given()
//                .accept(ContentType.JSON)
//                .auth()
//                .basic("user", "user");

//        responseSpec =
//                expect().statusCode(200)
//                        .contentType(ContentType.JSON);
    }

    public static RequestSpecification dynamicRequestSpec(String role) {
        try {
            switch (role) {
                case "admin":
                    username = "admin";
                    password = "admin";
                    break;
                case "user":
                    username = "user";
                    password = "user";
                    break;
                case "editor":
                    username = "editor";
                    password = "editor";
                    break;
            }
        }catch (Exception e){
            System.out.println("No Such a user type ");
            e.printStackTrace();
        }
        return requestSpec = given()
                .accept(ContentType.JSON)
                .auth()
                .basic(username, password);
    }
    public static ResponseSpecification dynamicResponseSpec(int statusCode) {

        return responseSpec =
                expect().statusCode(statusCode)
                        .contentType(ContentType.JSON);
    }
}

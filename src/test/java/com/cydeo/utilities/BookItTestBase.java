package com.cydeo.utilities;


import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class BookItTestBase {

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;
    private static String username;
    private static String password;
    @BeforeAll
    public static void init(){
        baseURI="https://api.qa.bookit.cydeo.com/";
    }

    public static RequestSpecification dynamicRequestSpec(String role) {
        try {
            switch (role) {
                case "teacher":
                    username = ConfigurationReader.getProperty("teacher_email");
                    password = ConfigurationReader.getProperty("teacher_password");
                    break;
                case "member":
                    username = ConfigurationReader.getProperty("team_member_email");
                    password = ConfigurationReader.getProperty("team_member_password");
                    break;
                case "leader":
                    username = ConfigurationReader.getProperty("team_leader_email");
                    password = ConfigurationReader.getProperty("team_leader_password");
                    break;
            }
        }catch (Exception e){
            System.out.println("No Such a user type ");
            e.printStackTrace();
        }
        return requestSpec = given()
                .accept(ContentType.JSON)
                .header("Authorization",BookItUtil.getTokenByRole("teacher"));
    }
    public static ResponseSpecification dynamicResponseSpec(int statusCode) {

        return responseSpec =
                expect().statusCode(statusCode)
                        .contentType(ContentType.JSON);
    }
}

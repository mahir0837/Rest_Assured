package com.cydeo.day6;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class P02_SpartanDeserializationPojo extends SpartanTestBase {

    @DisplayName("Get single spartan for deserialization to POJO (Spartan Class)")
    @Test
    public void test1() {
        Response response = given()
                .accept(ContentType.JSON)
                .pathParams("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).extract().response();
            /*
            {
                "id": 15,
                "name": "Meta",
                "gender": "Female",
                "phone": 1938695106
            }
            */

        System.out.println("************RESPONSE****************");

        Spartan spartan = response.as(Spartan.class);
        System.out.println("spartan.getName() = " + spartan.getName());
        System.out.println("spartan.getId() = " + spartan.getId());
        System.out.println("spartan.getGender() = " + spartan.getGender());
        System.out.println("spartan.getPhone() = " + spartan.getPhone());

        //JSONPATH
        System.out.println("************JSONPATH****************");
        Spartan spartanJP = response.jsonPath().getObject("", Spartan.class);
        System.out.println("spartanJP.getId() = " + spartanJP.getId());
        System.out.println("spartanJP.getName() = " + spartanJP.getName());
        System.out.println("spartanJP.getGender() = " + spartanJP.getGender());
        System.out.println("spartanJP.getPhone() = " + spartanJP.getPhone());

    }

    @DisplayName("Get Spartans from search end point and deserialize to pojo")
    @Test
    public void test2() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200).extract().response();

        //I want to get 10th spartan from content array and save into the Spartan object
        //response.as("content[9]",Spartan.class)
        //we cannot do with as() method since it does not support path and class type at the same time

        Spartan spartan = response.jsonPath().getObject("content[9]", Spartan.class);
        System.out.println("spartan = " + spartan);
        System.out.println("spartan.getName() = " + spartan.getName());
    }

    @DisplayName("GET spartans from search endpoint for deserialization to Search class")
    @Test
    public void test4(){
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200).extract().response();
        Search search = response.as(Search.class);
        System.out.println("search.getTotalElement() = " + search.getTotalElement());

        //Print second spartan from content
        System.out.println("search.getContent().get(1) = " + search.getContent().get(1));

        //get me second spartan name
        System.out.println("Second Spartan name : "+search.getContent().get(1).getName());
    }

    @DisplayName("GET spartans from search endpoint for deserialization to List of Spartan")
    @Test
    public void test5(){
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<Spartan> allSpartans = jsonPath.getList("content", Spartan.class);
        for (Spartan spartan : allSpartans) {
            System.out.println("spartan = " + spartan);
        }
        System.out.println("allSpartans.get(1).getId() = " + allSpartans.get(1).getId());
        System.out.println("allSpartans.get(1).getName() = " + allSpartans.get(1).getName());
    }
}

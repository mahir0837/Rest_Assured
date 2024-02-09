package com.cydeo.day3;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;


import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P03_SpartanWithResponsePath extends SpartanTestBase {
  /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */

    @DisplayName("Get Spartans with response path")
    @Test
    void name() {
        Response resp = given().accept(ContentType.JSON)
                .and()
                .pathParams("id", 10)
                .get("/api/spartans/{id}");
        assertEquals(ContentType.JSON.toString(),resp.getContentType());
        assertEquals(HttpStatus.SC_OK,resp.getStatusCode());

        int id = resp.path("id");
        String name=resp.path("name");
        String gender=resp.path("gender");
        Long phone=resp.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //if the path is incorrect it will return null
        String address = resp.path("address");
        System.out.println("address = " + address);

        //Assertions
        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female",gender);
        assertEquals(3312820936l,phone);
    }

    @DisplayName("Get all Spartans")
    @Test
    void test2() {
        Response resp = given().accept(ContentType.JSON)
                .and()
                .when().get("/api/spartans");
        //get me first spartans id
        int firstId=resp.path("[0].id");
        System.out.println("firstId = " + firstId);

        //first id
        int IDFirst=resp.path("id[0]");
        System.out.println("IDFirst = " + IDFirst);

        //get me firstName
        System.out.println("resp.path(\"[0].name\") = " + resp.path("[0].name"));
        System.out.println("resp.path(\"[0].name\") = " + resp.path("[1].name"));

        //get me last spartan`s name
        //name[-1]-->refers last element of the name list
        System.out.println("resp.path(\"name[-1]\") = " + resp.path("name[-1]"));
        //get me second spartant from the last
        System.out.println("resp.path(\"name[-1]\") = " + resp.path("name[-2]"));
//        System.out.println("resp.path(\"name[-1]\") = " + resp.path("[-1]name"));
        //get me all spartans names
        List<String>allName = resp.path("name");
        for (String each : allName) {
            System.out.println(each);
        }


    }

}

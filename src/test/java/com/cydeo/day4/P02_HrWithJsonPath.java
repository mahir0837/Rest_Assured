package com.cydeo.day4;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_HrWithJsonPath extends HrTestBase {

    @DisplayName("Get all /countries")
    @Test
    void test1() {
        Response response = get("/countries");

        //verify status code
        assertEquals(200, response.statusCode());

        //create json path
        JsonPath jsonPath = response.jsonPath();

        //get me 3rd country name
        System.out.println("3 rd country name: " + jsonPath.getString("items[2].country_name"));

        //get me 3rd and 4th country name together

        System.out.println("3 rd and 4th country name: " + jsonPath.getString("items[2,3].country_name"));

        //get me all country name where region_id is 2
        List<String> listCountries = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("listCountries = " + listCountries);
    }

    @DisplayName("GET all /employees?limit=200 with jsonpath")
    @Test
    void test2() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("limit", 200)
                .when().get("employees");
        //assert status code
        assertEquals(200,response.getStatusCode());
        JsonPath jsonPath = response.jsonPath();
        //get all emails from response
        System.out.println("-----------------------------------------");
        System.out.println("All Emails :"+jsonPath.getList("items.email"));
        //get all emails who is working as IT_PROG

        System.out.println("-----------------------------------------");
        System.out.println("All emails who is working as IT_PROG: "
                +jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email"));
        //get me all employees first names whose salary more than 10000

        System.out.println("-----------------------------------------");
        System.out.println("all employees first names whose salary more than 10000: "+
                jsonPath.getList("items.findAll {it.salary>10000}.first_name"));
        //get me all information from response who has max salary

        System.out.println("-----------------------------------------");
        System.out.println("all information from response who has max salary: "
                +jsonPath.getString("items.max {it.salary}"));
        //get me first name from response who has max salary

        System.out.println("-----------------------------------------");
        System.out.println("first name from response who has max salary: "+
                jsonPath.getString("items.max {it.salary}.first_name"));
        //get me first name from response who has min salary
        System.out.println("-----------------------------------------");
        System.out.println("first name from response who has min salary: "+
                jsonPath.getString("items.min {it.salary}.first_name"));
    }

        /*

        TASK
        Given
             accept type is application/json
        When
             user sends get request to /locations
        Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK

      */

    @Test
    void test3() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .get("/locations");
        //             response status code must be 200
        assertEquals(200,response.statusCode());
        //             content type equals to application/json
        assertEquals(ContentType.JSON.toString(),response.getContentType());
        //             get the second city with JsonPath
        System.out.println("Second city is : "+response.jsonPath().getString("items[1].city"));
        //             get the last city with JsonPath
        System.out.println("last city is : "+response.jsonPath().getString("items[-1].city"));
        //             get all country ids
        System.out.println("All country ID's : "+
                response.jsonPath().getList("items.country_id"));
        //             get all city where their country id is UK
        System.out.println(response.jsonPath().getList("items.findAll {it.country_id=='UK'}.city"));
    }
}

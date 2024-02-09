package com.cydeo.day4;

import com.cydeo.utilities.CydeoAPIBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_CydeoTrainingAPITest extends CydeoAPIBase {

     /*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify following
                firstName Mark
                batch 13
                major math
                emailAddress mark@email.com
                companyName Cydeo
                street 777 5th Ave
                zipCode 33222
    */

    @DisplayName("Get student/2")
    @Test
    void test1() {
        Response resp = given().accept(ContentType.JSON)
                .and()
                .pathParams("id", 289) //someone deleted id 2 information thats why using 289
                .get("/student/{id}");

        // Then status code should be 200
        assertEquals(200,resp.getStatusCode());
        // And content type is application/json;charset=UTF-8
        assertEquals(ContentType.JSON+";charset=UTF-8",resp.getContentType());
        // And Date header is exist
        boolean isDateExist = resp.headers().hasHeaderWithName("Date");
        assertTrue(isDateExist);
        // And Server header is envoy
        boolean isServerHeaderEnvoy = resp.header("server").equals("envoy");
        assertTrue(isServerHeaderEnvoy);

        //                firstName Mark
        String actualFirstName = resp.jsonPath().getString("students[0].firstName");
        assertEquals("Mark",actualFirstName);
        //                batch 13
        assertEquals(13,resp.jsonPath().getInt("students[0].batch"));
        //                major math
        assertEquals("math",resp.jsonPath().getString("students[0].major"));
        //                emailAddress mark@email.com
        assertEquals("mark@email.com",resp.jsonPath().getString("students[0].contact.emailAddress"));
        //                companyName Cydeo
        assertEquals("Cydeo",resp.jsonPath().getString("students[0].company.companyName"));
        //                street 777 5th Ave
        assertEquals("777 5th Ave",resp.jsonPath().getString("students[0].company.address.street"));
        //                zipCode 33222
        assertEquals(33222,resp.jsonPath().getLong("students[0].company.address.zipCode"));

    }

        /*

    TASK
    Given accept type is application/json
    And path param is 22
    When user send request /student/batch/{batch}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify all the batch number is 22
     */

    @DisplayName("Get batch/22")
    @Test
    void test2() {
        Response resp = given().accept(ContentType.JSON)
                .and()
                .pathParams("batch", 22)
                .get("/student/batch/{batch}");
        resp.prettyPrint();
        //    Then status code should be 200
        assertEquals(200,resp.getStatusCode());
        //    And content type is application/json;charset=UTF-8
        assertEquals(ContentType.JSON+";charset=UTF-8",resp.getContentType());
        //    And Date header is exist
        assertTrue(resp.getHeaders().hasHeaderWithName("Date"));
        //    And Server header is envoy
        assertEquals("envoy",resp.getHeader("server"));
        //    And verify all the batch number is 22
        List<Integer> batchList = resp.jsonPath().getList("students.batch");
        for (Integer actualEachBatchNumber : batchList) {
            assertEquals(22,actualEachBatchNumber);
        }
    }
}

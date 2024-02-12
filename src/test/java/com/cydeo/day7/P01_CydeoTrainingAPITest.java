package com.cydeo.day7;

import com.cydeo.pojo.Student;
import com.cydeo.pojo.Students;
import com.cydeo.utilities.CydeoAPIBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P01_CydeoTrainingAPITest extends CydeoAPIBase {

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
        assertEquals(200, resp.getStatusCode());
        assertEquals(ContentType.JSON + ";charset=UTF-8", resp.getContentType());
        // And Date header is exist
        boolean isDateExist = resp.headers().hasHeaderWithName("Date");
        assertTrue(isDateExist);
        // And Server header is envoy
        boolean isServerHeaderEnvoy = resp.header("server").equals("envoy");
        assertTrue(isServerHeaderEnvoy);

        JsonPath jsonPath = resp.jsonPath();
        Student student = jsonPath.getObject("students[0]", Student.class);

        //                firstName Mark

        assertEquals("Mark", student.getFirstName());
        //                batch 13
        assertEquals(13, student.getBatch());
        //                major math
        assertEquals("math", student.getMajor());
        //                emailAddress mark@email.com
        assertEquals("mark@email.com", student.getContact().getEmailAddress());
        //                companyName Cydeo
        assertEquals("Cydeo", student.getCompany().getCompanyName());
        //                street 777 5th Ave
        assertEquals("777 5th Ave", student.getCompany().getAddress().getStreet());
        //                zipCode 33222
        assertEquals(33222, student.getCompany().getAddress().getZipCode());

    }

    @DisplayName("Get student/2")
    @Test
    void test2() {
        Response resp = given().accept(ContentType.JSON)
                .and()
                .pathParams("id", 289) //someone deleted id 2 information thats why using 289
                .get("/student/{id}");

        // Then status code should be 200
        assertEquals(200, resp.getStatusCode());
        assertEquals(ContentType.JSON + ";charset=UTF-8", resp.getContentType());
        // And Date header is exist
        boolean isDateExist = resp.headers().hasHeaderWithName("Date");
        assertTrue(isDateExist);
        // And Server header is envoy
        boolean isServerHeaderEnvoy = resp.header("server").equals("envoy");
        assertTrue(isServerHeaderEnvoy);

        JsonPath jsonPath = resp.jsonPath();
        //Deserialize to Students class
        Students students = jsonPath.getObject("", Students.class);
        //we deserialize everything to Student class which is holding list of Student
        System.out.println("students = " + students);
        Student student = students.getStudents().get(0);
        //if there is no path, we can use response as method for deserialization
        //                firstName Mark

        assertEquals("Mark", student.getFirstName());
        //                batch 13
        assertEquals(13, student.getBatch());
        //                major math
        assertEquals("math", student.getMajor());
        //                emailAddress mark@email.com
        assertEquals("mark@email.com", student.getContact().getEmailAddress());
        //                companyName Cydeo
        assertEquals("Cydeo", student.getCompany().getCompanyName());
        //                street 777 5th Ave
        assertEquals("777 5th Ave", student.getCompany().getAddress().getStreet());
        //                zipCode 33222
        assertEquals(33222, student.getCompany().getAddress().getZipCode());

    }

    @DisplayName("Get student/2")
    @Test
    void test3() {
        Response resp = given().accept(ContentType.JSON)
                .and()
                .pathParams("id", 289) //someone deleted id 2 information thats why using 289
                .get("/student/{id}");

        JsonPath jsonPath=resp.jsonPath();
        com.cydeo.pojo.readyPojo.readyPojo.Student student = jsonPath.getObject("students[0]", com.cydeo.pojo.readyPojo.readyPojo.Student.class);

        System.out.println("student.getJoinDate() = " + student.getJoinDate());
        System.out.println("student.getCompany().getStartDate() = " + student.getCompany().getStartDate());
        System.out.println("student.getCompany().getAddress().getState() = " + student.getCompany().getAddress().getState());

    }
}

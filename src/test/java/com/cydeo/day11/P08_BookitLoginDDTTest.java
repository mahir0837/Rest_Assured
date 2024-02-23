package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class P08_BookitLoginDDTTest {


    //create a method to read bookitqa3 excel file information (QA3 Sheet)
    //use those information as an email and password to send a get request to /sign endpoint
    //verify you got 200 for each request
    //print accessToken for each request

    String accessToken;

    @ParameterizedTest
    @MethodSource("getAllEmailAndPassword")
    public void tokenVerification(Map<String,String>userInfo){
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .queryParams("password", userInfo.get("password"))
                .queryParams("email", userInfo.get("email"))
                .when()
                .get("https://api.qa3.bookit.cydeo.com/sign")
                .then()
                .statusCode(200)
                .extract().jsonPath();
        accessToken=jsonPath.getString("accessToken");
        System.out.println("accessToken = " + accessToken);
    }
    public static List<Map<String,String>> getAllEmailAndPassword(){
        ExcelUtil excelUtil=new ExcelUtil("src/test/resources/BookItQa3.xlsx","QA3");
       return excelUtil.getDataList();
    }

}
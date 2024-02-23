package com.cydeo.day11;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P09_SpartanPOSTDTT extends SpartanAuthTestBase {

    /**
     * POST SPARTAN DDT
     * <p>
     * Given content type is json
     * And accept type is json
     * When I POST Spartan
     * And status code needs to 201
     * Verify spartan informations macthing with dynamic that we provide
     * <p>
     * Generate DUMMY DATA
     * https://www.mockaroo.com/
     * <p>
     * name
     * gender
     * phone
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/MockData.csv", numLinesToSkip = 1)
    public void test(String name, String gender, String phone) {
        Map<String, Object> mapList = new LinkedHashMap<>();
        mapList.put("name", name);
        mapList.put("gender", gender);
        mapList.put("phone", phone);

        JsonPath jsonPath =
                given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .auth().basic("editor", "editor")
                        .when()
                        .body(mapList)
                        .post("/api/spartans").prettyPeek()
                        .then()
                        .statusCode(201).extract().jsonPath();

        String nameActual = jsonPath.getString("name");
        String genderActual = jsonPath.getString("gender");
        String phoneActual = jsonPath.getString("phone");

        assertThat(nameActual, equalTo(mapList.get("data.name")));
        assertThat(genderActual, equalTo(mapList.get("data.gender")));
        assertThat(phoneActual, equalTo(mapList.get("data.phone")));
    }

   @ParameterizedTest
   @CsvFileSource(resources = "/Delete.csv",numLinesToSkip = 1)
    public void deleteSpartans(int id) {

            given()
                    .auth()
                    .basic("admin", "admin")
                    .pathParam("id" , id)
                    .when()
                    .delete("/api/spartans/{id}")
                    .then()
                    .statusCode(204);

    }
}
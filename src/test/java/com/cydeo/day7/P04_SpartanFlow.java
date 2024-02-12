package com.cydeo.day7;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class P04_SpartanFlow extends SpartanTestBase {

    private static Spartan spartan;

    @DisplayName("Post Spartan")
    @Test
    @Order(1)
    public void postSpartan() {
        spartan = Spartan.createSpartan();
        JsonPath jsonPath = given().log().body()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON).extract().jsonPath();
        spartan.setId( jsonPath.getInt("data.id"));
        spartan.setName(jsonPath.getString("data.name"));
        String ActualMessage = jsonPath.getString("success");
        assertThat("A Spartan is Born!", is(equalTo(ActualMessage)));

    }


    @DisplayName("Get Request")
    @Test
    @Order(2)
    void getSpartanWithId() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .pathParam("id", spartan.getId())
                .when()
                .get("/api/spartans/{id}")
                .then()
                .extract().jsonPath();
        String actualName=jsonPath.getString("name");
        assertThat(spartan.getName(),is(equalTo(actualName)));
    }

    @DisplayName("Put Spartan")
    @Test
    @Order(3)
    public void putSpartan() {
        Map<String,Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name",spartan.getName()+" Put");
        requestBodyMap.put("gender",spartan.getGender());
        requestBodyMap.put("phone",spartan.getPhone());
        given()
                .contentType(ContentType.JSON)
                .pathParam("id",spartan.getId())
                .body(requestBodyMap).log().body()
                .when()
                .put("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(204);
        spartan.setName(requestBodyMap.get("name").toString());
    }
    @DisplayName("Get Request")
    @Test
    @Order(4)
    void getSpartanWithIdSecond() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .pathParam("id", spartan.getId())
                .when()
                .get("/api/spartans/{id}")
                .then()
                .extract().jsonPath();
        String actualName=jsonPath.getString("name");
        assertThat(spartan.getName(),is(equalTo(actualName)));
    }
    @DisplayName("Delete Spartan")
    @Test
    @Order(5)
    public void deleteSpartan() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id",spartan.getId())
                .when()
                .delete("api/spartans/{id}")
                .then()
                .statusCode(204);
        System.out.println(spartan.getId());

    }

}
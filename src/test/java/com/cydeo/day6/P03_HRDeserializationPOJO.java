package com.cydeo.day6;

import com.cydeo.pojo.HrEmployee;
import com.cydeo.pojo.HrRegion;
import com.cydeo.pojo.HrRegionRequiredField;
import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_HRDeserializationPOJO extends HrTestBase {

    @DisplayName("GET regions to deserialize to POJO")
    @Test
    void test1() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when()
                .get("/regions")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        //get first region from items array and convert it to Region Class
        System.out.println("jsonPath.getObject(\"items[0]\", Region.class) = " +
                jsonPath.getObject("items[0]", HrRegion.class));
        List<HrRegion> itemList = jsonPath.getList("items", HrRegion.class);

        System.out.println("itemList.get(1).getRegionName() = " + itemList.get(1).getRegionName());
    }

    @DisplayName("Get employee to deserialize to pojo only required fields")
    @Test
    void test2() {
        JsonPath jsonPath = get("/employees")
                .then().statusCode(200)
                .extract().jsonPath();
        HrEmployee employee1 = jsonPath.getObject("items[0]", HrEmployee.class);
        System.out.println("employee1 = " + employee1);
    }

        /*
    TASK

    Given accept is application/json
    When send request  to /regions endpoint
    Then status should be 200
            verify that region ids are 1,2,3,4
            verify that regions names Europe ,Americas , Asia, Middle East and Africa
            verify that count is 4
        -- Create Regions POJO
        -- And ignore field that you dont need


     */

    @Test
    void test3() {

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when()
                .get("/regions")
                .then()
                .statusCode(200).extract().jsonPath();

        List<HrRegionRequiredField> actualItemList = jsonPath.getList("items", HrRegionRequiredField.class);
        List<Integer> actualItemListId = jsonPath.getList("items.region_id", Integer.class);
        List<String> actualItemListRegionName = jsonPath.getList("items.region_name", String.class);

        List<Integer>expectedItemListId=Arrays.asList(1,2,3,4);
        List<String>expectedIremListRegionName= Arrays.asList("Europe" ,"Americas" , "Asia", "Middle East and Africa");

        assertThat(expectedIremListRegionName,is(equalTo(actualItemListRegionName)));
        assertThat(expectedItemListId,is(equalTo(actualItemListId)));

        assertThat(actualItemList,is(hasSize(4)));

    }
}

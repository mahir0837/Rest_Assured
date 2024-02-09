package com.cydeo.day3;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_HRWithResponsePath extends HrTestBase {

    @DisplayName("Get request to countries with using response path")
    @Test
    void name() {
        Response resp = given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");
        resp.prettyPrint();

        //print limit
        System.out.println("resp.path(\"limit\") = " + resp.path("limit"));
        //print hasmore
        System.out.println("resp.path(\"hasMore\") = " + resp.path("hasMore"));
        //print second country name
        System.out.println("resp.path(\"country_name[2]\") = " + resp.path("items[1].country_name"));
        //print 4 th element of country_name
        System.out.println("resp.path(\"item[3]\") = " + resp.path("items[3].country_name"));
        //print 3 th element href
        System.out.println("resp.path(\"items[2].links[0].href\") = " + resp.path("items[2].links[0].href"));
        //get all countries names
        System.out.println("resp.path(\"items.country_name\") = " + resp.path("items.country_name"));

        //verify all region id equals 2
        List<Integer> allRegionId = resp.path("items.region_id");
        for (Integer each : allRegionId) {
            assertEquals(2,each);
        }
    }
}

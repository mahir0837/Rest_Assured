package com.cydeo.day10;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P02_MovieXML {

    @Test
    void test1() {

        Response response = given()
                .queryParam("apikey", "81815fe6")
                .queryParam("r", "xml")
                .queryParam("t", "Inception")
                .when().get("https://www.omdbapi.com").prettyPeek();
        //get me year attribute
        System.out.println("response.xmlPath().getString(\"root.movie.@year\") = " + response.xmlPath().getString("root.movie.@year"));

        //get me director
        System.out.println("response.xmlPath().getString(\"root.movie.@director\") = " + response.xmlPath().getString("root.movie.@director"));

    }

    /**
     * http://www.omdbapi.com?apikey=81815fe6&r=xml&s=Harry Potter
     * --Try to get all years and verify they are greater then 2000
     * --Print each title and make sure each of them contains Harry Potter
     * --verify total result is 127
     */

    @Test
    void test2() {

        Response response = given()
                .accept(ContentType.XML)
                .queryParam("apikey", "81815fe6")
                .queryParam("r", "xml")
                .queryParam("s", "Harry Potter")
                .when()
                .get("http://www.omdbapi.com")
                .then().extract().response();
        XmlPath xmlPath = response.xmlPath();
        List<String> allyears = xmlPath.getList("root.result.@year");
        System.out.println(allyears);
        assertThat(allyears,everyItem(greaterThan("2000")));

        List<String>allTitle=xmlPath.getList("root.result.@title");
        System.out.println("allTitle = " + allTitle);
        assertThat(allTitle,everyItem(containsString("Harry Potter")));

        String totalResult=xmlPath.getString("root.@totalResults");
        System.out.println("totalResult = " + totalResult);
        assertThat(totalResult,is(equalTo("140")));
    }
}

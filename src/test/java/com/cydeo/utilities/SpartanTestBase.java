package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public class SpartanTestBase{

    @BeforeAll
    public static void init(){
        baseURI="http://54.208.32.156";
        port=7000;
        basePath="/api";
    }

}

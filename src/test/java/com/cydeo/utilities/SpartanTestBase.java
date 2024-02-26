package com.cydeo.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public class SpartanTestBase{

    public Logger log= LogManager.getLogger();
    @BeforeAll
    public static void init(){
        baseURI="http://54.208.32.156:8000";
    }

}

package com.cydeo.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P06_MethodSourceTest {

    @ParameterizedTest
    @MethodSource("getNames")
    public void test1(String name) {

        System.out.println("name = " + name);

    }

    public static List<String> getNames() {

        List<String> nameList = Arrays.asList("Steven", "TJ", "Kimberly", "Mike");

          /*  -Can we read data from database and assign the list ?
                -Create conn, run query , store them and feed to Parameterized ?
            -Can we get data from 3rd party APIs ( that we consume to get data and use into our API)
                -use Java Knowledge and RestAssured
           - this makes method source more powerful than others.
         */

        return nameList;
    }

}

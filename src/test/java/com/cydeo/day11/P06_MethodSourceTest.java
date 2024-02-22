package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class P06_MethodSourceTest {

    @ParameterizedTest
    @MethodSource("getNames")
    public void test1(String name) {

        System.out.println("name = " + name);

    }

    @ParameterizedTest
    @MethodSource("getExcellData")
    public void test2(Map<String,String>userInfo){
        System.out.println("userInfo = " + userInfo);
        System.out.println("userInfo.get(\"Email\") = " + userInfo.get("Email"));
        System.out.println("userInfo.get(\"Password\") = " + userInfo.get("Password"));
    }

    public static List<Map<String, String>> getExcellData() {
        ExcelUtil excelUtil = new ExcelUtil("src/test/resources/Library.xlsx", "Library1-short");
        return excelUtil.getDataList();
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

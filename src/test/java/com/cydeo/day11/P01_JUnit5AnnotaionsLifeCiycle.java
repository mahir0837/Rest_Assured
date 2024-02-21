package com.cydeo.day11;

import org.junit.jupiter.api.*;

public class P01_JUnit5AnnotaionsLifeCiycle {

    //TestNG-->@BeforeClass
    @BeforeAll
    public static void init(){
        System.out.println("-----Before All is running------");
    }
    @BeforeEach
    public void initEach(){
        System.out.println("-----Before Each is running------");
    }
    @Test
    public void test1(){
        System.out.println("--------Test1 is running------");
    }

    @Test
    public void test2(){
        System.out.println("--------Test2 is running------");
    }
    @AfterEach
    public void destroyEach(){
        System.out.println("-----After each is running------");
    }
    @AfterAll
    public static void destroy(){
        System.out.println("-----After All is running------");
    }
}

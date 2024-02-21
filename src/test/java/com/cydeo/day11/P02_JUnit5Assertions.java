package com.cydeo.day11;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.*;

public class P02_JUnit5Assertions {

    /**
     *
     * HARD ASSERT -->
     * - Test Execution will be aborted if the assert condition is not met
     * - Rest of the execution will stop
     * - Use Case --> if we are checking critical functionally of the app we can check with hard assert
     *
     */

    @Test
    void hardAssert() {
        assertEquals(10,5+5);
        System.out.println("----First Assert is done----");

        assertEquals(10,5+4);
        System.out.println("----Second Assert is done----");

        assertEquals(10,5+5);
        System.out.println("----Third Assert is done----");
    }

    /**
     * SOFT ASSERT --> VERIFY (Soft Assertion is implementation of VERIFY
     * -Test execution will continue till end of the code fragment even if one the assertion is failing
     *
     */
    @DisplayName("JUNIT 5 soft assertion is implemented")
    @Test
    void softAssert() {
        assertAll("Soft assertion practice",
                ()->assertEquals(10,5+5),
                ()->assertEquals(10,5+3),
                ()->assertEquals(10,5+4),
                ()->assertEquals(10,5+5));
    }
}

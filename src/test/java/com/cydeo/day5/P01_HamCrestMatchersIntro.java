package com.cydeo.day5;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_HamCrestMatchersIntro {

    @Test
    public void numbers() {
        //junit 5 assert equals method
        //HamcrestMatchers comes from Rest assured dependency
        //2 static import to get rid of class names ,directly call assertThat and Matchers
        //import static org.hamcrest.MatcherAssert.*;
        //import static org.hamcrest.Matchers.*;

        //Hamcrest Matchers
        //Matchers has 2 overloaded methods
        //-First one will take value to check
        //-Second one will take another Matchers to make it readable / to add new assert functionality
        assertEquals(9,6+3);

        assertThat(6+3,is(9));
        assertThat(6+3,is(equalTo(9)));
        assertThat(6+3,equalTo(9));

        /**
         is(someValue)
         is(equalTo(someValue))
         equalTo(someValue)
         All of them same in terms of assertion
         */

        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        /**
         * They are all the same for assertion
         */

        assertThat(5+6,is(greaterThan(10)));
        assertThat(5+6,is(greaterThanOrEqualTo(11)));
        assertThat(5+6,greaterThanOrEqualTo(11));
        assertThat(5+6,lessThanOrEqualTo(11));
        assertThat(5+6,lessThan(12));
    }

    @Test
    public void testString() {

        String msg="API is fun!!";
        assertThat(msg,is(equalTo("API is fun!!")));
        assertThat(msg,equalTo("API is fun!!"));
        assertThat(msg,equalToIgnoringCase("api is fun!!"));

        assertThat(msg,startsWith("API"));
        assertThat(msg,startsWithIgnoringCase("api"));

        assertThat(msg,endsWith("fun!!"));
        assertThat(msg,endsWithIgnoringCase("FUn!!"));

        assertThat(msg,containsString("is"));
        assertThat(msg,containsStringIgnoringCase("IS"));

        assertThat(msg,not("FUNN!!"));
        assertThat(msg,is(not("FUNN!!")));
    }

    @Test
    public void testCollection() {
        List<Integer>numberList= Arrays.asList(3,5,1,77,44,76); //6 elements

        //how to check size of element
        assertThat(numberList,hasSize(6));
        assertThat(numberList,hasSize(greaterThan(5)));

        //how to check 77 is collection
        assertThat(numberList,hasItem(77));

        //how to check 44 and 76 into the collection
        assertThat(numberList,hasItems(44,76));
     // assertThat(numberList,hasItems(44,76,4)); // to make it fail

        // loop through each off the element and make sure they are matching with Matchers inside the every Item
        assertThat(numberList,everyItem(greaterThanOrEqualTo(1)));

        //check if you have all values and position are correct
        assertThat(numberList,containsInRelativeOrder(3,5,1,77,44));
        //check if you have all values in there  and position might correct or no
        assertThat(numberList,containsInAnyOrder(76,3,5,1,77,44));
    }
}

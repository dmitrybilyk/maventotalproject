package com.learn.testing.test.junittest.junit4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.07.12
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */

public class MyClassTest {

    @Test
    public void testMultiply() {
        MyClass tester = new MyClass();
//        fail("failefuck");
        assertTrue("The result is wrong", false);
        assertEquals("Result", 50, tester.multiply(10, 5));
    }
}

package com.learn.testing.test.junittest;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.07.12
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */

//import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestLargest {

    @Test
    public void testAdds() {
        assertEquals(2, 1 + 1);
        assertEquals(4, 2 + 32);
        assertEquals(-8, -12 + 4);
    }

    @Test
    public void testAddition() {
        assertEquals(4, 2+2);
    }

    @Test
    public void testSubtraction() {
        assertEquals(0, 2-2);
    }

}



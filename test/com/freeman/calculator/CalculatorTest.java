package com.freeman.calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by freeman on 07.08.2016.
 */
public class CalculatorTest {
    private Calculator testee;

    @Before
    public void setUp() {
        testee = new Calculator();
    }

    @Test
    public void testAdding() {
//        Assert.assertEquals("3.5", testee.calculate("3+4*2/(1-5)^2"));
        Assert.assertEquals("36", testee.calculate("( − 1 − 5 ) ^ 2"));
    }
}

package com.freeman.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dmitriy Nefedchenko on 27.10.2016.
 */
public class InputFormatterTest {
    @Test
    public void shouldOmitDecimalNumbers() {
        assertEquals("1", InputFormatter.format("1"));
    }

    @Test
    public void shouldOmitShortFloatingPointNumbers() {
        assertEquals("1.1", InputFormatter.format("1.1"));
        assertEquals("1.12", InputFormatter.format("1.12"));
        assertEquals("1.123", InputFormatter.format("1.123"));
        assertEquals("1.1234", InputFormatter.format("1.1234"));
        assertEquals("1.12345", InputFormatter.format("1.12345"));
        assertEquals("1.123456", InputFormatter.format("1.123456"));
        assertEquals("1.1234567", InputFormatter.format("1.1234567"));
    }

    @Test
    public void shouldFormatLongFloatingPointNumbers() {
        assertEquals("1.1234567", InputFormatter.format("1.12345678"));
        assertEquals("111.1234567", InputFormatter.format("111.1234567898"));
    }

    @Test
    public void shouldNotFormatNotNumbers() {
        assertEquals("Error", InputFormatter.format("Error"));
        assertEquals("Err0r", InputFormatter.format("Err0r"));
    }

    @Test
    public void shouldTrimTrailingZeros() {
        assertEquals("1", InputFormatter.format("1.0"));
        assertEquals("1", InputFormatter.format("1.0000000000"));
    }
}

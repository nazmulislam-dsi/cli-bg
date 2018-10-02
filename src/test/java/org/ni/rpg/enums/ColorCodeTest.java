package org.ni.rpg.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by nazmul on 10/2/2018.
 */
public class ColorCodeTest {
    @Test
    public void testColorCode() {
        assertEquals(ColorCode.ANSI_RED.getColorCode(), "\u001B[31m");
    }
}

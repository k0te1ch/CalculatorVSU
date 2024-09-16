package org.example;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class MainTest {
    @Test
    public void testAdd() {
        Main main = new Main();
        assertEquals(4, (int) main.apply(2, 2));
        assertEquals(0, (int) main.apply(-2, 2));
        assertEquals(-4, (int) main.apply(-2, -2));
    }
}
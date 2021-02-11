package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorTest {
    Processor proc;

    @BeforeEach
    void setUp() {
        proc = new Processor();
    }

    @Test
    void testCalc1() {
        double[] array = {5, 36, 45, 63, 253, -34, -75, 2, 46, 232, 653, -333};
        double result = proc.calc(array);
        double expected = 653;
        assertEquals(expected, result, "Result not correct.");
    }

    @Test
    void testCalc2() {
        double[][] array = {
                {166, 116, -431, -210, -205, -353},
                {-281, 22, 140, -15, 460, -397},
                {-203, 352, -225, -31, -178, -146},
                {140, 174, 7, -276, -373, 235},
                {-169, -39, 335, 459, 120, -497},
                {376, 84, -109, 190, 193, 135}
        };
        double result = proc.calc(array);
        double expected = -39;
        assertEquals(expected, result, "Result not correct.");
    }
}
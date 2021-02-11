package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Main main;

    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @Test
    void testCalculateY1() {
        main.fillX(2,5, 0.005);
        double x = main.x[0];
        double expected = 0.17317818956;
        double result = main.calculateY(x);
        assertEquals(expected,result,0.00001, "y must be x*x");
    }

    @Test
    void testCalculateY2() {
        main.fillX(2,5, 0.005);
        double x = main.x[280];
        double expected = 0.93469874517;
        double result = main.calculateY(x);
        assertEquals(expected,result,0.00001, "y must be x*x");
    }

    @Test
    void testCalculateY3() {
        main.fillX(2,5, 0.005);
        double x = main.x[600];
        double expected = -1.5433290828;
        double result = main.calculateY(x);
        assertEquals(expected,result,0.00001, "y must be x*x");
    }

    @Test
    void testCountSteps() {
        double begin = 2;
        double end = 5;
        double step = 0.005;
        int expected = 601;
        int result = main.countSteps(begin, end, step);
        assertEquals(expected, result, 0.001, "not correct step count");
    }

    @Test
    void testFillX() {
        main.fillX(1,5, 0.2);
        assertEquals(3.4, main.x[12], 0.001, "x[12] must be 3.4");
    }

    @Test
    void testFillY() {
        main.fillX(2,5, 0.005);
        main.fillY();
        double expected = -1.5433290828;
        int i = 600;
        assertEquals(expected, main.y[i], 0.001, "not correct fillY()");
    }

    @Test
    void testMax() {
        double y[] = {5, 6, 1, -1, 3, 8, 2};
        double expected = 5;
        double result = main.max(y);
        assertEquals(expected, result, 0.001, "not correct max()");
    }

    @Test
    void testMin() {
        double y[] = {5, 6, 1, -1, 3, 8, 2};
        int expected = 3;
        int result = main.min(y);
        assertEquals(expected, result, 0.001, "not correct min()");
    }

    @Test
    void testSum() {
        double y[] = {5, 6, 1, -1, 3, 8, 2};
        double expected = 24;
        double result = main.sum(y);
        assertEquals(expected, result, 0.001, "not correct sum()");
    }

    @Test
    void testAverage() {
        double y[] = {5, 6, 1, -1, 3, 8, 2};
        double expected = 3.428571428571429;
        double result = main.average(y);
        assertEquals(expected, result, 0.001, "not correct average()");
    }
}
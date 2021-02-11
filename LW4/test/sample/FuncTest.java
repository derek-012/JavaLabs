package sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncTest {

    Func func;
    @BeforeEach
    void setUp() {
        func = new Func();
    }

    @Test
    void countSteps() {
        double begin = 1, end = 4, step = 0.05;
        int expected = 61;
        assertEquals(expected, func.countSteps(begin, end, step), "Количество шагов не совпадает");
    }
}
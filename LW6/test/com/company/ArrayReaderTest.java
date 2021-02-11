package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ArrayReaderTest {

    ArrayReader ar;

    @BeforeEach
    void setUp() {
        ar = new ArrayReader();
    }

    final String testOneDimFilename1 = "./src/resources/testAR1.txt";
    final String testOneDimFilename2 = "./src/resources/testAR2.txt";
    final String testOneDimFilename3 = "./src/resources/testAR3.txt";
    final String testOneDimFilename4 = "./src/resources/testAR4.txt";

    @Test
    void readOneDimArray() {
        File file = new File(testOneDimFilename1);
        double[] numbers = ar.readOneDimArray(file);
        double[] expected = {12, 5, 64, 2, 3, 42, -5};
        assertArrayEquals(expected, numbers, "Arrays not equals.");
    }

    @Test
    void testReadOneDimArray() {
        double[] numbers = ar.readOneDimArray(testOneDimFilename2);
        double[] expected = {5, 36, 45, 63, 253, -34, -75, 2, 46, 232, 653, -333};
        assertArrayEquals(expected, numbers, "Arrays not equals.");
    }

    @Test
    void readTwoDimArray() {
        File file = new File(testOneDimFilename3);
        double[][] numbers = ar.readTwoDimArray(file);
        double[][] expected = {
                {-475, -384, -130, 87, 7},
                {209, -483, -439, 101, 32},
                {-100, -457, -473, 348, -482},
                {-297, -164, 322, -357, 238},
                {-383, -437, -182, 159, -274}
        };
        assertArrayEquals(expected, numbers, "Arrays not equals.");
    }

    @Test
    void testReadTwoDimArray() {
        double[][] numbers = ar.readTwoDimArray(testOneDimFilename4);
        double[][] expected = {
                {166, 116, -431, -210, -205, -353},
                {-281, 22, 140, -15, 460, -397},
                {-203, 352, -225, -31, -178, -146},
                {140, 174, 7, -276, -373, 235},
                {-169, -39, 335, 459, 120, -497},
                {376, 84, -109, 190, 193, 135}
        };
        assertArrayEquals(expected, numbers, "Arrays not equals.");
    }
}
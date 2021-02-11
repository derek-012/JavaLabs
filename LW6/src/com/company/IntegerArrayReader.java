package com.company;

import java.io.File;

public interface IntegerArrayReader {
    double[] readOneDimArray(File file);
    double[] readOneDimArray(String fileName);

    double[][] readTwoDimArray(File file);
    double[][] readTwoDimArray(String filename);
}

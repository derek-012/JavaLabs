package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayReader implements IntegerArrayReader{
    double[] stringToIntArray(String s) {
        double[] result;
        try {
            result = Arrays.stream(s.trim().split(" +")).mapToDouble(Double::parseDouble).toArray();
        } catch (NumberFormatException e) {
            return null;
        }
        return result;
    }

    public double[] readOneDimArray(File file) {
        try (Stream<String> fileStream = Files.lines(file.toPath())) {
            int n;

            List<String> lines = fileStream.collect(Collectors.toList());
            try {
                n = Integer.parseInt(lines.get(0).trim());
                lines.remove(0);
            } catch (NumberFormatException e) {
                return null;
            }

            double[] numbers;

            numbers = stringToIntArray(lines.get(0));

            return (numbers.length == n) ? numbers : null;
        } catch (IOException e) {
            System.out.println("File not found.");
            return null;
        }
    }

    public double[] readOneDimArray(String filename) {
        try (Stream<String> fileStream = Files.lines(Path.of(filename))) {
            int n;

            List<String> lines = fileStream.collect(Collectors.toList());
            try {
                n = Integer.parseInt(lines.get(0).trim());
                lines.remove(0);
            } catch (NumberFormatException e) {
                return null;
            }

            double[] numbers;

            numbers = stringToIntArray(lines.get(0));

            return (numbers.length == n) ? numbers : null;
        } catch (IOException e) {
            System.out.println("File not found.");
            return null;
        }
    }

    public double[][] readTwoDimArray(File file) {
        try (Stream<String> fileStream = Files.lines(file.toPath())) {
            int n;

            List<String> lines = fileStream.collect(Collectors.toList());
            try {
                n = Integer.parseInt(lines.get(0).trim());
                lines.remove(0);
            } catch (NumberFormatException e) {
                return null;
            }

            double[][] numbers = new double[n][];

            for (int i = 0; i < n; i++)
                numbers[i] = stringToIntArray(lines.get(i));

            return numbers;
        } catch (IOException e) {
            System.out.println("File not found.");
            return null;
        }
    }

    public double[][] readTwoDimArray(String filename) {
        try (Stream<String> fileStream = Files.lines(Path.of(filename))) {
            int n;

            List<String> lines = fileStream.collect(Collectors.toList());
            try {
                n = Integer.parseInt(lines.get(0).trim());
                lines.remove(0);
            } catch (NumberFormatException e) {
                return null;
            }

            double[][] numbers = new double[n][];

            for (int i = 0; i < n; i++) {
                numbers[i] = stringToIntArray(lines.get(i));
                if (numbers[i].length != n)
                    return null;
            }

            return numbers;
        } catch (IOException e) {
            System.out.println("File not found.");
            return null;
        }
    }
}

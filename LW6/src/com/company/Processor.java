package com.company;

public class Processor implements ArrayProcessor {
    public double calc(double[] array) {
        double max = array[0];
        int n = array.length;
        for(int i = 0; i < n; i += 2)
            if (max < array[i])
                max = array[i];
        return max;
    }

    public double calc(double[][] array) {
        int n = array.length;
        int m = (n / 2);
        double max = 0;

        for (int i = m; i < n; i++)
            for (int j = (n - i - 1); j <= n - (n - i); j++)
                if (array[i][j] < 0)
                    if (array[i][j] > max || max == 0)
                        max = array[i][j];

        return max;
    }
}

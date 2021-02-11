package com.company;

public class Main {
    double x[];
    double y[];

    final double BEGIN = 3;
    final double END = 5;
    final double STEP = 0.005;
    final double EPS = 0.000001;


    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        fillX(BEGIN, END, STEP);
        fillY();
        printMinMax();
    }

    public double calculateY(double x) {
        if (x > 3.4 + EPS) {
            return (Math.sin(x) * Math.log(x));
        } else {
            return Math.cos(x) * Math.cos(x);
        }
    }

    public int countSteps(double begin, double end, double step) {
        return (int)((end - begin) / step) + 1;
    }

    public void fillX(double begin, double end, double step) {
        int count = countSteps(begin, end, step);
        x = new double[count];
        for (int i = 0; i < count; i++) {
            x[i] = begin + step * i;
        }
    }

    public void fillY() {
        y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] = calculateY(x[i]);
        }
    }

    public int max(double[] a) {
        int max = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[max])
                max = i;
        }
        return max;
    }

    public int min(double[] a) {
        int min = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[min])
                min = i;
        }
        return min;
    }

    public double sum(double[] y) {
        double result = 0;
        for (int i = 0; i < y.length; i++) {
            result += y[i];
        }
        return result;
    }

    public double average(double[] y) {
        return sum(y) / y.length;
    }

    public void printMinMax() {
        System.out.println("Значения функции:");
        int minNum = min(y);
        System.out.printf("\tНаименьшее - №%d:\n\t\tx = %f\n\t\ty = %f\n", minNum, x[minNum], y[minNum]);
        int maxNum = max(y);
        System.out.printf("\tНаибольшее - №%d:\n\t\tx = %f\n\t\ty = %f\n", maxNum, x[maxNum], y[maxNum]);
    }
}

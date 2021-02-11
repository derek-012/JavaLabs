package sample;

import java.util.ArrayList;

public class Func {
    double x[];
    double y[];

    double BEGIN;
    double END;
    double STEP;
    final double EPS = 0.000001;

    Func() { }

    public void setData(double begin, double end, double step) {
        BEGIN = begin;
        END = end;
        STEP = step;
        fillX(begin, end, step);
        fillY();
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

    private double getStep(int i) {
        return BEGIN + STEP * i;
    }

    public void fillX(double begin, double end, double step) {
        int count = countSteps(begin, end, step);
        x = new double[count];
        for (int i = 0; i < count; i++) {
            x[i] = getStep(i);
        }
    }

    public void fillY() {
        y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] = calculateY(x[i]);
        }
    }

    private int max(double[] a) {
        int max = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[max])
                max = i;
        }
        return max;
    }

    private int min(double[] a) {
        int min = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[min])
                min = i;
        }
        return min;
    }

    public int getMax() {
        return this.max(y);
    }

    public int getMin() {
        return this.min(y);
    }

    public double getSum() {
        return this.sum(y);
    }

    public double getAverage() {
        return this.average(y);
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

    public String getValuesOfStep(int i) {
        return new String("f(" + x[i] + ") = " + y[i]);
    }

    public int getStepsCount() {
        return countSteps(BEGIN, END, STEP);
    }

    public ArrayList<Point> getPointsList() {
        ArrayList<Point> list = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            list.add(new Point(getStep(i), x[i], y[i]));
        }
        return list;
    }
}

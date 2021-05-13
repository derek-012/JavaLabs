package sample;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.IntStream;

public class IntegralCalculator {
    private double start;
    private double end;
    private int n;
    private DoubleUnaryOperator f;
    private double h;

    IntegralCalculator(double start, double end, int n, DoubleUnaryOperator f) {
        this.start = start;
        this.end = end;
        this.n = n;
        this.f = f;
        h = (end - start) / n;
    }

    public double calculate() {
        return IntStream.range(1, n).mapToDouble(i -> start + i * h).map(f).sum() * h;
    }
}

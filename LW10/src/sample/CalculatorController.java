package sample;

import java.util.function.DoubleUnaryOperator;

public class CalculatorController {
    double start;
    double end;
    int n;
    int threads;
    double delta;
    DoubleUnaryOperator f;
    double result;

    int finishedThreads;

    CalculatorController(double start, double end, int n, int threads, DoubleUnaryOperator f) {
        this.start = start;
        this.end = end;
        this.n = n;
        this.f = f;
        this.threads = threads;
        delta = (end - start) / threads;
        result = 0;

        finishedThreads = 0;
    }

    public double calculate() {
        for (int i = 0; i < threads; i++) {
            ThreadIntegralCalculator t = new ThreadIntegralCalculator(this, start + i * delta, start + (i + 1) * delta, n / threads, Function::calculate);
            new Thread(t).start();
        }

        try {
            synchronized (this) {
                while (finishedThreads < threads) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public synchronized void sendResult(double r) {
        result += r;
        finishedThreads++;
        notify();
    }
}

package sample;

import java.util.function.DoubleUnaryOperator;

public class ThreadIntegralCalculator implements Runnable {
    IntegralCalculator calculator;
    CalculatorController controller;

    ThreadIntegralCalculator(CalculatorController controller, double start, double end, int n, DoubleUnaryOperator f) {
        calculator = new IntegralCalculator(start, end, n, f);
        this.controller = controller;
    }

    @Override
    public void run() {
        double result = calculator.calculate();
        controller.sendResult(result);
    }
}

package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
    @FXML public TextField TFStart, TFEnd, TFCount, TFResult, TFTime;

    public void calculate() {
        int n = 1000000000;
        CalculatorController controller = new CalculatorController(Double.parseDouble(TFStart.getText()), Double.parseDouble(TFEnd.getText()), n, Integer.parseInt(TFCount.getText()), Function::calculate);
        long startTime = System.nanoTime();
        double result = controller.calculate();
        long endStart = System.nanoTime();
        TFResult.setText(String.valueOf(result));
        TFTime.setText((((double)endStart - (double)startTime) / 1000000) + " ms");
    }
}

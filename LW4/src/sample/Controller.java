package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller {

    private final Func func = new Func();

    @FXML TextField tfBegin;
    @FXML TextField tfEnd;
    @FXML TextField tfStep;

    @FXML Label lStepsCount;
    @FXML Label lMax;
    @FXML Label lMin;
    @FXML Label lSum;
    @FXML Label lAverage;

    @FXML TableView<Point> table;
    @FXML TableColumn<Point, String> tStep;
    @FXML TableColumn<Point, String> tX;
    @FXML TableColumn<Point, String> tY;

    boolean tableInitiated = false;

    public void initTable() {
        tStep.setCellValueFactory(new PropertyValueFactory<>("step"));
        tX.setCellValueFactory(new PropertyValueFactory<>("x"));
        tY.setCellValueFactory(new PropertyValueFactory<>("y"));
    }

    public void closeProgram() {
        Platform.exit();
    }

    public void clearFields() {
        tfBegin.clear();
        tfEnd.clear();
        tfStep.clear();
        lStepsCount.setText("");
        lMax.setText("");
        lMin.setText("");
        lSum.setText("");
        lAverage.setText("");
        table.getItems().clear();
    }

    public void calculate() {
        try {
            func.setData(Double.parseDouble(tfBegin.getText()), Double.parseDouble(tfEnd.getText()), Double.parseDouble(tfStep.getText()));
            lStepsCount.setText("" + func.getStepsCount());
            lMax.setText(func.getValuesOfStep(func.getMax()));
            lMin.setText(func.getValuesOfStep(func.getMin()));
            lSum.setText("" + func.getSum());
            lAverage.setText("" + func.getAverage());


            if (!tableInitiated)
                initTable();
            table.setItems(getDataPoints());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка при обработке данных");
            alert.setHeaderText("Некорректные входные данные");
            alert.setContentText("Проверьте правильность введенных данных.\nВ полях для ввода данных не должно присутствовать ничего кроме цифр и точки.");
            alert.showAndWait();
        }
    }

    private ObservableList<Point> getDataPoints() {
        return FXCollections.observableList(func.getPointsList());
    }
}

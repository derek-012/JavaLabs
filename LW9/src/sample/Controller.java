package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import sample.data.Car;

public class Controller implements Initializable {
    @FXML ChoiceBox<String> CBModelA, CBModelB, CBModelF;

    @FXML TableView<Car> TCars;
    @FXML TableColumn<Car, String> TCID, TCModel, TCYear, TCPrice, TCRegNum;

    @FXML TextField TFExp, TFPrice, TFYear;

    CarData data;

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        alert.setAlertType(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    Alert alert = new Alert(Alert.AlertType.WARNING);

    private boolean isYear(String s) {
        return Pattern.matches("(19[6-9]\\d)|(20(([01]\\d)|(2[01])))", s);
    }

    private boolean isNumber(String s) {
        return Pattern.matches("\\d+", s);
    }

    private void initTable() {
        TCID.setCellValueFactory(p -> new SimpleObjectProperty<>(String.valueOf(p.getValue().getID())));

        TCModel.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getModel()));

        TCYear.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getYearString()));

        TCPrice.setCellValueFactory(p -> new SimpleObjectProperty<>(String.valueOf(p.getValue().getPrice())));

        TCRegNum.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getRegNum()));

        TCars.setEditable(true);
    }

    private void refreshModels() {
        List<String> list = new ArrayList<>(data.getModelList());

        CBModelA.getItems().setAll(list);
        CBModelB.getItems().setAll(list);
        CBModelF.getItems().setAll(list);
    }

    private void refreshTable() {
        TCars.getItems().setAll(data.getList());
    }

    private void setTableData(List<Car> list) {
        TCars.getItems().setAll(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = new CarData();

        initTable();
        refreshTable();
        refreshModels();
    }

    public void ExerciseA() {
        if (CBModelA.getValue() != null && !CBModelA.getValue().isBlank())
            setTableData(data.ExerciseA(CBModelA.getValue()));
        else
            showAlert(Alert.AlertType.WARNING, "Ошибка", null, "Модель не выбрана.");
    }

    public void ExerciseB() {
        if (CBModelB.getValue() != null && !CBModelB.getValue().isBlank() && !TFExp.getText().isBlank() && isNumber(TFExp.getText()))
            setTableData(data.ExerciseB(CBModelB.getValue(), Integer.parseInt(TFExp.getText())));
        else
            showAlert(Alert.AlertType.ERROR, "Неверные данные", null, "Поле либо пустое, либо введенные данные не являются числом.");
    }

    public void ExerciseC() {
        if (!TFPrice.getText().isBlank() && isNumber(TFPrice.getText()) && !TFYear.getText().isBlank() && isYear(TFYear.getText()))
            setTableData(data.ExerciseC(Integer.parseInt(TFYear.getText()), Integer.parseInt(TFPrice.getText())));
        else
            showAlert(Alert.AlertType.ERROR, "Неверные данные", null, "Поле либо пустое, либо введенные данные не являются числом.");
    }

    public void ExerciseD() {
        setTableData(data.ExerciseD());
    }

    public void ExerciseE() {
        showAlert(Alert.AlertType.INFORMATION, "Список моделей", null, String.join("\n", data.ExerciseE()));
    }

    public void ExerciseF() {
        if (CBModelF.getValue() != null && !CBModelF.getValue().isBlank())
            setTableData(data.ExerciseF(CBModelF.getValue()));
        else
            showAlert(Alert.AlertType.WARNING, "Ошибка", null, "Модель не выбрана.");
    }
}

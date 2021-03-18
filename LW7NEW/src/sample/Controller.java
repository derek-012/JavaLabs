package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Controller implements Initializable {
    @FXML ChoiceBox<String> CBModelA, CBModelF;

    @FXML TableView<Car> TCars;
    @FXML TableColumn<Car, String> TCID, TCModel, TCYear, TCPrice, TCRegNum;

    @FXML TextField TFExp, TFPrice;

    CarData data;

    public void setStageAndListeners(Stage stage) {
        stage.setOnCloseRequest(this::exitApplication);
    }

    public void exitApplication(WindowEvent event) {
        if (!data.isEdited())
            return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Сохранение данных");
        alert.setHeaderText(null);
        alert.setContentText("Сохранить имеющиеся данные в файл?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent())
            if (result.get() == ButtonType.OK)
                if (!data.save())
                    showAlert(Alert.AlertType.ERROR, "Ошибка сохранения", null, "Во время сохранения данных произошла ошибка!");
    }

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

    private ContextMenu getContextMenu() {
        ContextMenu menu = new ContextMenu();

        MenuItem itemAdd = new MenuItem("Добавить");
        itemAdd.setOnAction(e -> {
            data.addCar();
            refreshTable();
        });

        MenuItem itemDelete = new MenuItem("Удалить");
        itemDelete.setOnAction(e -> {
            data.deleteCar(TCars.getSelectionModel().getSelectedItem());
            refreshTable();
        });

        menu.getItems().setAll(itemAdd, itemDelete);

        return menu;
    }

    private void initTable() {
        TCID.setCellValueFactory(p -> new SimpleObjectProperty<>(String.valueOf(p.getValue().getId())));

        TCModel.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getModel()));
        TCModel.setCellFactory(TextFieldTableCell.forTableColumn());
        TCModel.setOnEditCommit(event -> {
            event.getRowValue().setModel(event.getNewValue() != null ? event.getNewValue() : event.getOldValue());
            //TCModel.setText(event.getRowValue().getModel());

            data.setEdited(true);
            TCars.refresh();
        });

        TCYear.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getYearString()));
        TCYear.setCellFactory(TextFieldTableCell.forTableColumn());
        TCYear.setOnEditCommit(event -> {
            String year = event.getNewValue() != null && isYear(event.getNewValue()) ? event.getNewValue() : event.getOldValue();

            if (event.getRowValue().setYearString(year))
                data.setEdited(true);
            else
                showAlert(Alert.AlertType.INFORMATION, "Некорректные данные", null, "Введенные данные не являются корректными. Повторите попытку.");

            TCars.refresh();
        });

        TCPrice.setCellValueFactory(p -> new SimpleObjectProperty<>(String.valueOf(p.getValue().getPrice())));
        TCPrice.setCellFactory(TextFieldTableCell.forTableColumn());
        TCPrice.setOnEditCommit(event -> {
            String price = event.getNewValue() != null && isNumber(event.getNewValue()) ? event.getNewValue() : event.getOldValue();
            if ((event.getNewValue() != null && isNumber(event.getNewValue())) && event.getRowValue().setPriceString(price))
                data.setEdited(true);
            else
                showAlert(Alert.AlertType.INFORMATION, "Некорректные данные", null, "Введенные данные не являются корректными. Повторите попытку.");
            TCars.refresh();
        });

        TCRegNum.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getRegNum()));
        TCRegNum.setCellFactory(TextFieldTableCell.forTableColumn());
        TCRegNum.setOnEditCommit(event -> {
            String regnum = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
            event.getRowValue().setRegNum(regnum);
            TCars.refresh();
            data.setEdited(true);
        });


        TCars.setContextMenu(getContextMenu());

        TCars.setEditable(true);
    }

    private void refreshModels() {
        List<String> list = new ArrayList<>(data.getModelList());

        CBModelA.getItems().setAll(list);
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
        final String filename = "cars.ser";

        data = new CarData(filename);

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
        if (!TFExp.getText().isBlank() && isNumber(TFExp.getText()))
            setTableData(data.ExerciseB(Integer.parseInt(TFExp.getText())));
        else
            showAlert(Alert.AlertType.ERROR, "Неверные данные", null, "Поле либо пустое, либо введенные данные не являются числом.");
    }

    public void ExerciseC() {
        if (!TFPrice.getText().isBlank() && isNumber(TFPrice.getText()))
            setTableData(data.ExerciseC(Integer.parseInt(TFPrice.getText())));
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

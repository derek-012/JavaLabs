package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class Controller implements Initializable {
    Stage MainStage;
    @FXML Pane MainPane;

    @FXML Button BAddCar, BDeleteCar;

    @FXML ComboBox<String> CBSortYear, CBSortPrice;
    @FXML Button BCleanSort;

    @FXML ChoiceBox<String> CBFilterModel;
    @FXML TextField TFYear, TFExploitation, TFPrice;
    @FXML Button BCleanFilter;

    @FXML TableView<Car> TableCar;
    @FXML TableColumn<Car, String> TCModel, TCRegNum, TCID, TCYear, TCPrice;


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
                saveData();
    }

    CarData data;


    final List<String> listSort = Arrays.asList("-", "Возрастание", "Убывание");

    public void setStageAndListeners(Stage stage) {
        MainStage = stage;
        MainStage.setOnCloseRequest(this::exitApplication);
    }

    private boolean isNumber(String s) {
        if (s.length() != 0) {
            for (char ch : s.toCharArray()) {
                if (!Character.isDigit(ch))
                    return false;
            }
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final String filename = "data.ser";

        CBSortYear.getItems().addAll(listSort);
        CBSortYear.setValue(listSort.get(0));
        CBSortPrice.getItems().addAll(listSort);
        CBSortPrice.setValue(listSort.get(0));

        CBSortYear.getSelectionModel().selectedItemProperty().addListener((observable, oldV, newV) ->
            CBSortPrice.setDisable(!newV.equals(listSort.get(0)))
        );

        CBSortPrice.getSelectionModel().selectedItemProperty().addListener((observableValue, oldV, newV) ->
            CBSortYear.setDisable(!newV.equals(listSort.get(0)))
        );

        TFYear.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            KeyCode key = event.getCode();
            if (key == KeyCode.V && event.isControlDown()) {
                if (!isNumber(TFYear.getText()))
                    TFYear.undo();
            } else if (!Character.isIdentifierIgnorable(key.getCode()) && (!Character.isDigit(key.getCode()) || (event.isShiftDown() && Character.isDigit(key.getCode()))))
                TFYear.deletePreviousChar();
            TFExploitation.setDisable(TFYear.getLength() > 0);
        });

        TFExploitation.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            KeyCode key = event.getCode();
            if (key == KeyCode.V && event.isControlDown()) {
                if (!isNumber(TFExploitation.getText()))
                    TFExploitation.undo();
            } else if (!Character.isIdentifierIgnorable(key.getCode()) && (!Character.isDigit(key.getCode()) || (event.isShiftDown() && Character.isDigit(key.getCode()))))
                TFExploitation.deletePreviousChar();
            TFYear.setDisable(TFExploitation.getLength() > 0);
        });

        TFPrice.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            KeyCode key = event.getCode();
            if (key == KeyCode.V && event.isControlDown()) {
                if (!isNumber(TFPrice.getText()))
                    TFPrice.undo();
            } else if (!Character.isIdentifierIgnorable(key.getCode()) && (!Character.isDigit(key.getCode()) || (event.isShiftDown() && Character.isDigit(key.getCode()))))
                TFPrice.deletePreviousChar();
        });

        TCID.setCellValueFactory(p -> new SimpleObjectProperty<>(String.valueOf(p.getValue().getId())));
        TCModel.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getModel()));
        TCModel.setCellFactory(TextFieldTableCell.forTableColumn());
        TCModel.setOnEditCommit(event -> {
            event.getRowValue().setModel(event.getNewValue() != null ? event.getNewValue() : event.getOldValue());
            refreshModels();
        });
        TCYear.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getYearString()));
        TCYear.setCellFactory(TextFieldTableCell.forTableColumn());
        TCYear.setOnEditCommit(event -> {
            if () {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Неверные данные");
                alert.setTitle(null);
                alert.setContentText("Введенные данные являются не корректными и не являются числом. Повторите попытку.");
                alert.showAndWait();
                event.getTableView().refresh();
            }
        });
        TCPrice.setCellValueFactory(p -> new SimpleObjectProperty<>(String.valueOf(p.getValue().getPrice())));
        TCPrice.setCellFactory(TextFieldTableCell.forTableColumn());
        TCPrice.setOnEditCommit(event -> {
            if (!event.getRowValue().setPriceString(event.getNewValue() != null && isNumber(event.getNewValue()) ? event.getNewValue() : event.getOldValue())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Неверные данные");
                alert.setTitle(null);
                alert.setContentText("Введенные данные являются не корректными и не являются числом. Повторите попытку.");
                alert.showAndWait();
                event.getTableView().refresh();
            }
        });
        TCRegNum.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getRegNum()));
        TCRegNum.setCellFactory(TextFieldTableCell.forTableColumn());
        TCRegNum.setOnEditCommit(event ->
            event.getRowValue().setRegNum(event.getNewValue() != null ? event.getNewValue() : event.getOldValue())
        );

        data = new CarData(filename);

        refreshTable();
        refreshModels();
    }

    private void refreshTable() {
        TableCar.getItems().setAll(data.getList());
    }

    public static boolean checkNumber(String s) {
        return Pattern.matches("\\d{4}", s);
    }

    private void refreshModels() {
        final String def = "-";
        String[] listModels = data.getModelList();
        if (listModels != null){
            CBFilterModel.getItems().setAll(def);
            CBFilterModel.getItems().addAll(listModels);
            CBFilterModel.setValue(def);
        }
    }

    public void addCar() {
        TableCar.getItems().add(data.addCar());
    }

    public void deleteCar() {
        Car car;
        if ((car = TableCar.getSelectionModel().getSelectedItem()) != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText(null);
            alert.setContentText("Вы хотите удалить запись:\n\n" + car.toString() + "\n\nВы уверены?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                data.deleteCar(car);
                refreshTable();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Не выбрана запись");
            alert.setHeaderText(null);
            alert.setContentText("Вы не выбрали запись. Выберите нужную запись в таблице и повторите попытку.");
            alert.showAndWait();
        }
    }

    public void saveDataAction() {
        saveData();
    }

    private void saveData() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сохранение данных");
        alert.setHeaderText(null);
        if (data.save()) {
            alert.setContentText("Данные успешно сохранены!");
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Ошибка во время сохранения, повторите попытку позже.");
        }
        alert.showAndWait();
    }

    private String getFilterModel() {
        return !CBFilterModel.getValue().equals(CBFilterModel.getItems().get(0)) ? CBFilterModel.getValue() : null;
    }

    private Integer getFilterYear() {
        if (TFYear.getLength() != 0)
            return (checkYear(TFYear.getText())) ? Integer.parseInt(TFYear.getText()) : -1;
        return null;
    }

    private boolean checkYear(String year) {
        return Pattern.matches("(19[5-9]\\d)|(20(([01]\\d)|(2[01])))", year);
    }

    private Integer getFilterExp() {
        if (TFExploitation.getLength() != 0)
            return (isNumber(TFExploitation.getText())) ? Integer.parseInt(TFExploitation.getText()) : -1;
        return null;
    }

    private Integer getFilterPrice() {
        if (TFPrice.getLength() != 0)
            return (!TFPrice.getText().isBlank() && isNumber(TFPrice.getText())) ? Integer.parseInt(TFPrice.getText()) : -1;
        return null;
    }

    private void showFilterAlert(int type) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Некорректные данные");
        alert.setHeaderText(null);
        String s = "";
        switch (type) {
            case 1 -> s = "года выпуска";
            case 2 -> s = "эксплуатации";
            case 3 -> s = "цены";
        }
        alert.setContentText("Проверьте корректность ввода " + s + " автомобиля.");
        alert.showAndWait();
    }

    public void setFilter() {
        String model = getFilterModel();

        Integer year;
        if ((year = getFilterYear()) != null && year == -1) {
            showFilterAlert(1);
            return;
        }

        Integer exp;
        if ((exp = getFilterExp()) != null  && exp == -1) {
            showFilterAlert(2);
            return;
        }

        Integer price;
        if ((price = getFilterPrice()) != null && price == -1) {
            showFilterAlert(3);
            return;
        }

        data.setFilter(model, year, exp, price);
        refreshTable();
    }

    public void clearFilter() {
        CBFilterModel.setValue(CBFilterModel.getItems().get(0));
        TFYear.clear();
        TFYear.setDisable(false);
        TFExploitation.clear();
        TFExploitation.setDisable(false);
        TFPrice.clear();

        data.setFilter(null, null, null, null);

        refreshTable();
    }

    public void sort() {
        setSort(listSort.indexOf(CBSortYear.getValue()), listSort.indexOf(CBSortPrice.getValue()));
        refreshTable();
    }

    public void clearSort() {
        setSort(0,0);
        CBSortYear.setValue(listSort.get(0));
        CBSortPrice.setValue(listSort.get(0));
        refreshTable();
    }

    private void setSort(int sortYear, int sortPrice) {
        int type = sortYear != 0 ? sortYear : (sortPrice != 0 ? sortPrice + 2 : 0);
        data.setSort(type);
    }
}

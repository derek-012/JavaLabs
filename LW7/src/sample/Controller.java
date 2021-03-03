package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class Controller implements Initializable {
    @FXML Pane MainPane;

    @FXML Button BAddCar, BDeleteCar;

    @FXML ComboBox<String> CBSortYear, CBSortPrice;
    @FXML Button BCleanSort;

    @FXML ChoiceBox<String> CBFilterModel;
    @FXML TextField TFYear, TFExploitation, TFPrice;
    @FXML Button BCleanFilter;

    @FXML TableView<Car> TableCar;
    @FXML TableColumn<Car, String> TCModel, TCRegNum, TCID, TCYear, TCPrice;


//    public void exitApplication(WindowEvent event) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Сохранение данных");
//        alert.setHeaderText(null);
//        alert.setContentText("Сохранить имеющиеся данные в файл?");
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.isPresent())
//            if (result.get() == ButtonType.OK)
//                saveData();
//        Platform.exit();
//    }

    CarData data;


    final List<String> listSort = Arrays.asList("-", "Возрастание", "Убывание");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final String filename = "data.ser";

        //MainPane.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::exitApplication);


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
            System.out.println(event.getText());
            TFExploitation.setDisable(TFYear.getLength() > 0);
        });

        TFExploitation.addEventHandler(KeyEvent.KEY_RELEASED, event ->
            TFYear.setDisable(TFExploitation.getLength() > 0)
        );

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
            if (!event.getRowValue().setYearString(event.getNewValue() != null ? event.getNewValue() : event.getOldValue())) {
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
            if (!event.getRowValue().setPriceString(event.getNewValue() != null ? event.getNewValue() : event.getOldValue())) {
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
                TableCar.refresh();
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

    public void setFilter() {
        data.setFilter(
                !CBFilterModel.getValue().equals(CBFilterModel.getItems().get(0)) ? CBFilterModel.getValue() : null,
                !TFYear.getText().isBlank() ? Integer.parseInt(TFYear.getText()) : null,
                !TFExploitation.getText().isBlank() ? Integer.parseInt(TFExploitation.getText()) : null,
                !TFPrice.getText().isBlank() ? Integer.parseInt(TFPrice.getText()) : null
        );
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

package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML Pane MainPane;

    @FXML Button BAddCar, BDeleteCar;

    @FXML ComboBox<String> CBSortYear, CBSortPrice;
    @FXML Button BCleanSort;

    @FXML ChoiceBox<String> CBFilterModel;
    @FXML TextField TFYear, TFExploitation, TFPrice;
    @FXML Button BCleanFilter;

    @FXML TableView<Car> TableCar;
    @FXML TableColumn<Car, Integer> TCID, TCYear, TCPrice;
    @FXML TableColumn<Car, String> TCModel, TCRegNum;


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

    CarDataProcessor data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final String filename = "data.ser";

        //MainPane.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::exitApplication);

        String[] listSort = {"Возрастание", "Убывание"};
        CBSortYear.getItems().addAll(listSort);
        CBSortPrice.getItems().addAll(listSort);

        TCID.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getId()));
        TCModel.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getModel()));
        TCYear.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getYear()));
        TCPrice.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getPrice()));
        TCRegNum.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getRegnum()));

        data = new CarDataProcessor(filename);
        TableCar.getItems().addAll(data.getList());

        resetModels();
    }

    private void resetModels() {
        String[] listModels = data.getModelList();
        if (listModels != null) CBFilterModel.getItems().addAll(listModels);
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
            if (result.get() == ButtonType.OK)
                data.deleteCar(car);
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

    private boolean saveData() {
        data.save();
        return false;
    }
}

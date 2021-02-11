package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

public class Controller {
    File file;
    PrimaryClass pc;

    @FXML TableView<Patient> table;
    @FXML TableColumn<Patient, String> tId;
    @FXML TableColumn<Patient, String> tName;
    @FXML TableColumn<Patient, String> tMedCard;
    @FXML TableColumn<Patient, String> tDiagnosis;
    @FXML TableColumn<Patient, String> tAddress;
    @FXML TableColumn<Patient, String> tPhone;

    @FXML TextField tfDiagnosis;
    @FXML TextField tfMedCardBegin;
    @FXML TextField tfMedCardEnd;
    @FXML TextField tfPhone;

    public void initTable() {
        tId.setCellValueFactory(new PropertyValueFactory<>("id"));

        tName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tName.setCellFactory(TextFieldTableCell.forTableColumn());
        tName.setOnEditCommit(
                t -> (t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setName(t.getNewValue())
        );

        tMedCard.setCellValueFactory(new PropertyValueFactory<>("medCard"));
        tMedCard.setCellFactory(TextFieldTableCell.forTableColumn());
        tMedCard.setOnEditCommit(
                t -> (t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setMedCard(t.getNewValue())
        );

        tDiagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        tDiagnosis.setCellFactory(TextFieldTableCell.forTableColumn());
        tDiagnosis.setOnEditCommit(
                t -> (t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setDiagnosis(t.getNewValue())
        );

        tAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        tAddress.setOnEditCommit(
                t -> (t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setAddress(t.getNewValue())
        );

        /*
        tAddress.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Patient, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Patient, String> t) {
                        ((Patient) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setAddress(t.getNewValue());
                    }
                }
        );
         */

        tPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        tPhone.setOnEditCommit(
                t -> (t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setPhone(t.getNewValue())
        );

        table.setEditable(true);
    }

    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Файл с пациентами", "*.pl"));
        file = fileChooser.showOpenDialog(Window.getWindows().get(0));
        if (file != null) {
            pc = new PrimaryClass(file);
            showData();
        }
    }

    private void showData() {
        initTable();
        table.setItems(FXCollections.observableList(pc.getData()));
    }

    public void save() {
        if (file != null && file.exists())
            saveInFile(this.file);
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Нечего сохранять, или файл");
        }
    }

    public void saveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Файл с пациентами", "*.pl"));
        File file = fileChooser.showSaveDialog(Window.getWindows().get(0));
        if (file != null) {
            if (!file.exists()) {
                try {
                    if (file.createNewFile())
                        saveInFile(file);
                    else
                        showAlert("Проблемы с созданием или перезаписью файла, повторите попытку.");
                } catch (IOException e) {
                    showAlert("Проблемы с созданием или перезаписью файла, повторите попытку.");
                }
            } else {
                saveInFile(file);
            }
        }
    }

    public void saveInFile(File file) {
        if (pc.saveInFile(file) == 0) {
            this.file = file;
        } else {
            showAlert("Проблема с сохранением данных.");
        }
    }

    public void addPatient() {
        showData();
        pc.addPatient();
        table.refresh();
        clearFilters();
    }

    public boolean tryParseInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Некорректные данные");
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void sortByDiagnosis() {
        if (!tfDiagnosis.getText().isEmpty())
            table.setItems(FXCollections.observableList(pc.sortByDiagnosis(tfDiagnosis.getText())));
        else
            showAlert("Введите диагноз для поиска!");
    }

    public void sortByMedCard() {
        int begin, end;
        if (tryParseInt(tfMedCardBegin.getText()) && tryParseInt(tfMedCardEnd.getText())) {
            if ((!tfMedCardBegin.getText().isEmpty() && (begin = Integer.parseInt(tfMedCardBegin.getText())) >= 1000) && (!tfMedCardEnd.getText().isEmpty() && (end = Integer.parseInt(tfMedCardEnd.getText())) <= 9999))
                table.setItems(FXCollections.observableList(pc.sortByMedCard(begin, end)));
            else
                showAlert("Проверьте правильность введенных данных. Пределы поиска: от 1000 и до 9999.");
        } else {
            showAlert("В одном или в нескольких полях находятся недопустимые символы. Проверьте правильность введенных данных.");
        }
    }

    public void sortByPhone() {
        if (!tfPhone.getText().isEmpty() && tfPhone.getText().length() == 1 && Character.isDigit(tfPhone.getText().charAt(0))) {
            table.setItems(FXCollections.observableList(pc.sortByPhone(tfPhone.getText().charAt(0))));
        } else {
            showAlert("В поле должна присутствовать одна цифра на которую начинается номер телефона. Проверьте правильность введенной информации.");
        }
    }

    public void clearFilters() {
        tfDiagnosis.clear();
        tfMedCardBegin.clear();
        tfMedCardEnd.clear();
        tfPhone.clear();
        showData();
    }
}

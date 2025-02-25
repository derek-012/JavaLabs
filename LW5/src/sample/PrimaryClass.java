package sample;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class PrimaryClass {
    ArrayList<Patient> patients;

    PrimaryClass(File file) {
        readData(file);
    }

    public void readData(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            patients = new ArrayList<>();
            while ((s = br.readLine()) != null) {
                patients.add(new Patient(s));
            }
            br.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setContentText("Что-то пошло не так!");
            alert.showAndWait();
        }
    }

    public int saveInFile(File file) {
        if (file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))){
                ListIterator<Patient> listIterator = patients.listIterator();
                for (;listIterator.hasNext();bw.newLine()) {
                    Patient p = listIterator.next();
                    bw.write(p.toString());
                }
                return 0;
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Не удалось открыть файл!");
                alert.showAndWait();
                return -1;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Файл не выбран");
            alert.setContentText("Для сохранения информации сначала необходимо выбрать файл.");
            alert.showAndWait();
            return -1;
        }
    }

    public ArrayList<Patient> getData() {
        return patients;
    }

    public void addPatient() {
        Patient p = new Patient(patients.size() + 1);
        patients.add(p);
    }

    public ArrayList<Patient> sortByDiagnosis(String diagnosis) {
        ArrayList<Patient> sortedList = new ArrayList<>();
        ListIterator<Patient> listIterator = patients.listIterator();
        while (listIterator.hasNext()) {
            Patient p = listIterator.next();
            if (p.checkDiagnosis(diagnosis))
                sortedList.add(p);
        }
        return sortedList;
    }

    public ArrayList<Patient> sortByMedCard(int begin, int end) {
        ArrayList<Patient> sortedList = new ArrayList<>();
        ListIterator<Patient> listIterator = patients.listIterator();
        while (listIterator.hasNext()) {
            Patient p = listIterator.next();
            if (p.checkMedCard(begin, end))
                sortedList.add(p);
        }
        return sortedList;
    }

    public ArrayList<Patient> sortByPhone(char c) {
        ArrayList<Patient> sortedList = new ArrayList<>();
        ListIterator<Patient> listIterator = patients.listIterator();
        while (listIterator.hasNext()) {
            Patient p = listIterator.next();
            if (p.checkPhone(c))
                sortedList.add(p);
        }
        return sortedList;
    }
}

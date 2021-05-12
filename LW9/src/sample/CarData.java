package sample;

import sample.data.Car;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class CarData {
    private final List<Car> basicList;
    CarReader carReader;

    CarData() {
        carReader = new CarReader();
        basicList = carReader.read();
    }

    public List<Car> getList() {
        return basicList;
    }

    public Set<String> getModelList() {
        return carReader.ExerciseE();
    }

    public List<Car> ExerciseA(String model) {
        return carReader.ExerciseA(model);
    }

    public List<Car> ExerciseB(String model, Integer exp) {
        return carReader.ExerciseB(model, exp);
    }

    public List<Car> ExerciseC(Integer year, Integer price) {
        return carReader.ExerciseC(year, price);
    }

    public List<Car> ExerciseD() {
        return carReader.ExerciseD();
    }

    public Set<String> ExerciseE() {
        return carReader.ExerciseE();
    }

    public List<Car> ExerciseF(String model) {
        return carReader.ExerciseF(model);
    }
}

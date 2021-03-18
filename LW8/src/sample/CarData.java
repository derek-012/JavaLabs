package sample;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class CarData {
    private final List<Car> basicList;

    private boolean edited = false;

    private final CarReader carReader;

    CarData(String filename) {
        carReader = new CarReader(filename);
        basicList = carReader.readFromFile();
    }

    public List<Car> getList() {
        return basicList;
    }

    public boolean save() {
        return carReader.saveToFile(basicList);
    }

    public List<String> getModelList() {
        return basicList.stream()
                .map(Car::getModel)
                .distinct()
                .collect(Collectors.toList());
    }

    public Car addCar() {
        int size = basicList.size();
        Car car = new Car((size > 0) ? basicList.get(size - 1).getId() + 1 : 1);
        basicList.add(car);
        setEdited(true);
        return car;
    }

    public void deleteCar(Car car) {
        basicList.remove(car);
        setEdited(true);
    }

    public void setEdited(boolean status) {
        edited = status;
    }

    public boolean isEdited() {
        return edited;
    }

    public List<Car> ExerciseA(String model) {
        return basicList.stream()
                .filter(car -> car.getModel().equals(model))
                .sorted(Comparator.comparingInt(Car::getYearInt))
                .collect(Collectors.toList());
    }

    public List<Car> ExerciseB(Integer exp) {
        int year = LocalDate.now().getYear();

        return basicList.stream().filter(car -> year - car.getYearInt() > exp).collect(Collectors.toList());
    }

    public List<Car> ExerciseC(Integer price) {
        return basicList.stream().filter(car -> car.getPrice() > price).collect(Collectors.toList());
    }

    public List<Car> ExerciseD() {
        return basicList.stream()
                .sorted((c1, c2) -> {
                    int value = c2.getPrice() - c1.getPrice();
                    return (value == 0) ? c1.getYearInt() - c2.getYearInt() : value;
                })
                .collect(Collectors.toList());
    }

    public List<String> ExerciseE() {
        return basicList.stream()
                .map(Car::getModel)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Car> ExerciseF(String model) {
        Map<String, List<Car>> cars = basicList.stream().collect(Collectors.groupingBy(Car::getModel));

        return cars.get(model);
    }
}

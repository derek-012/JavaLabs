package sample;

import java.time.LocalDate;
import java.util.*;


public class CarData {
    private final List<Car> basicList;

    private boolean edited = false;

    private final CarReader carReader;

    private Comparator<Car> comparator = null;

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
        Set<String> models = new HashSet<>();
        for (Car car : basicList) models.add(car.getModel());
        return List.copyOf(models);
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
        List<Car> list = new ArrayList<>();
        Iterator<Car> iterator = basicList.iterator();

        Car car;
        while (iterator.hasNext()) {
            car = iterator.next();
            if (car.getModel().equals(model))
                list.add(car);
        }

        if (list.size() > 0) {
            list.sort(Comparator.comparingInt(Car::getYearInt));
        }

        return list;
    }

    public List<Car> ExerciseB(Integer exp) {
        List<Car> list = new ArrayList<>();
        Iterator<Car> iterator = basicList.iterator();

        Integer year = LocalDate.now().getYear();

        Car car;
        while (iterator.hasNext()) {
            car = iterator.next();
            if (year - car.getYearInt() > exp)
                list.add(car);
        }

        return list;
    }

    public List<Car> ExerciseC(Integer price) {
        List<Car> list = new ArrayList<>();
        Iterator<Car> iterator = basicList.iterator();

        Car car;
        while (iterator.hasNext()) {
            car = iterator.next();
            if (car.getPrice() > price)
                list.add(car);
        }

        return list;
    }

    public List<Car> ExerciseD() {
        List<Car> list = new ArrayList<>(List.copyOf(basicList));
        Comparator<Car> comparator = (c1, c2) -> {
            int value = c2.getPrice() - c1.getPrice();
            return (value == 0) ? c1.getYearInt() - c2.getYearInt() : value;
        };

        list.sort(comparator);

        return list;
    }

    public List<String> ExerciseE() {
        Set<String> models = new HashSet<>();
        for (Car car : basicList) models.add(car.getModel());
        return List.copyOf(models);
    }

    public List<Car> ExerciseF(String model) {
        Map<String, List<Car>> cars = new HashMap<>();

        Iterator<Car> iterator = basicList.iterator();
        Car car;
        while (iterator.hasNext()) {
            car = iterator.next();

            if (!cars.containsKey(car.getModel()))
                cars.put(car.getModel(), new ArrayList<>());

            cars.get(car.getModel()).add(car);
        }

        return cars.get(model);
    }
}

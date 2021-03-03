package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarData {

    private final String filename;

    private final List<Car> basicList;
    private List<Car> currentList;

    private final CarFilter carFilter = new CarFilter();

    private Comparator<Car> comparator = null;

    CarData(String filename) {
        this.filename = filename;
        currentList = basicList = readFromFile(filename);
    }

    public List<Car> getList() {
        filter();
        sort();
        return currentList;
    }

    private void filter() {
        currentList = carFilter.filter(basicList);
    }

    private void sort() {
        if (comparator != null)
        currentList.sort(comparator);
    }

    public void setFilter(String model, Integer year, Integer exp, Integer price) {
        carFilter.setFilter(model, year, exp, price);
    }

    public void setSort(int type) {
        switch (type) {
            case 0 -> comparator = CarComparator.DEFAULT;
            case 1 -> comparator = CarComparator.SORT_BY_YEAR_ASC;
            case 2 -> comparator = CarComparator.SORT_BY_YEAR_DESC;
            case 3 -> comparator = CarComparator.SORT_BY_PRICE_ASC;
            case 4 -> comparator = CarComparator.SORT_BY_PRICE_DESC;
        }
    }

    private List<Car> readFromFile(String filename) {
        List<Car> list = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    list.add((Car) in.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
            return list;
        } catch (IOException | ClassNotFoundException e) {
            if (e.getClass() == IOException.class) {
                return new ArrayList<>();
            }
            return list;
        }
    }

    public boolean save() {
        return saveToFile(filename);
    }

    private boolean saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Car car : basicList) {
                out.writeObject(car);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String[] getModelList() {
        return (!basicList.isEmpty()) ? basicList.stream().map(Car::getModel).distinct().toArray(String[]::new) : null;
    }

    public Car addCar() {
        int size = basicList.size();
        Car car = new Car((size > 0) ? basicList.get(size - 1).getId() + 1 : 1);
        basicList.add(car);
        return car;
    }

    public void deleteCar(Car car) {
        basicList.remove(car);
    }
}

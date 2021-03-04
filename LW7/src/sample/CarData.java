package sample;

import java.util.*;

public class CarData {

    //private final String filename;

    private final List<Car> basicList;
    private List<Car> currentList;

    private boolean edited = false;

    private final CarFilterList carFilterList = new CarFilterList();
    private final CarReader carReader;

    private Comparator<Car> comparator = null;

    CarData(String filename) {
        //this.filename = filename;
        carReader = new CarReader(filename);
        currentList = basicList = carReader.readFromFile();
    }

    public List<Car> getList() {
        filter();
        sort();
        return currentList;
    }

    private void filter() {
        currentList = carFilterList.filter(basicList);
    }

    private void sort() {
        if (comparator != null)
        currentList.sort(comparator);
    }

    public void setFilter(String model, Integer year, Integer exp, Integer price) {
        carFilterList.setFilter(model, year, exp, price);
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

    public boolean save() {
        return carReader.saveToFile(basicList);
    }

    public String[] getModelList() {
        Map<Integer, String> models = new HashMap<>();
        Iterator<Car> iterator = basicList.iterator();
        Car car;
        while (iterator.hasNext()) {
            car = iterator.next();
            if (!models.containsValue(car.getModel()))
                models.put(models.size() + 1, car.getModel());
        }
        return models.values().toArray(String[]::new);
        //return (!basicList.isEmpty()) ? basicList.stream().map(Car::getModel).distinct().toArray(String[]::new) : null;
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
}

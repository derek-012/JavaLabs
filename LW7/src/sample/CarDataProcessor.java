package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarDataProcessor {
    private String filename;

    public List<Car> list;

    public List<Car> getList() {
        return list;
    }

    public void setList(List<Car> list) {
        this.list = list;
    }

    CarDataProcessor(String filename) {
        this.filename = filename;
        this.setList(getCarList(filename));
    }

    private List<Car> getCarList(String filename) {
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
            for (Car car : list) {
                out.writeObject(car);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String[] getModelList() {
        return (!list.isEmpty()) ? list.stream().map(Car::getModel).distinct().toArray(String[]::new) : null;
    }

    public Car addCar() {
        int size = list.size();
        Car car = new Car((size > 0) ? list.get(size - 1).getId() + 1 : 1);
        list.add(car);
        return car;
    }

    public void deleteCar(Car car) {
        list.remove(car);
    }

    public List<Car> getFilteredList() {
        
    }
}

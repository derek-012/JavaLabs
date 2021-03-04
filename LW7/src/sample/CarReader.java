package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarReader {
    final String filename;

    public CarReader(String filename) {
        this.filename = filename;
    }

    public List<Car> readFromFile() {
        List<Car> list = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.filename))) {
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

    public boolean saveToFile(List<Car> list) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.filename))) {
            for (Car car : list) {
                out.writeObject(car);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}

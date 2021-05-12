package sample;

import sample.data.Car;
import java.util.List;
import java.util.Set;

public class CarReader {
    DBReader reader;


    public CarReader() {
        reader = new DBReader();
    }

    public List<Car> read() {
        return reader.getList();
    }

    public List<Car> ExerciseA(String model) {
        return reader.getExerciseA(model);
    }

    public List<Car> ExerciseB(String model, int exp) {
        return reader.getExerciseB(model, exp);
    }

    public List<Car> ExerciseC(int year, int price) {
        return reader.getExerciseC(year, price);
    }

    public List<Car> ExerciseD() {
        return reader.getExerciseD();
    }

    public Set<String> ExerciseE() {
        return reader.getExerciseE();
    }

    public List<Car> ExerciseF(String model) {
        return reader.getExerciseF(model);
    }
}

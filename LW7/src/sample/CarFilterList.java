package sample;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarFilterList extends CarFilter{

    @Override
    public List<Car> filter(List<Car> list) {
        if (this.isEmpty())
            return list;

        List<Car> cars = new ArrayList<>();
        Iterator<Car> iterator = list.iterator();

        int yearNow = LocalDate.now().getYear();

        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (
                    (this.getPrice() == null || (car.getPrice() > this.getPrice()))
                    && (this.getYear() == null || (car.getYearInt() == this.getYear()))
                    && (this.getExp() == null || (yearNow - car.getYearInt() > this.getExp()))
                    && (this.getModel() == null || (car.getModel().equals(this.getModel())))
            )
                cars.add(car);
        }
        return cars;
    }
}

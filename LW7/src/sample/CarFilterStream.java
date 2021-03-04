package sample;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarFilterStream extends CarFilter {
    @Override
    public List<Car> filter(List<Car> list) {
        if (!this.isEmpty()) {
            Stream<Car> stream = list.stream();
            if (getModel() != null)
                stream = stream.filter(car -> car.getModel().equals(this.getModel()));
            if (getYear() != null && getExp() == null)
                stream = stream.filter(car -> car.getYearInt() == getYear());
            else if (getYear() == null && getExp() != null) {
                int currentYear = LocalDate.now().getYear();
                stream = stream.filter(car -> (currentYear - car.getYearInt()) > getExp());
            }
            if (getPrice() != null)
                stream = stream.filter(car -> car.getPrice() > getPrice());
            return stream.collect(Collectors.toList());
        } else
            return list;
    }
}

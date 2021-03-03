package sample;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarFilter {
    private String model;
    private Integer year;
    private Integer exp;
    private Integer price;

    CarFilter() {
        model = null;
        year = null;
        exp = null;
        price = null;
    }

    public void setFilter(String model, Integer year, Integer exp, Integer price) {
        this.model = model;
        this.year = year;
        this.exp = exp;
        this.price = price;
    }

    public boolean isEmpty() {
        return model == null && year == null && exp == null && price == null;
    }

    public List<Car> filter(List<Car> list) {
        if (!this.isEmpty()) {
            Stream<Car> stream = list.stream();
            if (model != null)
                stream = stream.filter(car -> car.getModel().equals(this.model));
            if (year != null && exp == null)
                stream = stream.filter(car -> car.getYearInt() == year);
            else if (year == null && exp != null) {
                int currentYear = LocalDate.now().getYear();
                stream = stream.filter(car -> (currentYear - car.getYearInt()) > exp);
            }
            if (price != null)
                stream = stream.filter(car -> car.getPrice() > price);
            return stream.collect(Collectors.toList());
        } else
            return list;
    }
}

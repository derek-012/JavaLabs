package sample;

import java.util.List;

public abstract class CarFilter {
    private String model;
    private Integer year;
    private Integer exp;
    private Integer price;

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getExp() {
        return exp;
    }

    public Integer getPrice() {
        return price;
    }

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

    public abstract List<Car> filter(List<Car> list);
}

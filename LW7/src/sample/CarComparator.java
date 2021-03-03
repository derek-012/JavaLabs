package sample;

import java.util.Comparator;

public class CarComparator {
    public static final Comparator<Car> DEFAULT = Comparator.comparingInt(Car::getId);

    public static final Comparator<Car> SORT_BY_YEAR_ASC = Comparator.comparingInt(Car::getYearInt);

    public static final Comparator<Car> SORT_BY_YEAR_DESC = (o1, o2) -> o2.getYearInt() - o1.getYearInt();

    public static final Comparator<Car> SORT_BY_PRICE_ASC = Comparator.comparingInt(Car::getPrice);

    public static final Comparator<Car> SORT_BY_PRICE_DESC = (o1, o2) -> o2.getPrice() - o1.getPrice();
}

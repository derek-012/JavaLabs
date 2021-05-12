package sample.data;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Car.findAllCars", query = "SELECT c FROM Car c"),
        @NamedQuery(name = "Car.ExerciseA", query = "SELECT c FROM Car c WHERE c.Model = :model ORDER BY c.Year"),
        @NamedQuery(name = "Car.ExerciseB", query = "SELECT c FROM Car c WHERE c.Model = :model and (2021 - c.Year) > :exp"),
        @NamedQuery(name = "Car.ExerciseC", query = "SELECT c FROM Car c WHERE c.Year = :year and c.Price > :price"),
        @NamedQuery(name = "Car.ExerciseD", query = "SELECT c FROM Car c ORDER BY c.Price DESC, c.Year"),
        @NamedQuery(name = "Car.ExerciseF", query = "SELECT c FROM Car c WHERE c.Model = :model")
})
public class Car {
    @GeneratedValue
    @Id
    private int ID;
    @Basic
    private String Model;
    @Basic
    private int Year;
    @Basic
    private int Price;
    @Basic
    private String RegNum;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getYearString() {
        return String.valueOf(Year);
    }

    public int getYearInt() {
        return Year;
    }

    public void setYearInt(int year) {
        this.Year = year;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getRegNum() {
        return RegNum;
    }

    public void setRegNum(String regNum) {
        RegNum = regNum;
    }
}

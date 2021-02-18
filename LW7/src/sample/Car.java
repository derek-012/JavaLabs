package sample;

import java.io.Serializable;

public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String model;
    private int year;
    private int price;
    private String regnum;

    Car() {}
    Car(int id) {
        setId(id);
        setModel("None");
        setYear(0);
        setPrice(0);
        setRegnum("None");
    }
    Car(int id, String model, int year, int price, String regnum) {
        setId(id);
        setModel(model);
        setYear(year);
        setPrice(price);
        setRegnum(regnum);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRegnum() {
        return regnum;
    }

    public void setRegnum(String regnum) {
        this.regnum = regnum;
    }
}

package sample;

import java.io.Serializable;

public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String model;
    private int year;
    private int price;
    private String regnum;

    Car(int id) {
        setId(id);
        setModel("None");
        setYearInt(0);
        setPrice(0);
        setRegNum("None");
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

    public String getYearString() {
        return String.valueOf(year);
    }

    public boolean setYearString(String year) {
        int yearInt;
        try {
            yearInt = Integer.parseInt(year);
            this.year = yearInt;
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public int getYearInt() {
        return year;
    }

    public void setYearInt(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean setPriceString(String price) {
        try {
            this.setPrice(Integer.parseInt(price));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getRegNum() {
        return regnum;
    }

    public void setRegNum(String regnum) {
        this.regnum = regnum;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %d, %d, %s", getId(), getModel(), getYearInt(), getPrice(), getRegNum());
    }
}

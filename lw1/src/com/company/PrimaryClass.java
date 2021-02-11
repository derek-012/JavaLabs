package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PrimaryClass {
    private double x = 1.426, y = -1.220, z = 3.5;

    double calculateA(double x, double y) {
        return (2 * Math.cos(x - Math.PI / 6) ) / (1.0 / 2 + (Math.sin(y) * Math.sin(y)));
    }

    double calculateB(double z) {

        return 1 + (z*z)/(3 + Math.pow(z, 2) / 5);
    }

    public void getResult() {
        System.out.printf("Input:\n\tx = %f\n\ty = %f\n\tz = %f\n\nResults:\n\ta = %f\n\tb = %f\n\n",getX(), getY(), getZ(), calculateA(getX(),getY()), calculateB(getZ()));
    }

    public double getX() { return x; }
    public void setX(double newX) { x = newX; }

    public double getY() { return y; }
    public void setY(double newY) {
        y = newY;
    }

    public double getZ() { return z; }
    public void setZ(double newZ) {
        z = newZ;
    }

    public void getDate() {
        LocalDate date = LocalDate.now();
        System.out.println("Today is " + date.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
    }

    public void setInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter numbers:\n\tx = ");
        setX(scanner.nextDouble());
        System.out.print("\ty = ");
        setY(scanner.nextDouble());
        System.out.print("\tz = ");
        setZ(scanner.nextDouble());
        System.out.println("Numbers changed!");
    }

    public void control() {
        boolean exit = false;
        while (!exit) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Menu:\n\t1 - Get data\n\t2 - Set new input\n\t3 - Exit\n\nYour select: ");
            byte select = scanner.nextByte();
            System.out.println();
            switch (select) {
                case 1 -> {
                    getResult();
                    getDate();
                }
                case 2 -> setInput();
                case 3 -> exit = true;
                default -> System.out.println("Wrong select. Try again.");
            }
        }
    }

    public void main() {
        control();
    }
}
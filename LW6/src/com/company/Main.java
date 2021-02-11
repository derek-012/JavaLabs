package com.company;

public class Main {

    private final String FILEPATH = "./src/resources/lab52.txt";

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        Exercise51();
        Exercise52();
    }

    public void Exercise51() {
        ArrayReader ar = new ArrayReader();
        Processor proc = new Processor();
        double result = proc.calc(ar.readOneDimArray(FILEPATH));
        System.out.println("Задание 5.1: Найти наибольший элемент массива с нечетным номером.\nРезультат: " + result + "\n");
    }

    public void Exercise52() {
        ArrayReader ar = new ArrayReader();
        Processor proc = new Processor();
        double result = proc.calc(ar.readTwoDimArray(FILEPATH));
        System.out.println("Задание 5.2: Найти значение наибольшего отрицательного элемента заштрихованной области.\nРезультат: " + result + "\n");
    }
}

package com.company;

public class Main {

    public static void main(String[] args) {
        TextEdit te = new TextEdit();
        System.out.print("Введите строку: ");
        te.insertText();
        System.out.println("\nИсходная строка: " + te.getStr());
        System.out.println("Результат: " + te.getNewStr());
    }
}

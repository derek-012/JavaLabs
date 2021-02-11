package com.company;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    ArrayList<Patient> patients;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    void run() {
        initPatientsList();
        exerciseA();
        exerciseB();
        exerciseC();
    }

    void initPatientsList() {
        patients = new ArrayList<>();
        patients.add(new Patient(1, "Лэндер", "Ермакович", "Арсеньев",  "171097, г. Ольоватка, ул. Анатолия Живова, дом 55, квартира 389"));
        patients.add(new Patient(2, "Туйпи", "Станиславовна", "Попова",  "655234, г. Коврово, ул. Балтийская, дом 14, квартира 978"));
        patients.add(new Patient(3, "Мирта", "Матвеевна", "Ермилова",  "500984, г. Изобильное, ул. Войковский 3-й проезд, дом 54, квартира 69"));
        patients.add(new Patient(4, "Неклюда", "Львовна", "Мамедова",  "624965, г. Североморск, ул. Гончарная, дом 71, квартира 160"));
        patients.add(new Patient(5, "Иварс", "Валерьевич", "Богачев",  "391709, г. Уварово, ул. Коммуны, дом 121, квартира 199"));
        patients.add(new Patient(6, "Айбану", "Виталиевна", "Лебедева",  "403503, г. Мураши, ул. Чымская 1-я, дом 174, квартира 6"));
        patients.add(new Patient(7, "Альзана", "Борисовна", "Соколович",  "462416, г. Кармаскалы, ул. 2-я Шоссейная, дом 59, квартира 419"));
        patients.add(new Patient(8, "Неонилия", "Дмитриевна", "Волощук",  "156536, г. Маломожайское, ул. Васильевский Спуск пл, дом 80, квартира 247"));
        patients.add(new Patient(9, "Яромила", "Кирилловна", "Лютова",  "399072, г. Волоколамск, ул. 5-й пр-кт, дом 49, квартира 874"));
        patients.add(new Patient(10, "Арммелс", "Макарович", "Соловьев",  "442502, г. Талдом, ул. Воробьевское ш, дом 26, квартира 36"));
        patients.add(new Patient(11, "Овив", "Ильич", "Ермилов",  "152935, г. Татарск, ул. Черкасский 2-й пер, дом 18, квартира 154"));
        patients.add(new Patient(12, "Крайслава", "Вячеславовна", "Романова",  "456796, г. Мосальск, ул. Фрунзенская 1-я, дом 157, квартира 982"));
        patients.add(new Patient(13, "Амс", "Ярославовна", "Затулина",  "610960, г. Намцы, ул. Минаевский проезд, дом 115, квартира 939"));
        patients.add(new Patient(14, "Фадул", "Георгиевич", "Арсеньев",  "368436, г. Курсавка, ул. 26-ти Бакинских Комиссаров, дом 20, квартира 507"));
        patients.add(new Patient(15, "Фэридэ", "Даниловна", "Сысолятина",  "182295, г. Анучино, ул. Пуговишников пер, дом 184, квартира 823"));
    }

    void exerciseA() {
        System.out.println("\nА) Поиск по диагнозу");
        System.out.print("Укажите диагноз для поиска: ");
        Scanner scanner = new Scanner(System.in);
        String diagnosis = scanner.nextLine();
        System.out.println("Пациенты, подходящие по критерию:\n");
        PatientFilter.showPatientsInConsole(PatientFilter.getForDiagnosis(patients, diagnosis));
    }



    void exerciseB() {
        System.out.println("\nБ) Поиск по номеру медицинской карты");
        System.out.print("Укажите начало интервала: ");
        Scanner scanner = new Scanner(System.in);
        int begin = scanner.nextInt();
        System.out.print("Укажите конец интервала: ");
        int end = scanner.nextInt();
        System.out.println("Пациенты, подходящие по критерию:\n");
        PatientFilter.showPatientsInConsole(PatientFilter.getForMedCard(patients, begin, end));
    }

    void exerciseC() {
        System.out.println("\nВ) Поиск по номеру медицинской карты");
        System.out.print("Укажите первую цифру номера телефона: ");
        Scanner scanner = new Scanner(System.in);
        char c = scanner.next().charAt(0);
        System.out.println("Пациенты, подходящие по критерию:\n");
        PatientFilter.showPatientsInConsole(PatientFilter.getForPhone(patients, c));
    }
}

package com.company;

public class Patient {
    int id;
    String firstName;   // Имя
    String middleName;  // Отчество
    String lastName;    // Фамилия
    String adress;
    String phone;
    int medCard;
    String diagnosis;

    Patient(int id, String firstName, String middleName, String lastName, String adress) {
        String[] diagnoses = {"Отравление", "Коронавирус", "Шиза", "Астения"};
        setId(id);
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setAdress(adress);
        setPhone("" + (int)(100000000 + Math.random() * 899999999));
        setMedCard((int)(1000 + Math.random() * 8999));
        setDiagnosis(diagnoses[(int)(Math.random() * 4)]);
    }

    Patient(int id, String firstName, String middleName, String lastName, String adress, String phone, int medCard, String diagnosis) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.adress = adress;
        this.phone = phone;
        this.medCard = medCard;
        this.diagnosis = diagnosis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMedCard() {
        return medCard;
    }

    public void setMedCard(int medCard) {
        this.medCard = medCard;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public boolean checkDiagnosis(String diagnosis) {
        return diagnosis.equals(getDiagnosis());
    }

    public boolean checkMedCard(int begin, int end) {
        return (getMedCard() >= begin && getMedCard() <= end);
    }

    public boolean checkPhone(char number) {
        return getPhone().charAt(0) == number;
    }

    @Override
    public String toString() {
        return "" + getId() + ": " + getLastName() + ' ' + getFirstName() + ' ' + getMiddleName() + '\n' +
                "\tАдрес: " + getAdress() + '\n' +
                "\tТелефон: " + getPhone() + '\n' +
                "\tНомер медицинской карты: " + getMedCard() + '\n' +
                "\tДиагноз: " + getDiagnosis() + '\n';
    }
}

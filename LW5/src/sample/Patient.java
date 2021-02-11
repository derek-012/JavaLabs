package sample;

public class Patient extends Human {
    int id;
    String address;
    String phone;
    String medCard;
    String diagnosis;

    Patient(int id, String firstName, String middleName, String lastName, String address) {
        String[] diagnoses = {"Отравление", "Коронавирус", "Шиза", "Астения"};
        setId(id);
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setAddress(address);
        setPhone("" + (int)(100000000 + Math.random() * 899999999));
        setMedCard(String.valueOf(1000 + Math.random() * 8999));
        setDiagnosis(diagnoses[(int)(Math.random() * 4)]);
    }

    Patient(int id, String firstName, String middleName, String lastName, String address, String phone, int medCard, String diagnosis) {
        this.id = id;
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        this.address = address;
        this.phone = phone;
        this.medCard = String.valueOf(medCard);
        this.diagnosis = diagnosis;
    }

    Patient(String s) {
        String[] data = s.split(", ");
        setId(Integer.parseInt(data[0]));
        setName(data[1]);
        setMedCard(data[2]);
        setDiagnosis(data[3]);
        setAddress(data[4]);
        setPhone(data[5]);
    }

    Patient(int id) {
        this.id = id;
        setFirstName("");
        setMiddleName("");
        setLastName("");
        setMedCard("0000");
        setDiagnosis("Неизвестно");
        setAddress("Отсутствует");
        setPhone("000000000");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMedCard() {
        return medCard;
    }

    public void setMedCard(String medCard) {
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
        int medCard = Integer.parseInt(getMedCard());
        return (medCard >= begin && medCard <= end);
    }

    public boolean checkPhone(char number) {
        return getPhone().charAt(0) == number;
    }

//    public boolean isNotEmpty() {
//        return ((getName().length() > 8) && !getMedCard().isEmpty() && !getDiagnosis().isEmpty() && !getAddress().isEmpty() && !getPhone().isEmpty());
//    }

    @Override
    public String toString() {
        return String.join(", ", String.valueOf(getId()), getName(), String.valueOf(getMedCard()), getDiagnosis(), getAddress(), getPhone());
    }
}

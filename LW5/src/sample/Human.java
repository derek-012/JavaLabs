package sample;

public class Human {
    private String firstName;   // Имя
    private String middleName;  // Отчество
    private String lastName;    // Фамилия

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

    public String getName() {
        return String.join(" ", lastName, firstName, middleName);
    }

    public int setName(String name) {
        String[] buff = name.split(" ");
        if (buff.length == 3) {
            lastName = buff[0];
            firstName = buff[1];
            middleName = buff[2];
            return 0;
        }
        return -1;
    }
}

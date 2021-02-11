package com.company;

import java.util.ArrayList;
import java.util.ListIterator;

public class PatientFilter {
    public static ArrayList<Patient> getForDiagnosis(ArrayList<Patient> patients, String diagnosis) {
        ArrayList<Patient> newPatients = new ArrayList<Patient>();
        ListIterator<Patient> listIterator = patients.listIterator();
        while(listIterator.hasNext()) {
            Patient p = listIterator.next();
            if (p.checkDiagnosis(diagnosis)) {
                newPatients.add(p);
            }
        }
        return newPatients;
    }

    public static ArrayList<Patient> getForMedCard(ArrayList<Patient> patients, int begin, int end) {
        ArrayList<Patient> newPatients = new ArrayList<Patient>();
        ListIterator<Patient> listIterator = patients.listIterator();
        while(listIterator.hasNext()) {
            Patient p = listIterator.next();
            if (p.checkMedCard(begin, end)) {
                newPatients.add(p);
            }
        }
        return newPatients;
    }

    public static ArrayList<Patient> getForPhone(ArrayList<Patient> patients, char number) {
        ArrayList<Patient> newPatients = new ArrayList<Patient>();
        ListIterator<Patient> listIterator = patients.listIterator();
        while(listIterator.hasNext()) {
            Patient p = listIterator.next();
            if (p.checkPhone(number)) {
                newPatients.add(p);
            }
        }
        return newPatients;
    }

    public static void showPatientsInConsole(ArrayList<Patient> patients) {
        ListIterator<Patient> listIterator = patients.listIterator();
        while (listIterator.hasNext()) {
            Patient p = listIterator.next();
            System.out.print(p.toString());
        }
    }
}

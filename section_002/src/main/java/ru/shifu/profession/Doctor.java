package ru.shifu.profession;

public class Doctor extends Profession {

    public Doctor(String name, String profession) {
        super(name, profession);
    }

    Diagnosis heal(Patient patient) {
        Diagnosis diagnosis = new Diagnosis("name");
        return diagnosis;
    }
}

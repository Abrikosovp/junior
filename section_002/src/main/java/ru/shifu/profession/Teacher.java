package ru.shifu.profession;

public class Teacher extends Profession {
    public Teacher(String name, String profession) {
        super(name, profession);
    }

    Subject learn(Student student) {
        Subject subject = new Subject("name");
        return subject;
    }
}

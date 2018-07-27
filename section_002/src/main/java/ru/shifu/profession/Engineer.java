package ru.shifu.profession;

public class Engineer extends Profession {
    public Engineer(String name, String profession) {
        super(name, profession);
    }

    Project bild(House house) {
        Project project = new Project("name");
        return project;
    }
}

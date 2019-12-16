package edu.upb.pumatiti.models.repository.firebase;

public class Grade {
    private String name;

    private int value;

    public Grade(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}

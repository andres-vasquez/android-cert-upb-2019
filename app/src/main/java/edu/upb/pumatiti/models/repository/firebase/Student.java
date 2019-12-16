package edu.upb.pumatiti.models.repository.firebase;

import java.util.List;

public class Student {

    private String name;
    private int age;
    private Computer computer;
    private List<Grade> grades;

    public Student(String name, int age, Computer computer, List<Grade> grades) {
        this.name = name;
        this.age = age;
        this.computer = computer;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}

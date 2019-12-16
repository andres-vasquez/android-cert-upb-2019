package edu.upb.pumatiti.models.repository.firebase;

public class Computer {

    private String brand;

    private int ram;

    public Computer(String brand, int ram) {
        this.brand = brand;
        this.ram = ram;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }
}

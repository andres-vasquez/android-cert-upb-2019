package edu.upb.pumatiti;

public class Circulo extends Shape implements ShapeImpl {

    private double radio;

    @Override
    public double getArea() {
        return 3.1416 * Math.pow(radio, 2);
    }
}

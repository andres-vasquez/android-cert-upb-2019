package edu.upb.pumatiti;

public class Triangulo extends Shape implements ShapeImpl {

    private double base;
    private double altura;

    @Override
    public double getArea() {
        return (base * altura) / 2;
    }
}

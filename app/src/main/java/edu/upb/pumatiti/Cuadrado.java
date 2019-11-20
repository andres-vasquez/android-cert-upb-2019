package edu.upb.pumatiti;

public class Cuadrado extends Shape implements ShapeImpl {

    private double lado;

    @Override
    public double getArea() {
        return Math.pow(lado, 2);
    }
}

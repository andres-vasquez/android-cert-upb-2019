package edu.upb.pumatiti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import edu.upb.pumatiti.repository.MockRepository;
import edu.upb.pumatiti.repository.Repository;
import edu.upb.pumatiti.repository.RepositoryImpl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RepositoryImpl repository = new MockRepository();
        List<String> rutas = repository.getRutas();
        Log.i("Esta es una prueba","SI");

        Cuadrado cuadrado = new Cuadrado();
        Triangulo triangulo = new Triangulo();
        Circulo circulo = new Circulo();

        this.draw(cuadrado);
        this.getArea(cuadrado);

        this.draw(triangulo);
        this.getArea(triangulo);

        this.draw(circulo);
        this.getArea(circulo);
    }

    private void draw(Shape shape) {
        String color = shape.getColor();
        String borderColor = shape.getBorderColor();
    }

    private void getArea(ShapeImpl shape) {
        shape.getArea();
    }
}

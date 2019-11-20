package edu.upb.pumatiti.repository;

import java.util.ArrayList;
import java.util.List;

public class MockRepository implements RepositoryImpl {
    @Override
    public List<String> getRutas() {
        List<String> rutas = new ArrayList<>();
        rutas.add("Ruta1");
        rutas.add("Ruta2");
        return rutas;
    }
}

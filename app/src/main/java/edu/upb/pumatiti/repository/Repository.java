package edu.upb.pumatiti.repository;

import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryImpl {
    @Override
    public List<String> getRutas() {
        List<String> rutas = new ArrayList<>();
        rutas.add("Chasquipampa");
        rutas.add("Irpavi");
        return rutas;
    }
}

package edu.upb.pumatiti.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.Route;

public class MockRepository implements RepositoryImpl {
    private static MockRepository instance;

    public static MockRepository getInstance() {
        if (instance == null) {
            instance = new MockRepository();
        }
        return instance;
    }


    @Override
    public LiveData<Base> login(String email, String password) {
        return null;
    }

    @Override
    public LiveData<Base> getRoutes() {
        String json = "[{\"uuid\":\"route-01\",\"name\":\"Chasquipampa\",\"stopList\":[{\"uuid\":\"stop-01\",\"name\":\"Adesu - Campo Ferial\",\"address\":\"Av. Costanera\",\"lat\":-16.531043,\"lng\":-68.100139,\"seat\":true,\"wheelchair\":true,\"status\":true},{\"uuid\":\"stop-02\",\"name\":\"M. 16 de Julio\",\"address\":\"Obrajes, Av. Hector Ormachea entre 15 y 16\",\"lat\":-16.529459,\"lng\":-68.103154,\"seat\":true,\"wheelchair\":true,\"status\":true},{\"uuid\":\"stop-03\",\"name\":\"Plza. de la Loba\",\"address\":\"Obrajes, Av. Hector Ormachea entre 12 y 13\",\"lat\":-16.528091,\"lng\":-68.106019,\"seat\":true,\"wheelchair\":true,\"status\":true},{\"uuid\":\"stop-04\",\"name\":\"Calle 4 Obrajes\",\"address\":\"Obrajes, Av. Hernando Siles calle 4\",\"lat\":-16.52452,\"lng\":-68.111025,\"seat\":true,\"wheelchair\":true,\"status\":true},{\"uuid\":\"stop-05\",\"name\":\"UMSA\",\"address\":\"Av. Poeta, PUC \",\"lat\":-16.503878,\"lng\":-68.12851,\"seat\":true,\"wheelchair\":true,\"status\":true},{\"uuid\":\"stop-08\",\"name\":\"Plza. Camacho\",\"address\":\"Calle Bueno esq. Av. Camacho\",\"lat\":-16.500322,\"lng\":-68.132068,\"seat\":true,\"wheelchair\":true,\"status\":true}],\"busList\":[{\"uuid\":\"bus-01\",\"plate\":\"1234ABC\",\"driveName\":\"Juan Perez\",\"type\":\"pumakatari\",\"capacity\":50},{\"uuid\":\"bus-02\",\"plate\":\"1235ABD\",\"driveName\":\"Pepe Escobar\",\"type\":\"pumakatari\",\"capacity\":50},{\"uuid\":\"bus-03\",\"plate\":\"1236ABE\",\"driveName\":\"Cristiano Ronaldo\",\"type\":\"chikititi\",\"capacity\":25}]},{\"uuid\":\"route-02\",\"name\":\"Irpavi II\",\"stopList\":[{\"uuid\":\"stop-09\",\"name\":\"Parque Sensao\",\"address\":\"Alto Obrajes, Av. Raúl Gamarra\",\"lat\":-16.520335,\"lng\":-68.107955,\"seat\":true,\"wheelchair\":true,\"status\":true},{\"uuid\":\"stop-10\",\"name\":\"Teleférico Alto Obrajes\",\"address\":\"Teleférico Línea Verde estación Alto Obrajes\",\"lat\":-16.52133,\"lng\":-68.110017,\"seat\":true,\"wheelchair\":true,\"status\":true},{\"uuid\":\"stop-11\",\"name\":\"Normal\",\"address\":\"Alto Obrajes, Normal\",\"lat\":-16.519943,\"lng\":-68.113065,\"seat\":true,\"wheelchair\":true,\"status\":true},{\"uuid\":\"stop-12\",\"name\":\"Puente Independencia\",\"address\":\"Alto Obrajes, Puente trillizo\",\"lat\":-16.514244,\"lng\":-68.114365,\"seat\":true,\"wheelchair\":true,\"status\":true},{\"uuid\":\"stop-13\",\"name\":\"San Jorge\",\"address\":\"San Jorge, Corte Electoral\",\"lat\":-16.51516,\"lng\":-68.119547,\"seat\":true,\"wheelchair\":true,\"status\":true},{\"uuid\":\"stop-14\",\"name\":\"Plza. Camacho\",\"address\":\"Calle Bueno esq. Av. Camacho\",\"lat\":-16.500322,\"lng\":-68.132068,\"seat\":true,\"wheelchair\":true,\"status\":true}],\"busList\":[{\"uuid\":\"bus-04\"},{\"uuid\":\"bus-05\"}]}]";
        MutableLiveData<Base> result = new MutableLiveData<>();
        try {
            List<Route> routes = new Gson().fromJson(json, new TypeToken<List<Route>>() {
            }.getType());
            result.postValue(new Base(routes));
        } catch (Exception ex) {

        }
        return result;
    }

    @Override
    public LiveData<Base> getRouteDetails(String uuid) {
        return null;
    }
}

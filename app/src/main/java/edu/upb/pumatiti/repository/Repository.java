package edu.upb.pumatiti.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.User;
import edu.upb.pumatiti.repository.api.ApiRepository;
import edu.upb.pumatiti.repository.firebase.FirebaseRepository;
import edu.upb.pumatiti.repository.local.LocalRepository;

public class Repository implements RepositoryImpl {

    private static Repository instance;
    private LocalRepository local;

    public static Repository getInstance(Application application) {
        if (instance == null) {
            instance = new Repository(application);
        }
        return instance;
    }

    private Repository(Application application) {
        local = new LocalRepository(application);
    }

    @Override
    public LiveData<Base> login(String email, String password) {
        return FirebaseRepository.getInstance().login(email, password);
    }

    @Override
    public LiveData<Base> getRoutes() {
        return ApiRepository.getInstance().getRoutes();
    }

    @Override
    public LiveData<Base> getRouteDetails(String uuid) {
        return null;
    }

    @Override
    public void insert(User user) {
        local.insert(user);
    }

    @Override
    public LiveData<List<User>> getAll() {
        return local.getAll();
    }
}

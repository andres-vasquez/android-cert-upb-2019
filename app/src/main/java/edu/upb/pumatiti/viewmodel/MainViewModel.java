package edu.upb.pumatiti.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.repository.MockRepository;
import edu.upb.pumatiti.repository.Repository;
import edu.upb.pumatiti.repository.RepositoryImpl;

public class MainViewModel extends AndroidViewModel {

    private RepositoryImpl repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        //repository = Repository.getInstance();
        repository = MockRepository.getInstance();
    }

    public LiveData<Base> getRoutes() {
        return repository.getRoutes();
    }
}

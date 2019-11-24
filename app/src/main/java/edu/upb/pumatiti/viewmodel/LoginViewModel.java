package edu.upb.pumatiti.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.User;
import edu.upb.pumatiti.repository.Repository;

public class LoginViewModel extends AndroidViewModel {

    Repository repository;

    public LoginViewModel(Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Base> login(String username, String password) {
        return this.repository.login(username, password);
    }
}

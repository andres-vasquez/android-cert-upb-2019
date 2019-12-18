package edu.upb.pumatiti.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import java.util.List;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.User;
import edu.upb.pumatiti.models.ui.UserLogged;
import edu.upb.pumatiti.repository.Repository;
import edu.upb.pumatiti.utils.ResponseMapper;

public class RegisterViewModel extends AndroidViewModel {

    private Repository repository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public LiveData<Base> register(final User user) {
        final MutableLiveData<Base> result = new MutableLiveData<>();
        repository.insert(user);
        return result;
    }

    public LiveData<List<User>> getAll() {
        return repository.getAll();
    }
}

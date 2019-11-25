package edu.upb.pumatiti.repository.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.repository.firebase.FirebaseRepository;

public class ApiRepository {
    private static final String LOG = ApiRepository.class.getSimpleName();

    private static ApiRepository instance;

    public static ApiRepository getInstance() {
        if (instance == null) {
            instance = new ApiRepository();
        }
        return instance;
    }

    public ApiRepository() {

    }

    public LiveData<Base> getRoutes() {
        final MutableLiveData<Base> result = new MutableLiveData<>();
        return result;
    }
}

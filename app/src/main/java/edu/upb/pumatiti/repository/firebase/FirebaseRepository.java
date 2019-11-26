package edu.upb.pumatiti.repository.firebase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.upb.pumatiti.models.repository.Base;

public class FirebaseRepository {
    private static FirebaseRepository instance;

    public static FirebaseRepository getInstance() {
        if (instance == null) {
            instance = new FirebaseRepository();
        }
        return instance;
    }

    private FirebaseRepository() {

    }

    public LiveData<Base> login(String email, String password) {
        MutableLiveData<Base> results = new MutableLiveData<>();
        //TODO magic
        return results;
    }
}

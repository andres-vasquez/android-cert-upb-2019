package edu.upb.pumatiti.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.User;

public class LoginViewModel extends AndroidViewModel {

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Base> login(String email, String password) {
        final MutableLiveData<Base> result = new MutableLiveData<>();
        if (email.equals("admin@pumatiti") && password.equals("12345")) {
            User user = new User();
            user.setUuid("1");
            user.setEmail(email);
            user.setStatus(true);
            result.postValue(new Base(user));
        } else {
            result.postValue(new Base("Error not found", new NullPointerException()));
        }
        return result;
    }
}

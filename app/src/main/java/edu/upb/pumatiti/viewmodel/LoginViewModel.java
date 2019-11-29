package edu.upb.pumatiti.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.InterruptedIOException;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.User;
import edu.upb.pumatiti.models.ui.UserLogged;
import edu.upb.pumatiti.utils.ResponseMapper;

public class LoginViewModel extends AndroidViewModel {

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Base> login(final String email, final String password) {
        final MutableLiveData<Base> result = new MutableLiveData<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    User user = new User();
                    user.setUuid("1");
                    user.setEmail(email);
                    user.setStatus(true);

                    UserLogged userLogged = ResponseMapper.mapUserToUserLooged(user);
                    result.postValue(new Base(userLogged));
                } catch (InterruptedException ex) {
                    result.postValue(new Base("Interrupted", ex));
                }
            }
        }).start();
        return result;
    }
}

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

import java.io.InterruptedIOException;

import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.User;
import edu.upb.pumatiti.models.ui.UserLogged;
import edu.upb.pumatiti.repository.Repository;
import edu.upb.pumatiti.utils.ResponseMapper;

public class LoginViewModel extends AndroidViewModel {

    private Repository repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public LiveData<Base> login(final String email, final String password) {
        final MutableLiveData<Base> result = new MutableLiveData<>();
        repository.login(email, password).observeForever(new Observer<Base>() {
            @Override
            public void onChanged(Base base) {
                if (base.isSuccess()) {
                    FirebaseUser user = (FirebaseUser) base.getData();
                    UserLogged userLogged = ResponseMapper.mapUserToUserLooged(user);
                    result.postValue(new Base(userLogged));
                } else {
                    result.postValue(base);
                }
            }
        });
        /*new Thread(new Runnable() {
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
        }).start();*/
        return result;
    }
}

package edu.upb.pumatiti.preparation.repository.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.upb.pumatiti.preparation.models.repository.Base;
import edu.upb.pumatiti.preparation.models.repository.Bus;
import edu.upb.pumatiti.preparation.utils.ResponseMapper;

public class FirebaseRepository {

    private static final String LOG = FirebaseRepository.class.getSimpleName();

    private static FirebaseRepository instance;
    private FirebaseAuth auth;
    private FirebaseDatabase db;

    public static FirebaseRepository getInstance() {
        if (instance == null) {
            instance = new FirebaseRepository();
        }
        return instance;
    }

    private FirebaseRepository() {
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
    }


    public LiveData<Base> login(String email, String password) {
        final MutableLiveData<Base> result = new MutableLiveData<>();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            result.postValue(
                                    new Base(ResponseMapper.mapFirebaseUserToUsserLogged(user)));
                        } else {
                            Log.e(LOG, "" + task.getException().getMessage());
                            result.postValue(
                                    new Base("autentication_failed",
                                            task.getException()));
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(LOG, "" + e.getMessage());
                        result.postValue(
                                new Base("autentication_failed_internet", e));
                    }
                });
        return result;
    }

    public LiveData<Base> setValue(String path, Object value) {
        final MutableLiveData<Base> result = new MutableLiveData<>();
        db.getReference(path).setValue(value);
        return result;
    }

    public LiveData<Base> subscribeToValues(String path) {
        final MutableLiveData<Base> result = new MutableLiveData<>();
        db.getReference(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    Map<String, Bus> buses = (HashMap<String, Bus>) dataSnapshot.getValue();

                    Gson gson = new Gson();
                    List<Bus> busesList = new ArrayList<>();
                    Iterator hmIterator = buses.entrySet().iterator();
                    while (hmIterator.hasNext()) {
                        Map.Entry mapElement = (Map.Entry) hmIterator.next();
                        Bus bus = gson.fromJson(gson.toJson(mapElement.getValue()), Bus.class);
                        busesList.add(bus);
                    }
                    result.postValue(new Base(busesList));
                } catch (Exception ex) {
                    Log.e(LOG, "" + ex.getMessage());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return result;
    }
}

package edu.upb.pumatiti.repository.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.upb.pumatiti.models.repository.User;
import edu.upb.pumatiti.repository.local.db.PumatitiDb;

public class LocalRepository {
    private PumatitiDb db;


    public LocalRepository(Application application) {
        db = PumatitiDb.getDatabase(application);
    }

    public void insert(final User user) {
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        db.userDao().insert(user);
                    }
                }
        );
        thread.start();
    }

    public LiveData<List<User>> getAll() {
        return db.userDao().getAll();
    }
}

package edu.upb.pumatiti.repository.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import edu.upb.pumatiti.models.repository.User;
import edu.upb.pumatiti.repository.local.dao.UserDao;


@Database(entities = {User.class}, version = 1)
public abstract class PumatitiDb extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile PumatitiDb INSTANCE;

    static public PumatitiDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PumatitiDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PumatitiDb.class, "pumatiti_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

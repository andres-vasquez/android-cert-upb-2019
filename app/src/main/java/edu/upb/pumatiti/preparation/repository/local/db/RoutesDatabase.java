package edu.upb.pumatiti.preparation.repository.local.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import edu.upb.pumatiti.preparation.models.repository.Bus;
import edu.upb.pumatiti.preparation.models.repository.Route;
import edu.upb.pumatiti.preparation.models.repository.Stop;
import edu.upb.pumatiti.preparation.repository.local.dao.RouteDao;

@Database(entities = {Route.class, Stop.class, Bus.class},
        version = 1)
public abstract class RoutesDatabase extends RoomDatabase {

    public abstract RouteDao routeDao();

    private static volatile RoutesDatabase INSTANCE;

    static public RoutesDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoutesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoutesDatabase.class, "route_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

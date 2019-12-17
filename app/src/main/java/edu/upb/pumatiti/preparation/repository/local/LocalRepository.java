package edu.upb.pumatiti.preparation.repository.local;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.upb.pumatiti.preparation.models.repository.Route;
import edu.upb.pumatiti.preparation.repository.local.dao.RouteDao;
import edu.upb.pumatiti.preparation.repository.local.db.RoutesDatabase;

public class LocalRepository {

    public static final String LOG = LocalRepository.class.getSimpleName();

    private RouteDao routeDao;
    private LiveData<List<Route>> routes;

    public LocalRepository(Application application) {
        RoutesDatabase db = RoutesDatabase.getDatabase(application);
        routeDao = db.routeDao();
    }

    public LiveData<List<Route>> getRoutes() {
        return routeDao.getAll();
    }

    public void insertRoute(Route route) {
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        long id = routeDao.insert(route);
                        Log.e(LOG, "Insert:" + id);
                    }
                }
        );
        thread.start();
    }
}

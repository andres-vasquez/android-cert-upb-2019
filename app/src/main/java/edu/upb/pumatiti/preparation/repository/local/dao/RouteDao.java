package edu.upb.pumatiti.preparation.repository.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.upb.pumatiti.preparation.models.repository.Route;

@Dao
public interface RouteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Route route);

    @Query("DELETE FROM routes_table")
    void deleteAll();

    @Query("SELECT * from routes_table ORDER BY uuid ASC")
    LiveData<List<Route>> getAll();
}

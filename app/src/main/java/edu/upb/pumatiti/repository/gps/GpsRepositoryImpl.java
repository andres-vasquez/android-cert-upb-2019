package edu.upb.pumatiti.repository.gps;

import androidx.lifecycle.LiveData;

import edu.upb.pumatiti.models.repository.Base;

public interface GpsRepositoryImpl {
    LiveData<Base> subscribeToLocation();

    void unsubscribeLocation();
}

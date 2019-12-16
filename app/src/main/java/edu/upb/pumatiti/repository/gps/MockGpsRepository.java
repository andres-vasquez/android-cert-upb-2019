package edu.upb.pumatiti.repository.gps;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.upb.pumatiti.models.repository.Base;

public class MockGpsRepository implements GpsRepositoryImpl {

    @Override
    public LiveData<Base> subscribeToLocation() {
        final MutableLiveData<Base> results = new MutableLiveData<>();
        //TODO add fake location
        return results;
    }

    @Override
    public void unsubscribeLocation() {

    }
}

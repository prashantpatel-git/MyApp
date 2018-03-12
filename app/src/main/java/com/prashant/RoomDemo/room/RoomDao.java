package com.prashant.RoomDemo.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.prashant.RoomDemo.models.NearbyPlace;

import java.util.List;

/**
 * Created by Prashant on 11/03/18.
 */

@Dao
public interface RoomDao {

    @Query("Select * from NearbyPlace")
    List<NearbyPlace> getPlaces();

    @Insert
    void insertAllPlaces(List<NearbyPlace> nearbyPlaceList);
}

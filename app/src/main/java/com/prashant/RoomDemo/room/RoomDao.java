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

    /*@Insert
    void insertTeacher(Teacher teacher);

    @Insert
    void insertStudent(Student student);

    @Query("Select * from Teacher")
    Maybe<List<Teacher>> getTeachers();

    @Query("Select s.*, t.* from Teacher t, Student s Where s.tid=:teacherId")
    Maybe<List<StudentTeacher>> getStudentsAndTeachers(int teacherId);*/
}

package com.prashant.RoomDemo.models.school;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Prashant on 22/03/18.
 */

@Entity
public class Student {

    @PrimaryKey(autoGenerate = true)
    private int studentId;

    private String studentName;

    @ColumnInfo(name = "tid")
    private int teacherId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}

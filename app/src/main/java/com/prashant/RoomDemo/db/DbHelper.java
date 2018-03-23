package com.prashant.RoomDemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.prashant.RoomDemo.models.school.Student;
import com.prashant.RoomDemo.models.school.Teacher;

import java.util.ArrayList;
import java.util.List;

import static com.prashant.RoomDemo.db.tables.StudentTable.STUDENT_CREATE_QUERY;
import static com.prashant.RoomDemo.db.tables.StudentTable.STUDENT_ID;
import static com.prashant.RoomDemo.db.tables.StudentTable.STUDENT_NAME;
import static com.prashant.RoomDemo.db.tables.StudentTable.STUDENT_TABLE;
import static com.prashant.RoomDemo.db.tables.TeacherTable.TEACHER_CREATE_QUERY;
import static com.prashant.RoomDemo.db.tables.TeacherTable.TEACHER_ID;
import static com.prashant.RoomDemo.db.tables.TeacherTable.TEACHER_NAME;
import static com.prashant.RoomDemo.db.tables.TeacherTable.TEACHER_TABLE;

/**
 * Created by Prashant on 22/03/18.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static DbHelper dbHelper;
    private static int DB_VERSION = 1;
    private static String DB_NAME = "mydb";

    private DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static synchronized DbHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DbHelper(context);
        }
        return dbHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(STUDENT_CREATE_QUERY);
        sqLiteDatabase.execSQL(TEACHER_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertStudent(Student student) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NAME, student.getStudentId());
        contentValues.put(TEACHER_ID, student.getTeacherId());

        long insert = database.insertWithOnConflict(STUDENT_TABLE, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);

        database.close();

        return insert < 0 ? false : true;
    }

    public boolean insertTeacher(Teacher teacher) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEACHER_NAME, teacher.getTeacherName());

        long insert = database.insertWithOnConflict(TEACHER_TABLE, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);

        database.close();

        return insert < 0 ? false : true;

    }

    public List<Teacher> getTeachers() {

        List<Teacher> teacherList = new ArrayList<>();
        String query = "Select * FROM " + TEACHER_TABLE;

        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Teacher teacher = new Teacher();
                teacher.setId(cursor.getInt(cursor.getColumnIndex(TEACHER_ID)));
                teacher.setTeacherName(cursor.getString(cursor.getColumnIndex(TEACHER_NAME)));
                teacherList.add(teacher);

            } while (cursor.moveToNext());
        }
        return teacherList;
    }

    public List<Student> getStudents(int teacherId) {

        List<Student> studentList = new ArrayList<>();
        String query = "Select * FROM " + STUDENT_TABLE + " WHERE " + TEACHER_ID + "=?";

        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, new String[]{teacherId + ""});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setStudentId(cursor.getInt(cursor.getColumnIndex(STUDENT_ID)));
                student.setStudentName(cursor.getString(cursor.getColumnIndex(STUDENT_NAME)));
                student.setTeacherId(cursor.getInt(cursor.getColumnIndex(TEACHER_ID)));
                studentList.add(student);

            } while (cursor.moveToNext());
        }
        return studentList;
    }
}

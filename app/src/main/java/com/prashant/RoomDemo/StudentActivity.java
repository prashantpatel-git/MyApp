package com.prashant.RoomDemo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.prashant.RoomDemo.adapters.StudentAdapter;
import com.prashant.RoomDemo.models.school.Student;
import com.prashant.RoomDemo.models.school.StudentTeacher;

import java.util.ArrayList;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class StudentActivity extends AppCompatActivity {

    ArrayList<StudentTeacher> studentTeachers = new ArrayList<>();
    RecyclerView studentRv;
    StudentAdapter studentAdapter;
    int teacherId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        setSupportActionBar((Toolbar) findViewById(R.id.activity_toolbar));

        studentRv = findViewById(R.id.activity_rv);

        teacherId = getIntent().getIntExtra("tid", 0);

        getStudents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.toolbar_action_add:
                showAddStudentDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getStudents() {
        /*MyAppDatabase.getInstance().roomDao()
                .getStudentsAndTeachers(teacherId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<StudentTeacher>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<StudentTeacher> teachers) {
                        studentTeachers.clear();
                        studentTeachers.addAll(teachers);
                        setOrUpdateAdapter();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }

    private void addStudent(final Student student) {
        /*Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                MyAppDatabase.getInstance().roomDao().insertStudent(student);

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        getStudents();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Add Teacher", e.getMessage());
                    }
                });
        ;*/
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                //MyAppDatabase.getInstance().roomDao().insertTeacher(teacher);
                if (com.prashant.RoomDemo.db.DbHelper.getInstance(StudentActivity.this).insertStudent(student)) {

                }

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        getStudents();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Add Teacher", e.getMessage());
                    }
                });

    }

    private void setOrUpdateAdapter() {

        if (studentAdapter == null) {
            studentAdapter = new StudentAdapter(studentTeachers, this);
            studentRv.setLayoutManager(new LinearLayoutManager(this));
            studentRv.setAdapter(studentAdapter);
        } else
            studentAdapter.notifyDataSetChanged();
    }

    private void showAddStudentDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_teacher);

        Button addBtn = dialog.findViewById(R.id.addTeacher);
        addBtn.setText("Add Student");
        final EditText editText = (EditText) dialog.findViewById(R.id.teacherNameEt);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setStudentName(editText.getText().toString());
                addStudent(student);
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}

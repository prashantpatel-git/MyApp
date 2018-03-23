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

import com.prashant.RoomDemo.adapters.TeacherAdapter;
import com.prashant.RoomDemo.db.DbHelper;
import com.prashant.RoomDemo.models.school.Teacher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class TeacherListActivity extends AppCompatActivity {

    ArrayList<Teacher> teachersList = new ArrayList<>();
    RecyclerView teacherRv;
    TeacherAdapter teacherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        setSupportActionBar((Toolbar) findViewById(R.id.activity_toolbar));

        teacherRv = findViewById(R.id.activity_rv);

        getTeachers();
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
                showAddTeacherDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getTeachers() {
        /*MyAppDatabase.getInstance().roomDao()
                .getTeachers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Teacher>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Teacher> teachers) {
                        teachersList.clear();
                        teachersList.addAll(teachers);
                        setOrUpdateAdapter();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/

        Observable<List<Teacher>> observable = Observable.create(new ObservableOnSubscribe<List<Teacher>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Teacher>> e) throws Exception {

                try {
                    List<Teacher> teacherList = DbHelper.getInstance(TeacherListActivity.this).getTeachers();
                    e.onNext(teacherList);
                    e.onComplete();
                } catch (Exception e1) {
                    e.onError(e1.getCause());
                    e1.printStackTrace();
                }
            }
        });

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Teacher>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Teacher> teachers) {
                        teachersList.clear();
                        teachersList.addAll(teachers);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        setOrUpdateAdapter();
                    }
                });
    }

    private void addTeacher(final Teacher teacher) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                //MyAppDatabase.getInstance().roomDao().insertTeacher(teacher);
                if (DbHelper.getInstance(TeacherListActivity.this).insertTeacher(teacher)) {

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
                        teachersList.add(teacher);
                        setOrUpdateAdapter();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Add Teacher", e.getMessage());
                    }
                });

    }

    private void setOrUpdateAdapter() {

        if (teacherAdapter == null) {
            teacherAdapter = new TeacherAdapter(this, teachersList);
            teacherRv.setLayoutManager(new LinearLayoutManager(this));
            teacherRv.setAdapter(teacherAdapter);
        } else
            teacherAdapter.notifyDataSetChanged();
    }

    private void showAddTeacherDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_teacher);

        Button addBtn = dialog.findViewById(R.id.addTeacher);
        final EditText editText = (EditText) dialog.findViewById(R.id.teacherNameEt);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Teacher teacher = new Teacher();
                teacher.setTeacherName(editText.getText().toString());
                addTeacher(teacher);
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}

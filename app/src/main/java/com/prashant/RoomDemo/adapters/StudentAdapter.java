package com.prashant.RoomDemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prashant.RoomDemo.R;
import com.prashant.RoomDemo.models.school.StudentTeacher;

import java.util.ArrayList;

/**
 * Created by Prashant on 22/03/18.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.TeacherViewHolder> {

    ArrayList<StudentTeacher> studentTeachersList;
    Context context;

    public StudentAdapter(ArrayList<StudentTeacher> studentTeachersList, Context context) {
        this.studentTeachersList = studentTeachersList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new TeacherViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {

        //holder.nameTv.setText(studentTeachersList.get(position).getTeacher().getTeacherName());
        //holder.nameTv2.setText(studentTeachersList.get(position).getStudent().getStudentName());
    }

    @Override
    public int getItemCount() {
        return studentTeachersList.size();
    }

    public class TeacherViewHolder extends RecyclerView.ViewHolder {

        TextView nameTv, nameTv2;

        public TeacherViewHolder(View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.list_item_name_tv);
            nameTv2 = itemView.findViewById(R.id.list_item_name2_tv);
        }
    }
}

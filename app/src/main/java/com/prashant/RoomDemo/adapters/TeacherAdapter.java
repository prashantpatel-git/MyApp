package com.prashant.RoomDemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prashant.RoomDemo.R;
import com.prashant.RoomDemo.StudentActivity;
import com.prashant.RoomDemo.models.school.Teacher;

import java.util.ArrayList;

/**
 * Created by Prashant on 22/03/18.
 */

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {

    ArrayList<Teacher> teachersList;
    Context context;

    public TeacherAdapter(Context context, ArrayList<Teacher> teachersList) {
        this.teachersList = teachersList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new TeacherViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, final int position) {

        holder.nameTv.setText(teachersList.get(position).getTeacherName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(
                        new Intent(context, StudentActivity.class).putExtra("tid", teachersList.get(position).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return teachersList.size();
    }

    public class TeacherViewHolder extends RecyclerView.ViewHolder {

        TextView nameTv;
        View itemView;

        public TeacherViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            nameTv = itemView.findViewById(R.id.list_item_name_tv);
        }
    }
}

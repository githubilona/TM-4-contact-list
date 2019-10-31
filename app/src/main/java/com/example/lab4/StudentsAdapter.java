package com.example.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab4.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsAdapter extends BaseAdapter {

    private Context context;
    List<Student> students = new ArrayList<>();

    public StudentsAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.student_list_item, null);
        TextView studentName = (TextView) row.findViewById(R.id.studentNameTextView);
        TextView studentSurname = (TextView) row.findViewById(R.id.studentSurnameTextView);

        Student student = students.get(i);
        studentName.setText(student.getName());
        studentSurname.setText(student.getPhone() + "");

        return row;
    }
}

package com.example.lab4.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lab4.R;
import com.example.lab4.models.Student;
import com.example.lab4.StudentsAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentsListActivity extends AppCompatActivity {
    ListView listView;
//    List<String> students;
//    ArrayAdapter<String> adapter;
    List<Student> students;
   StudentsAdapter adapter;
    int index = 0;

//
//    public void addStudent(String student) {
//        students.add(student);
//        index++;
//        adapter.notifyDataSetChanged();
//    }

    public void addStudent(Student student) {
        students.add(student);
        index++;
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
               // data.getOb
                CharSequence resultName = data.getCharSequenceExtra("resultName");
                CharSequence resultPhone = data.getCharSequenceExtra("resultPhone");
                // Obsługa rezultatów które otrzymaliśmy z wywołanej aktywności
                System.out.println("RESULT NAME)))))))))))))))))))))" + resultName);
                System.out.println("RESULT PHONE)))))))))))))))))))))" + resultPhone);

//                addStudent(resultName + " " + resultPhone);
                addStudent(new Student(resultName+"", resultPhone+""));

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // W przpyapku otrzymania błędnych rezultatów
                Toast.makeText(this, "Wrong result!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void startAddStudentActivity(View view) {
        Intent intent = new Intent(this, AddStudentActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        listView = findViewById(R.id.listView);
        students = new ArrayList<>();
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        adapter = new StudentsAdapter(this,students);
        listView.setAdapter(adapter);

//        students.add("Student 1");
//        students.add("Student 2");
//        students.add("Student 3");

        students.add(new Student("Contact 1 ", "343-545-354"));
        students.add(new Student("Contact 2 ", "676-567-863"));
        students.add(new Student("Contact 3 ", "965-667-863"));

    }
}

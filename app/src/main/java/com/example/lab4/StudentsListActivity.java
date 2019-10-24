package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentsListActivity extends AppCompatActivity {
    ListView listView;
    List<String> students;
    ArrayAdapter<String> adapter;
    int index = 0;


    public void addStudent(String student) {
        students.add(student);
        index++;
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                CharSequence result = data.getCharSequenceExtra("result");
                //Obsługa rezultatów które otrzymaliśmy z wywołanej aktywności
                System.out.println("RESULT )))))))))))))))))))))" + result);
                addStudent(result + "");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //W przpyapku otrzymania błędnych rezultatów
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
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        listView.setAdapter(adapter);

    }
}

package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentsListActivity extends AppCompatActivity {

    Button addButton;
    Button removeButton;
    Button deleteButton;
    Button cancelButton;
    ListView listView;
    List<Student> students;
    StudentsAdapter adapter;
    int index = 0;
    SparseBooleanArray checkedItemsPositions;
    int itemCount;
    LayoutType layoutType;

    public void addStudent(Student student) {
        students.add(student);
        index++;
        adapter.notifyDataSetChanged();
    }

    private void fillArray() {
        checkedItemsPositions = listView.getCheckedItemPositions();
        itemCount = listView.getCount();

        for (int i = 0; i < itemCount; i++) {
            checkedItemsPositions.put(i, adapter.getChecked()[i]);
        }
    }

    public void deleteOnClick(View view) {
        fillArray();

        for (int i = itemCount - 1; i >= 0; i--) {
            if (checkedItemsPositions.get(i)) {
                adapter.getChecked()[i] = false;
                adapter.remove(i);
            }
        }
        //checkedItemsPositions.clear(); don't work
        adapter.notifyDataSetChanged();

    }

    public void cancelOnClick(View view) {
        setSimpleListView();
    }

    public void removeOnClick(View view) {
        setMultipleChoiceView();
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
                addStudent(new Student(resultName + "", resultPhone + ""));

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // W przpyapku otrzymania błędnych rezultatów
                Toast.makeText(this, "Name and phone can't be empty!", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void setSimpleListView() {
        removeButton.setVisibility(View.VISIBLE);
        addButton.setVisibility(View.VISIBLE);
        deleteButton.setVisibility(View.INVISIBLE);
        cancelButton.setVisibility(View.INVISIBLE);

        adapter = new StudentsAdapter(this, R.layout.student_list_item, students);
        listView.setAdapter(adapter);
        layoutType = LayoutType.SIMPLE_LAYOUT;
    }

    public void setMultipleChoiceView() {
        removeButton.setVisibility(View.INVISIBLE);
        addButton.setVisibility(View.INVISIBLE);
        deleteButton.setVisibility(View.VISIBLE);
        cancelButton.setVisibility(View.VISIBLE);

        adapter = new StudentsAdapter(this, R.layout.student_list_item_multiple_choice, students);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        layoutType = LayoutType.MULTIPLE_CHOICE_LAYOUT;
        adapter.notifyDataSetChanged();
    }

    public void startAddStudentActivity(View view) {
        Intent intent = new Intent(this, AddStudentActivity.class);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        removeButton = findViewById(R.id.removeButton);
        addButton = findViewById(R.id.addButton);
        deleteButton = findViewById(R.id.deleteButton);
        cancelButton = findViewById(R.id.cancelButton);

        listView = findViewById(R.id.listView);
        students = new ArrayList<>();
        setSimpleListView();

        students.add(new Student("Contact 1 ", "343-545-354"));
        students.add(new Student("Contact 2 ", "676-567-863"));
        students.add(new Student("Contact 3 ", "965-667-863"));


    }
}

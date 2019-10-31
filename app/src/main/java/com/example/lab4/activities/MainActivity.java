package com.example.lab4.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lab4.R;

public class MainActivity extends AppCompatActivity {

    public void startStudentListActivity(View view) {
        Intent intent = new Intent(this, StudentsListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}

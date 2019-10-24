package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddStudentActivity extends AppCompatActivity {

    public void onFinishAddClick(View view){
        TextView tv = findViewById(R.id.editText);
        Intent returnIntent = getIntent();
        returnIntent.putExtra("result", tv.getText());

        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
    }

}

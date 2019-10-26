package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

public class AddStudentActivity extends AppCompatActivity {

    private static final int PICK_CONTACT_REQUEST = 1;

    public void importContactsOnClick(View view){
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Pobieramy URI przekazany z książki adresowej do wybranego kontaktu
                Uri contactUri = data.getData();
                // Potrzebujemy tylko imienia
                String[] projection =
                        {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
                // Powinniśmy to robić w oddzielnym wątku bo operacje z użyciem kursora
                // mogą być czasochłonne.Można wykorzystać klasę CursorLoader.
                Cursor cursor = getContentResolver()
                        .query(contactUri, projection, null, null, null);
                //pobranie pierwszego wiersza
                cursor.moveToFirst();
                // Pobranie kolumny o odpowiednim indeksie
                int column =
                        cursor.getColumnIndex(ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME);
                String displayName = cursor.getString(column);
                TextView tv = (TextView) findViewById(R.id.nameEditText);
                tv.setText(displayName);
            }
        }
    }
        public void onFinishAddClick(View view){
        TextView tv = findViewById(R.id.nameEditText);
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

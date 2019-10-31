package com.example.lab4.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lab4.R;

import java.lang.reflect.Array;
import java.util.Arrays;

import static android.provider.Settings.NameValueTable.NAME;

public class AddStudentActivity extends AppCompatActivity {

    TextView nameEditText;
    TextView phoneEditText;
    private static final int PICK_CONTACT_REQUEST = 1;

    public void importContactsOnClick(View view){
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);

    }
//    void calContctPickerFnc()
//    {
//        Intent calContctPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//        calContctPickerIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
//        startActivityForResult(calContctPickerIntent, 1);
//    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (1):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contctDataVar = data.getData();

                    Cursor contctCursorVar = getContentResolver().query(contctDataVar, null,
                            null, null, null);
                    if (contctCursorVar.getCount() > 0) {
                        while (contctCursorVar.moveToNext()) {
                            String ContctUidVar = contctCursorVar.getString(contctCursorVar.getColumnIndex(ContactsContract.Contacts._ID));

                            String ContctNamVar = contctCursorVar.getString(contctCursorVar.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                            Log.i("Names", ContctNamVar);
                            nameEditText.setText(ContctNamVar);
                            if (Integer.parseInt(contctCursorVar.getString(contctCursorVar.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                                // Query phone here. Covered next
                                String ContctMobVar = contctCursorVar.getString(contctCursorVar.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                Log.i("Number", ContctMobVar);
                                phoneEditText.setText(ContctMobVar);


                            }
                        }
                    }
                }
                break;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (requestCode == PICK_CONTACT_REQUEST) {
//           if (resultCode == RESULT_OK) {
////                // Pobieramy URI przekazany z książki adresowej do wybranego kontaktu
////                Uri contactUri = data.getData();
////                // Potrzebujemy tylko imienia
////                String[] projection =
////                        {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
////                String[] phone =
////                        {ContactsContract.CommonDataKinds.Phone.NUMBER};
////                // Powinniśmy to robić w oddzielnym wątku bo operacje z użyciem kursora
////                // mogą być czasochłonne.Można wykorzystać klasę CursorLoader.
////                Cursor cursor = getContentResolver()
////                        .query(contactUri, projection, null, null, null);
////                //pobranie pierwszego wiersza
////                cursor.moveToFirst();
////                // Pobranie kolumny o odpowiednim indeksie
////                int column =
////                        cursor.getColumnIndex(ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME);
////                String displayName = cursor.getString(column);
////                TextView nameEditText = (TextView) findViewById(R.id.nameEditText);
////                TextView phoneEditText = findViewById(R.id.phoneEditText);
////
////                nameEditText.setText(displayName);
////                System.out.println("/..........................." + phone);
////                for(String p :phone){
////                    System.out.println("........." + p);
////                }
////                phoneEditText.setText(Arrays.toString(phone));
////
//
//           }
//        }
//    }
    private void getNameNumber(){
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Log.i("Names", name);
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                {
                    // Query phone here. Covered next
                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                    while (phones.moveToNext()) {
                        String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.i("Number", phoneNumber);

                    }
                    phones.close();
                }

            }
        }
    }
    public void onFinishAddClick(View view){
        Intent returnIntent = getIntent();
        returnIntent.putExtra("resultName", nameEditText.getText());
        returnIntent.putExtra("resultPhone", phoneEditText.getText());

        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        nameEditText= findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
//        nameEditText.requestFocus(0);


    }

}

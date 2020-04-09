package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button mAddButton;
    private Button mEditButton;

    static final int ADD_REQUEST = 1;

    private String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAddButton = findViewById(R.id.addButton);
        mEditButton = findViewById(R.id.editButton);

    }

    public void startAddActivity(View v){
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivityForResult(intent, ADD_REQUEST);
    }
//

    public void startEditActivity(View v){

        // Creates a new Intent to insert a contact
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);

        // Sets the MIME type to match the Contacts Provider
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        // Inserts the name
        intent.putExtra(ContactsContract.Intents.Insert.NAME, Name);

        startActivity(intent);

    }

    // toast to display pre name adding message
    public void displayPreAddToast(View v){
        Toast.makeText(getApplicationContext(), "Please add contact name first", Toast.LENGTH_LONG).show();
    }

    // toast to display post name adding message
    public void displayPostAddToast(View v){
        Toast.makeText(getApplicationContext(), String.format("%s is not a valid name", Name), Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == ADD_REQUEST) {

            // extracts the name from add activity
            Bundle extras = data.getExtras();
            Name = extras.getString("name");

            // changes the on click listener of the edit button depending on result code received from add activity
            mEditButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(resultCode == RESULT_OK){
                        startEditActivity(v);
                    }
                    else if(resultCode == RESULT_CANCELED){
                        displayPostAddToast(v);
                    }
                }
            });
        }
    }

}

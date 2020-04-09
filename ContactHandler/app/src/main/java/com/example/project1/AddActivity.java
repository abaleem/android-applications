package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    private TextView Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Name = (EditText) findViewById(R.id.Name);


        TextView.OnEditorActionListener Listener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView userName, int actionId, KeyEvent event) {
                // triggered when done check pressed
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String userEnteredName = Name.getText().toString();

                    // trims leading and trailing white spaces
                    userEnteredName = userEnteredName.trim();

                    // divides full name using spaces. To make sure there is atleast a first and a last name
                    String[] names = userEnteredName.split(" ", 5);

                    // add name to intent to send back to previous activity
                    getIntent().putExtra("name", userEnteredName);

                    // if name is valid and length is greater than 1 then ok result sent otherwise cancelled
                    if(validateName(userEnteredName) && names.length > 1){
                        setResult(RESULT_OK, getIntent());
                    }
                    else{
                        setResult(RESULT_CANCELED, getIntent());
                    }
                    AddActivity.this.finish();
                }
                return true;
            }
        };

        Name.setOnEditorActionListener(Listener);
    }

    // validates the name against empty/null and only alphabets and spaces.
    public static boolean validateName(String str)
    {
        return ((!str.equals("")) && (str != null) && (str.matches("^[a-zA-Z ]*$")));
    }
}

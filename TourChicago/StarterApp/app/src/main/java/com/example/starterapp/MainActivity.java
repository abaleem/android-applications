package com.example.starterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LAUNCH_INTENT = "edu.uic.cs478.BroadcastReceiver.Launcher";
    private static final String PERMISSION = "edu.uic.cs478.sp2020.project3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAttractions = findViewById(R.id.buttonAttractions);
        Button buttonRestaurants = findViewById(R.id.buttonResturants);

        buttonAttractions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndBroadcast("att");
            }
        });

        buttonRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndBroadcast("res");
            }
        });

    }

    private void checkPermissionAndBroadcast(String activityToLaunch) {

        if (ContextCompat.checkSelfPermission(this, PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Sending broadcast...", Toast.LENGTH_SHORT).show();
            Intent aIntent = new Intent(LAUNCH_INTENT);
            aIntent.putExtra("activityToLaunch", activityToLaunch);
            sendBroadcast(aIntent);
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, 0) ;
        }

    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted. Press Button Again!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Bummer! No Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

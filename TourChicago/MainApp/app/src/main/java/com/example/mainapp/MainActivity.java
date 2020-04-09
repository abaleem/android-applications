package com.example.mainapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LAUNCH_INTENT = "edu.uic.cs478.BroadcastReceiver.Launcher";
    private static final String PERMISSION = "edu.uic.cs478.sp2020.project3";

    BroadcastReceiver mReceiver = new MyReciever();
    IntentFilter mFilter = new IntentFilter(LAUNCH_INTENT) ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // registering a receiver to listen to broadcasts with permission
        registerReceiver(mReceiver, mFilter, PERMISSION, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.attractions:
                startActivity(new Intent(MainActivity.this, AttractionsActivity.class));
                return true;
            case R.id.resturants:
                startActivity(new Intent(MainActivity.this, RestaurantsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }


}

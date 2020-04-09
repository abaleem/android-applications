package com.example.mainapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Broadcast Received!", Toast.LENGTH_SHORT).show();

        Bundle intentExtras = intent.getExtras();
        String activityToLaunch = intentExtras.getString("activityToLaunch");

        Intent mIntent = null;

        if(activityToLaunch.equals("res")){
            mIntent = new Intent(context, RestaurantsActivity.class);
        } else if (activityToLaunch.equals("att")) {
            mIntent = new Intent(context, AttractionsActivity.class);
        }

        context.startActivity(mIntent);

    }
}

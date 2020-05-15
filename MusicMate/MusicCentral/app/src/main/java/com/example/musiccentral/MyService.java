package com.example.musiccentral;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.example.musiccommon.MusicDataGenerator;
import java.util.ArrayList;
import java.util.List;


public class MyService extends IntentService {

    private static final int NOTIFICATION_ID = 1;
    private static String CHANNEL_ID = "MusicCentral Foreground";
    private Notification notification ;

    int[] Images = {R.drawable.dreams, R.drawable.moose, R.drawable.punky, R.drawable.summer, R.drawable.ukulele};
    String[] Names, Artists, Urls;


    public MyService() {
        super("MyService");
    }


    @Override
    public void onCreate() {
        super.onCreate();

        Names = getResources().getStringArray(R.array.name);
        Artists = getResources().getStringArray(R.array.artist);
        Urls = getResources().getStringArray(R.array.url);

        this.createNotificationChannel();
        notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                            .setSmallIcon(android.R.drawable.arrow_up_float)
                            .setOngoing(true).setContentTitle("MusicCentral")
                            .setContentText("Moved to foreground")
                            .setTicker("Moved to foreground")
                            .build();
        startForeground(NOTIFICATION_ID, notification);
    }


    private final MusicDataGenerator.Stub mBinder = new MusicDataGenerator.Stub() {

        @Override
        public List<MySong> retriveAllSongs() {
            synchronized (this){
                List<MySong> songs = new ArrayList();
                for (int i = 0; i < Images.length; i++) {
                    MySong ms = new MySong();
                    ms.Name = Names[i];
                    ms.Artist = Artists[i];
                    ms.URL = Urls[i];
                    ms.Image = BitmapFactory.decodeResource(getResources(), Images[i]);
                    songs.add(ms);
                }
                return songs;
            }
        }

        @Override
        public List<MySong> retriveSong(int song)  {
            synchronized (this){
                List<MySong> songs = new ArrayList();
                MySong ms = new MySong();
                ms.Name = Names[song-1];
                ms.Artist = Artists[song-1];
                ms.URL = Urls[song-1];
                ms.Image = BitmapFactory.decodeResource(getResources(), Images[song-1]);
                songs.add(ms);
                return songs;
            }
        }


        @Override
        public String retriveURL(int song) {
            synchronized (this){
                return Urls[song-1];
            }
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
    }


    private void createNotificationChannel() {
        CharSequence name = "MusicCentral notification";
        String description = "MusicCentral Service moved to foreground.";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}

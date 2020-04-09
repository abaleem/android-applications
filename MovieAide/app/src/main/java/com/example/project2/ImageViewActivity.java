package com.example.project2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageViewActivity extends AppCompatActivity {

    int[] moviePosters = {R.drawable.parasite_full, R.drawable.joker_full, R.drawable.ouatih_full, R.drawable.irishman_full, R.drawable.avengers_full, R.drawable.toystory_full};
    String[] movieLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieLinks = getResources().getStringArray(R.array.movie_links);

        // Get the Intent used to start this Activity
        Intent intent = getIntent();

        // Make a new ImageView
        // Example of programmatic layout definition
        ImageView imageView = new ImageView(getApplicationContext());

        final int positionClicked = intent.getIntExtra(MainActivity.EXTRA_RES_ID, 0);

        // Get the ID of the image to display and set it as the image for this ImageView
        imageView.setImageResource(moviePosters[positionClicked]);

        setContentView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse(movieLinks[positionClicked]);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
    }
}

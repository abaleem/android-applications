package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {


    String[] movieNames, movieYears, movieDirectorsNames, movieDurations, movieStars, imdbRatings, rottenRatings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieNames = getResources().getStringArray(R.array.movie_names);
        movieYears = getResources().getStringArray(R.array.movie_years);
        movieDirectorsNames = getResources().getStringArray(R.array.movie_director_name);
        movieDurations = getResources().getStringArray(R.array.movie_duration);
        movieStars = getResources().getStringArray(R.array.movie_stars);
        imdbRatings = getResources().getStringArray(R.array.imdb_rating);
        rottenRatings = getResources().getStringArray(R.array.rotten_rating);


        TextView movieName = findViewById(R.id.movieName);
        TextView movieReleaseDate = findViewById(R.id.movieReleaseDate);
        TextView movieDirectorsName = findViewById(R.id.movieDirector);
        TextView movieDuration = findViewById(R.id.movieDuration);
        TextView movieStar = findViewById(R.id.movieStars);
        TextView imdbRating = findViewById(R.id.imdbRating);
        TextView rottenRating = findViewById(R.id.rottenRating);

        // Get the Intent used to start this Activity and get the position clicked
        Intent intent = getIntent();
        final int positionClicked = intent.getIntExtra(MainActivity.EXTRA_RES_ID, 0);


        movieName.setText(movieNames[positionClicked]);
        movieReleaseDate.setText(movieYears[positionClicked]);
        movieDirectorsName.setText(movieDirectorsNames[positionClicked]);
        movieDuration.setText(movieDurations[positionClicked]);
        movieStar.setText(movieStars[positionClicked]);
        imdbRating.setText(imdbRatings[positionClicked]);
        rottenRating.setText(rottenRatings[positionClicked]);

    }
}

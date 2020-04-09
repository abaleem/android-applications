package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected static final String EXTRA_RES_ID = "POS";
    int[] movieLogos = {R.drawable.parasite_logo, R.drawable.joker_logo, R.drawable.ouatih_logo, R.drawable.irishman_logo, R.drawable.avengers_logo, R.drawable.toystory_logo};

    String[] movieNames, movieYears, movieLinks, movieDirectorsWiki, movieTrailers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieNames = getResources().getStringArray(R.array.movie_names);
        movieYears = getResources().getStringArray(R.array.movie_years);
        movieLinks = getResources().getStringArray(R.array.movie_links);
        movieDirectorsWiki = getResources().getStringArray(R.array.movie_director_wiki);
        movieTrailers = getResources().getStringArray(R.array.movie_trailer);



        ListView listView = findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                //Create an Intent to start the ImageViewActivity
                                                Intent intent = new Intent(MainActivity.this, ImageViewActivity.class);
                                                // Add the ID of the thumbnail to display as an Intent Extra
                                                intent.putExtra(EXTRA_RES_ID, position);
                                                // Start the ImageViewActivity
                                                startActivity(intent);
                                            }
                                        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId() == R.id.detailsAction){
            //Create an Intent to start the ImageViewActivity
            Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
            // Add the ID of the thumbnail to as an Intent Extra
            intent.putExtra(EXTRA_RES_ID, info.position);
            // Start the ImageViewActivity
            startActivity(intent);
            return true;
        } else if(item.getItemId() == R.id.trailerAction){
            Uri uriUrl = Uri.parse(movieTrailers[info.position]);
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
            return true;
        } else if(item.getItemId() == R.id.directorAction){
            Uri uriUrl = Uri.parse(movieDirectorsWiki[info.position]);
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
            return true;
        } else if(item.getItemId() == R.id.iMDbAction){
            Uri uriUrl = Uri.parse(movieLinks[info.position]);
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
            return true;
        } else {
            return false;
        }
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return movieLogos.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Recycling
            // So, when listView is not null, you should simply update its contents instead of inflating a new row layout.
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.custom_layout, null);
            }

            ImageView movieLogo = convertView.findViewById(R.id.movieLogoCustom);
            TextView movieName = convertView.findViewById(R.id.movieNameCustom);
            TextView movieYear = convertView.findViewById(R.id.movieYearCustom);

            movieLogo.setImageResource(movieLogos[position]);
            movieName.setText(movieNames[position]);
            movieYear.setText(movieYears[position]);

            return convertView;
        }
    }
}

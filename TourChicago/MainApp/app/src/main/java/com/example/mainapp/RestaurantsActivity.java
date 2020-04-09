package com.example.mainapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class RestaurantsActivity extends AppCompatActivity implements RestaurantsTitleFragment.ListSelectionListener {

    public static String[] titles, websites;

    FragmentManager mFragmentManager;
    private RestaurantsTitleFragment titleFragment;
    private RestaurantsWebsiteFragment websiteFragment;

    private FrameLayout titleFrameLayout, websiteFrameLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturants);

        // get the string array containing titles and web links
        titles = getResources().getStringArray(R.array.Restaurants);
        websites = getResources().getStringArray(R.array.RestaurantsLinks);

        // Get references to the frame layouts
        titleFrameLayout = findViewById(R.id.fragment_restaurants_title);
        websiteFrameLayout = findViewById(R.id.fragment_restaurants_view);


        // FragmentManager to dynamically manage fragments
        mFragmentManager = getSupportFragmentManager();

        // getting fragments using ids
        titleFragment =  (RestaurantsTitleFragment) mFragmentManager.findFragmentById(R.id.fragment_restaurants_title);
        websiteFragment =  (RestaurantsWebsiteFragment) mFragmentManager.findFragmentById(R.id.fragment_restaurants_view);

        if(titleFragment == null){
            titleFragment = new RestaurantsTitleFragment();
        }

        if(websiteFragment == null){
            websiteFragment = new RestaurantsWebsiteFragment();
        }

        // Add titleFragment to layout
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.fragment_restaurants_title, titleFragment);
        mFragmentTransaction.commit();

        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        mFragmentManager.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setLayout();
        websiteFragment.refreshWebPage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.attractions:
                startActivity(new Intent(RestaurantsActivity.this, AttractionsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setLayout() {
        int orientation = this.getResources().getConfiguration().orientation;
        // decide layout depending on orientation and if website fragment added
        if (!websiteFragment.isAdded()) {
            titleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            websiteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
        } else {
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                titleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
                websiteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            } else {
                titleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));
                websiteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 2f));
            }
        }
    }

    @Override
    public void onListSelection(int index) {
        // If the Website fragment has not been added, add it now
        if (!websiteFragment.isAdded()) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_restaurants_view, websiteFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();
        }
        // change browser url
        if (websiteFragment.getShownIndex() != index) {
            websiteFragment.showWebPageAtIndex(index);
        }
    }
}

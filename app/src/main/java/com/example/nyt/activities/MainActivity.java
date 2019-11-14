package com.example.nyt.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nyt.R;
import com.example.nyt.fragments.CatRecyclerFragment;
import com.example.nyt.fragments.ProfileFragment;
import com.example.nyt.model.Favourite;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

// Notice the Activity implements the interface OnFragmentInteractionListener, meaning this Activity
// MUST have the method defined by the interface (see bottom), and if it does then this Activity
// can be considered an OnFragmentInteractionListener; it listens for Fragment interaction.
// This is only relevant to ProfileFragment in this app (because ArticleRecyclerFragment has nothing
// to listen to).
public class MainActivity extends AppCompatActivity implements ProfileFragment.OnFragmentInteractionListener {

    BottomNavigationView bottomNavigationView;
    public static ArrayList<Favourite> favouriteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        favouriteList = new ArrayList<Favourite>();
        // I want there to be a Fragment in the slot from the start
        Fragment fragment = new CatRecyclerFragment();
        swapFragment(fragment);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // menuItem = the item on the bottom nav view that was selected
                // The id's here belong to the items in the menu of the BottomnNavigationView
                // The menu is chunked out as bottom_nav_menu.xml in the res > menu folder
                if (menuItem.getItemId() == R.id.nav_articles) {
                    Fragment fragment = new CatRecyclerFragment();
                    swapFragment(fragment);
                    return true;

                } else if (menuItem.getItemId() == R.id.nav_profile) {
                    Fragment fragment = new ProfileFragment();
                    swapFragment(fragment);
                    return true;
                }
                return false;
            }
        });

    }

    /**
     * Helper method to change the fragment displayed in the activity. We put this here so we don't
     * have to repeat the code every time we want to saw
     * @param fragment: instance of the fragment to go into the slot
     */
    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }

    /**
     * If the Fragment has recognised this Activity as a listener, the Fragment can cause events
     * that this Activity can listen to (and thus call this method when it hears the event).
     * @param string
     */
    @Override
    public void onFragmentInteraction(String string) {
        Toast.makeText(this, "Hello! I have come from the fragment. Message: " + string, Toast.LENGTH_SHORT).show();
    }

    public void showCoolMessage(String string) {
        Toast.makeText(this, "Welcome to the Cat Application", Toast.LENGTH_SHORT).show();


    }

}
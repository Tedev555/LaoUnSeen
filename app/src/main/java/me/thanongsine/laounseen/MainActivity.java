package me.thanongsine.laounseen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.thanongsine.laounseen.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add Fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content_main, new MainFragment())
                    .commit();
        }

    } // Main Method

} // Main Class

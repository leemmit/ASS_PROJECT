package com.example.assproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.settings);

        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            int itemID = menuItem.getItemId();

            if (itemID == R.id.settings){
                return false;
            } else if (itemID == R.id.schedule) {
                startActivity(new Intent(getApplicationContext(), ScheduleActivity.class));
                finish();

            } else { //home
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
            return true;
        });
    }
}
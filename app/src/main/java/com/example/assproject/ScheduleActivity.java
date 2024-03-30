package com.example.assproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.schedule);

        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            int itemID = menuItem.getItemId();

            if (itemID == R.id.settings){
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                finish();
            } else if (itemID == R.id.schedule) {
                return false;

            } else { //home
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
            return true;
        });
    }
}
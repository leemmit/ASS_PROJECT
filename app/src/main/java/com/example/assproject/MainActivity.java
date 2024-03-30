package com.example.assproject;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.assproject.Adapter.ToDoAdapter;
import com.example.assproject.Model.ToDoModel;
import com.example.assproject.Utils.DataBaseHelper;
import com.example.assproject.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.assproject.R.id.home;

public class MainActivity extends AppCompatActivity implements OnDialogCloseListener{

    private RecyclerView mRecycleView;
    private FloatingActionButton fab;
    private DataBaseHelper myDB;
    private List<ToDoModel> mList;
    private ToDoAdapter adapter;
    ActivityMainBinding binding;
    private View home;
    private View profile;
    private View settings;
    private Menu menu;


    @SuppressLint({"NonConstantResourceId", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

        replaceFragment(new Fragment());

        //home = (View) menu.getItem(R.id.home);

//        binding.BottomNavView.setOnItemSelectedListener(menuItem -> {
//            switch (menuItem.getItemId()){
//                case R.id.profile:
//                    replaceFragment(new Fragment());
//                    break;
//
//            }
//
//            return true;
//        });


//        profile = findViewById(R.id.profile);
//        settings = findViewById(R.id.settings);

//        binding.BottomNavView.setOnItemSelectedListener(item -> {
//            switch (item.getItemId()){
//                case R.:
//
//                    break;
//                case profile:
//                    break;
//                case settings:
//                    break;
//
//            }
//            return true;
//        });

        mRecycleView = findViewById(R.id.recycleview);
        fab = findViewById(R.id.fab);
        myDB = new DataBaseHelper(MainActivity.this);
        mList = new ArrayList<>();
        adapter = new ToDoAdapter(myDB, MainActivity.this);

        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(adapter);

        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(mRecycleView);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);
        adapter.notifyDataSetChanged();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
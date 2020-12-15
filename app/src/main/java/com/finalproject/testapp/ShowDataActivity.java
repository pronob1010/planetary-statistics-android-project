package com.finalproject.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ShowDataActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter adapter;
    List<User> userList;
    DatabaseHelp databaseHelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        databaseHelp = new DatabaseHelp(this);
        recyclerView = findViewById(R.id.dataRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();
        adapter = new UserAdapter(userList);
        recyclerView.setAdapter(adapter);

        Cursor cursor = databaseHelp.showData();

        while (cursor.moveToNext()){

            int ID = cursor.getInt(cursor.getColumnIndex(databaseHelp.COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(databaseHelp.COL_NAME));
            String distance = cursor.getString(cursor.getColumnIndex(databaseHelp.COL_DISTANCE));
            String spin = cursor.getString(cursor.getColumnIndex(databaseHelp.COL_SPIN));
            String satellite = cursor.getString(cursor.getColumnIndex(databaseHelp.COL_SATELLITE));

            userList.add(new User(ID,name,distance,spin,satellite));
            adapter.notifyDataSetChanged();
        }

    }
}
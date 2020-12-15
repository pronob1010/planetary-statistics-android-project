package com.finalproject.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();


    }

    public void Home(View view) {

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();

    }

    public void Update_and_delete(View view) {

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new UpdateFragment()).commit();

    }


    public void delete(View view) {

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new DeleteFragment()).commit();

    }
}

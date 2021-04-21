package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.testapp.DI.ServiceLocator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServiceLocator.init(getApplicationContext());
    }
}
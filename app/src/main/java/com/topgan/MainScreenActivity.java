package com.topgan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.topgan.Database.DatabaseHandler;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        DatabaseHandler.getTest();

    }
}

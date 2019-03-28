package com.topgan.ChildDetailsScreen;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.topgan.ChildDetailsScreen.ui.childdetails.ChildDetailsFragment;
import com.topgan.R;

public class ChildDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_details_activity);
        Intent intent = getIntent();

        String childId = intent.getStringExtra("childId");
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ChildDetailsFragment.newInstance(childId))
                    .commitNow();
        }
    }
}

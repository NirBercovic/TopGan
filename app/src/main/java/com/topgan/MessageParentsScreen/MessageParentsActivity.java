package com.topgan.MessageParentsScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.topgan.ItemClickListener;
import com.topgan.R;

import java.util.ArrayList;

public class MessageParentsActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Reminder> reminderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_parents);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        Reminder re0 = new Reminder("להביא בגדי החלפה",R.drawable.reminder0);
        Reminder re1 = new Reminder("נגמרו החיתולים",R.drawable.reminder1);
        Reminder re2 = new Reminder("להביא בקבוק חלב",R.drawable.reminder2);
        Reminder re3 = new Reminder("להביא סדין ביום ראשון",R.drawable.reminder3);
        Reminder re4 = new Reminder("המוצץ נעלם",R.drawable.reminder4);
        Reminder re5 = new Reminder("יש להביא מגבונים",R.drawable.reminder5);
        Reminder re6 = new Reminder("הילד הרביץ",R.drawable.reminder6);
        Reminder re7 = new Reminder("הילד חולה",R.drawable.reminder7);
        Reminder re8 = new Reminder("הילד אבא של שבת",R.drawable.reminder8);
        /*Reminder re9 = new Reminder("הילד ננשך",9);
        Reminder re10 = new Reminder("הילד לא הגיע היום",10);
        Reminder re11 = new Reminder("התנהג יפה ",11);*/

        reminderList = new ArrayList<>();
        reminderList.add(re0);
        reminderList.add(re1);
        reminderList.add(re2);
        reminderList.add(re3);
        reminderList.add(re4);
        reminderList.add(re5);
        reminderList.add(re6);
        reminderList.add(re7);
        reminderList.add(re8);
        /*reminderList.add(re9);
        reminderList.add(re10);
        reminderList.add(re11);*/
        
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ReminderAdapter(reminderList, MessageParentsActivity.this,MessageParentsActivity.this );
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View view, int position) {
        Toast.makeText(MessageParentsActivity.this, "onClick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(View view, int position) {
        Toast.makeText(MessageParentsActivity.this, "onLongClick", Toast.LENGTH_SHORT).show();


    }
}

package com.topgan.MessageParentsScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.topgan.R;

import java.util.ArrayList;

public class MessageParentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Reminder> reminderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_parents);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        Reminder re0 = new Reminder("להביא בגדי החלפה",R.drawable.reminder0);
        Reminder re1 = new Reminder("נגמרו החיתולים",R.drawable.reminder1);
        /*Reminder re2 = new Reminder("להביא חלב",2);
        Reminder re3 = new Reminder("להביא סדין חדש ביום ראשון",3);
        Reminder re4 = new Reminder("המוצץ נעלם",4);
        Reminder re5 = new Reminder("יש להביא מגבונים",5);
        Reminder re6 = new Reminder("הילד הרביץ",6);
        Reminder re7 = new Reminder("הילד חולה",7);
        Reminder re8 = new Reminder("הילד אבא של שבת",8);
        Reminder re9 = new Reminder("הילד ננשך",9);
        Reminder re10 = new Reminder("הילד לא הגיע היום",10);
        Reminder re11 = new Reminder("התנהג יפה ",11);*/

        reminderList = new ArrayList<>();
        reminderList.add(re0);
        reminderList.add(re1);
      /*  reminderList.add(re2);
        reminderList.add(re3);
        reminderList.add(re4);
        reminderList.add(re5);
        reminderList.add(re6);
        reminderList.add(re7);
        reminderList.add(re8);
        reminderList.add(re9);
        reminderList.add(re10);
        reminderList.add(re11);*/


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ReminderAdapter(reminderList, MessageParentsActivity.this);
        recyclerView.setAdapter(mAdapter);
    }
}

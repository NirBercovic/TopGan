package com.topgan.MessageParentsScreen;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.topgan.Database.DatabaseHandler;
import com.topgan.ItemClickListener;
import com.topgan.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MessageParentsActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView;
    private ReminderAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ImageButton ibSend;
    private EditText etMessage;

    ArrayList<Reminder> reminderList;
    ArrayList<String> ids;

    boolean textEnter = false;

    private static List<Reminder> remidersSelectedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_parents);

        Intent intent = getIntent();
        ids = intent.getStringArrayListExtra("CHILD_IDS");

        //Toast.makeText(MessageParentsActivity.this,"ids size = "+ids.size() , Toast.LENGTH_LONG).show();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        ibSend = findViewById(R.id.ibSend);
        etMessage = findViewById(R.id.etMessage);

        ibSend.setVisibility(View.INVISIBLE);
        ibSend.setRotation(180);

       /* DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);*/

        Reminder re0 = new Reminder("בגדי החלפה", R.drawable.reminder0);
        Reminder re1 = new Reminder("חיתולים", R.drawable.reminder1);
        Reminder re2 = new Reminder(" בקבוק חלב", R.drawable.reminder2);
        Reminder re3 = new Reminder("סדין", R.drawable.reminder3);
        Reminder re4 = new Reminder("מוצץ", R.drawable.reminder4);
        Reminder re5 = new Reminder(" מגבונים", R.drawable.reminder5);
        Reminder re6 = new Reminder("בקבוק מים", R.drawable.reminder6);
        Reminder re7 = new Reminder("משחת החתלה", R.drawable.reminder7);
        Reminder re8 = new Reminder("מקבלי שבת", R.drawable.reminder8);
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
        mAdapter = new ReminderAdapter(reminderList, MessageParentsActivity.this, MessageParentsActivity.this);
        recyclerView.setAdapter(mAdapter);

        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ibSend.setVisibility(View.VISIBLE);
                textEnter = true;
            }
        });

    }

    @Override
    public void onClick(View view, int position) {
        //Toast.makeText(MessageParentsActivity.this, "ההודעות נשלחו", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLongClick(View view, int position) {
        ibSend.setVisibility(View   .VISIBLE);
        //Toast.makeText(MessageParentsActivity.this, "onLongClick", Toast.LENGTH_SHORT).show();
    }

    public void SendClicked(View v) {
        remidersSelectedList = mAdapter.getReminderSelected();

        if (textEnter == true) {
            Toast.makeText(MessageParentsActivity.this, "  הודעה כתובה נשלחה בהצלחה " , Toast.LENGTH_LONG).show();
            finish();
        } else {


            //ArrayList<RemindMessage> remind = new ArrayList<>();
            Map<String, Object> remind = new HashMap<>();
            FirebaseFirestore db = DatabaseHandler.getInstance().getDb();

            int id = 0;
            for (int i = 0; i < remidersSelectedList.size(); i++) {
                for (int j = 0; j < ids.size(); j++) {
                    Log.e("Debug", " i = " + i + " ,title =" + remidersSelectedList.get(i).getReminderTitle() + " j: " + j + ", id: " + ids.get(j));
                    //final RemindMessage mRemindMessage = new RemindMessage(remidersSelectedList.get(i).getReminderTitle(),ids.get(j));
                    //remind.add(mRemindMessage);
                    remind.put("child", ids.get(j));
                    remind.put("title", remidersSelectedList.get(i).getReminderTitle());
                    id++;
                    db.collection("Reminders").document("ID" + id)
                            .set(remind)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("Debug", "DocumentSnapshot successfully written!");
                                    //Log.d("Debug", mRemindMessage.childId+ " "+mRemindMessage.reminderTitle);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("Debug", "Error writing document", e);
                                }
                            });
                }
            }

            Toast.makeText(MessageParentsActivity.this, remidersSelectedList.size() * ids.size() + " הודעות נשלחו בהצלחה ", Toast.LENGTH_LONG).show();
            finish();

        }

    }

}






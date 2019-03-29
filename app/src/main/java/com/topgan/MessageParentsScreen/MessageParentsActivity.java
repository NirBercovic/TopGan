package com.topgan.MessageParentsScreen;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.topgan.ItemClickListener;
import com.topgan.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MessageParentsActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView;
    private ReminderAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ImageButton btnSend;
    private EditText etMessage;

    ArrayList<Reminder> reminderList;
    ArrayList<String> ids;

    private static List<Reminder> remidersSelectedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_parents);

        Intent intent = getIntent();
        ids = intent.getStringArrayListExtra("CHILD_IDS");

        Toast.makeText(MessageParentsActivity.this,"ids size = "+ids.size() , Toast.LENGTH_LONG).show();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        btnSend = findViewById(R.id.btnSend);
        etMessage = findViewById(R.id.etMessage);

        btnSend.setVisibility(View.INVISIBLE);

       /* DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);*/

        Reminder re0 = new Reminder("להביא בגדי החלפה", R.drawable.reminder0);
        Reminder re1 = new Reminder("נגמרו החיתולים", R.drawable.reminder1);
        Reminder re2 = new Reminder("להביא בקבוק חלב", R.drawable.reminder2);
        Reminder re3 = new Reminder("להביא סדין ביום ראשון", R.drawable.reminder3);
        Reminder re4 = new Reminder("המוצץ נעלם", R.drawable.reminder4);
        Reminder re5 = new Reminder("יש להביא מגבונים", R.drawable.reminder5);
        Reminder re6 = new Reminder("הילד הרביץ", R.drawable.reminder6);
        Reminder re7 = new Reminder("הילד חולה", R.drawable.reminder7);
        Reminder re8 = new Reminder("הילד אבא של שבת", R.drawable.reminder8);
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
                btnSend.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onClick(View view, int position) {
        //Toast.makeText(MessageParentsActivity.this, "ההודעות נשלחו", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLongClick(View view, int position) {
        btnSend.setVisibility(View   .VISIBLE);
        //Toast.makeText(MessageParentsActivity.this, "onLongClick", Toast.LENGTH_SHORT).show();
    }

    public void SendClicked(View v) {
        remidersSelectedList = mAdapter.getReminderSelected();


        for (int i = 0; i < remidersSelectedList.size(); i++) {
            for (int j = 0; i < ids.size(); i++) {
                // ??
            }
        }

        Toast.makeText(MessageParentsActivity.this,remidersSelectedList.size()*ids.size()+ "הודעות נשלחו " , Toast.LENGTH_LONG).show();
        finish();

           /* try {
                String text = "הודעה לילד מהגננת" + "\r\n" +
                        remidersSelectedList.get(i).getReminderTitle()+ "\r\n" +
                        "נשלח באמצעות אפליקציית טופ-גן " ;
                String toNumber = "972542331750";

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
                startActivity(intent);
            }
            catch (Exception e){
                e.printStackTrace();
            } */
    }

}






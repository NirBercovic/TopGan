package com.topgan;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.topgan.CommonData.MessageArray;
import com.topgan.CommonData.MessageItem;

import com.topgan.ChildDetailsScreen.ChildDetailsActivity;
import com.topgan.Database.Callback;
import com.topgan.Database.DatabaseHandler;
import com.topgan.MessageParentsScreen.MessageParentsActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainScreenActivity extends AppCompatActivity {

    ArrayList<MessageItem>  m_dataSources;
    MessageBaseAdapter      m_adapter;
    private RecyclerView    m_recyclerView;
    private RecyclerView.LayoutManager m_layoutManager;
    private MainScreenActivity         m_context;
    public static Set<String> m_selectedIds = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_context = this;
        setContentView(R.layout.activity_main_screen);

        Toolbar m_mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(m_mainToolbar);

        m_recyclerView = findViewById(R.id.rv_messages);
        m_layoutManager = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(m_layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        m_recyclerView.addItemDecoration(dividerItemDecoration);

        fetchMessages();
        //DatabaseHandler.getTest();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId)
        {
            case R.id.select_all:
                Toast.makeText(this, "selectAll", Toast.LENGTH_SHORT).show();

                break;

            case R.id.send_notifications:
                Toast.makeText(this, "sendNotifications", Toast.LENGTH_SHORT).show();

                Intent messageParentsIntent = (new Intent(this, MessageParentsActivity.class));
                String[] objects = new String[m_selectedIds.size()];
                m_selectedIds.toArray(objects);
                final ArrayList<String> list = new ArrayList<>(Arrays.asList(objects));
                messageParentsIntent.putStringArrayListExtra("CHILD_IDS",list);
                startActivity(messageParentsIntent);

                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchMessages() {

        FirebaseFirestore db = DatabaseHandler.getInstance().getDb();
        DatabaseHandler.getInstance().getDocData(db.collection("Messages").document("chat_mock"), MessageArray.class, new Callback<MessageArray>() {
            @Override
            public void onSuccess(MessageArray messages) {
                m_dataSources = messages.getChats();
                if (m_dataSources != null) {
                    if (m_adapter == null) {
                        m_adapter = new MessageBaseAdapter(m_context, m_dataSources);
                        m_recyclerView.setAdapter(m_adapter);
                    } else {
                        m_adapter.setItems(m_dataSources);
                    }
                }
            }
        });
    }

}

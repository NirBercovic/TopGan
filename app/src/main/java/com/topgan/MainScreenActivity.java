package com.topgan;

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
import com.topgan.CommonData.MessageItem;
import com.topgan.Database.DatabaseHandler;

import java.util.ArrayList;

public class MainScreenActivity extends AppCompatActivity {

    ArrayList<MessageItem>  m_dataSources;
    MessageBaseAdapter      m_adapter;
    private RecyclerView    m_recyclerView;
    private RecyclerView.LayoutManager m_layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

            default:
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchMessages() {
        m_dataSources = loadMessages();

        if (m_dataSources != null) {
            if (m_adapter == null) {
                m_adapter = new MessageBaseAdapter(this, m_dataSources);
                m_recyclerView.setAdapter(m_adapter);
            } else {
                m_adapter.setItems(m_dataSources);
            }
        }
    }


    private ArrayList<MessageItem> loadMessages() {

        ArrayList<MessageItem> messages = new ArrayList<>(3);

        MessageItem message1 = new MessageItem();
        MessageItem message2 = new MessageItem();
        MessageItem message3 = new MessageItem();
        MessageItem message4 = new MessageItem();
        MessageItem message5 = new MessageItem();

        message1.setPrivateName("Nir");
        message1.setLastName("Bercovitz");
        message1.setLastMessage("Hey, what's up?");
        message1.setId("test");

        message2.setPrivateName("Roee");
        message2.setLastName("Greenberg");
        message2.setLastMessage("I forgot my coat");
        message2.setId("feoije43");

        message3.setPrivateName("Snir");
        message3.setLastName("Ozery");
        message3.setLastMessage("Hey, are you there?");
        message3.setId("fko409f09");

        message4.setPrivateName("Yael");
        message4.setLastName("Shavit");
        message4.setLastMessage("Good night");
        message4.setId("fko409f09");

        message5.setPrivateName("Avishai");
        message5.setLastName("Peretz");
        message5.setLastMessage("Good morning all");
        message5.setId("gj849jg4jg");

        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);
        messages.add(message5);

        return messages;
    }

}

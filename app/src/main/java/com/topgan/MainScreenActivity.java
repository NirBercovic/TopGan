package com.topgan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

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

        m_recyclerView = findViewById(R.id.rv_messages);
        m_layoutManager = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(m_layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        m_recyclerView.addItemDecoration(dividerItemDecoration);

        fetchMessages();
        //DatabaseHandler.getTest();
    }

    private void fetchMessages() {
        m_dataSources = loadMovies();

        if (m_dataSources != null) {
            if (m_adapter == null) {
                m_adapter = new MessageBaseAdapter(this, m_dataSources);
                m_recyclerView.setAdapter(m_adapter);
            } else {
                m_adapter.setItems(m_dataSources);
            }
        }
    }

    private ArrayList<MessageItem> loadMovies() {

        ArrayList<MessageItem> messages = new ArrayList<>(3);

        MessageItem message1 = new MessageItem();
        MessageItem message2 = new MessageItem();
        MessageItem message3 = new MessageItem();

        message1.setPrivateName("Nir");
        message1.setLastName("Bercovitz");
        message1.setLastMessage("Hey, what's up?");

        message2.setPrivateName("Roee");
        message2.setLastName("Greenberg");
        message2.setLastMessage("I forgot my coat");

        message3.setPrivateName("Snir");
        message3.setLastName("Ozeri");
        message3.setLastMessage("Hey, are you there?");

        messages.add(message1);
        messages.add(message2);
        messages.add(message3);

        return messages;
    }

}

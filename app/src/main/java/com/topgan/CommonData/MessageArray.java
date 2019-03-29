package com.topgan.CommonData;

import java.util.ArrayList;

public class MessageArray {
    ArrayList<MessageItem> chats;

    public MessageArray() {
    }

    public ArrayList<MessageItem> getChats() {
        return chats;
    }

    public void setChats(ArrayList<MessageItem> messageItems) {
        this.chats = messageItems;
    }
}

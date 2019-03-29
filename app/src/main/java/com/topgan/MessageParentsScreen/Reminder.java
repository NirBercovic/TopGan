package com.topgan.MessageParentsScreen;

public class Reminder {

    String reminderTitle;
    int photoId;


    public Reminder(String reminderTitle, int photoId) {
        this.reminderTitle = reminderTitle;
        this.photoId = photoId;

    }

    public String getReminderTitle() {
        return reminderTitle;
    }

    public void setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}

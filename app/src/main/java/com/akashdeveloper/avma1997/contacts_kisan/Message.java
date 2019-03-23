package com.akashdeveloper.avma1997.contacts_kisan;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName="message_table")
public class Message {
    @PrimaryKey(autoGenerate=true)
        private int id;
        private String date;
        private String firstname;
        private String lastname;
        private String phoneNo;

    public Message(String date, String firstname, String lastname, String phoneNo) {
        this.date = date;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNo = phoneNo;
    }

    public String getDate() {
        return date;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }



}

package com.akashdeveloper.avma1997.contacts_kisan.POJO;

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
        private String otp;

    public Message(String date, String firstname, String lastname, String phoneNo,String otp) {
        this.date = date;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNo = phoneNo;
        this.otp=otp;
    }

    public String getOtp() {
        return otp;
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

package com.akashdeveloper.avma1997.contacts_kisan;

import com.akashdeveloper.avma1997.contacts_kisan.POJO.Contact;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContactList {

        @SerializedName("Contact")
        private ArrayList<Contact> contactList;

        public ArrayList<Contact> getContactArrayList() {
            return contactList;
        }

        public void setNoticeArrayList(ArrayList<Contact> contactList) {
            this.contactList = contactList;
        }
}

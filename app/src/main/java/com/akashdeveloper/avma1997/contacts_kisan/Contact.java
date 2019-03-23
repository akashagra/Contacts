package com.akashdeveloper.avma1997.contacts_kisan;

import com.google.gson.annotations.SerializedName;

public class Contact {

        @SerializedName("firstname")
        private String firstname;
        @SerializedName("lastname")
        private String lastname;
        @SerializedName("phoneno")
        private String phoneno;

        public Contact(String firstname, String lastname, String phoneno) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.phoneno = phoneno;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getPhoneno() {
            return phoneno;
        }

        public void setPhoneno(String phoneno) {
            this.phoneno = phoneno;
        }
    }


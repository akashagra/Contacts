package com.akashdeveloper.avma1997.contacts_kisan.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.akashdeveloper.avma1997.contacts_kisan.Fragments.ContactFragment;
import com.akashdeveloper.avma1997.contacts_kisan.R;

public class ContactInfoActivity extends AppCompatActivity {
    String firstName;
    String lastName;
    String phoneNo;
    TextView nameTextView,phoneNoTextView;
    Button sendButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        setTitle("Contact Information");
       // Toolbar toolbar =  findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

         Intent intent = getIntent();
        firstName = intent.getStringExtra(ContactFragment.EXTRA_FIRSTNAME);
        lastName = intent.getStringExtra(ContactFragment.EXTRA_LASTNAME);
        phoneNo = intent.getStringExtra(ContactFragment.EXTRA_PHONE_NO);
        nameTextView=findViewById(R.id.name_tv);
        phoneNoTextView=findViewById(R.id.phone_tv);

        nameTextView.setText(firstName+ "  " + lastName);
        phoneNoTextView.setText(phoneNo);
        sendButton=findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactInfoActivity.this, SendingActivity.class);
                intent.putExtra(ContactFragment.EXTRA_FIRSTNAME,firstName);
                intent.putExtra(ContactFragment.EXTRA_LASTNAME,lastName);
                intent.putExtra(ContactFragment.EXTRA_PHONE_NO,phoneNo);
                startActivity(intent);

            }
        });








    }
}

package com.akashdeveloper.avma1997.contacts_kisan.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akashdeveloper.avma1997.contacts_kisan.Fragments.ContactFragment;
import com.akashdeveloper.avma1997.contacts_kisan.MVVM.MessageViewModel;
import com.akashdeveloper.avma1997.contacts_kisan.POJO.Contact;
import com.akashdeveloper.avma1997.contacts_kisan.POJO.Message;
import com.akashdeveloper.avma1997.contacts_kisan.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class SendingActivity extends AppCompatActivity {
    String firstName;
    String lastName;
    String phoneNo;
    Contact mContact;
    MessageViewModel messageViewModel;

    private final static String TAG = "SendMessage";
    // Though its not a good practice to insert id and auth token in an app and hence this sample app is not for production
    private final static String ACCOUNT_SID = "AC07eb061d943be3faebe89a8904537957"; // Twilio account sid
    private final static String AUTH_TOKEN = "d13c9abfdd2b95bd42686cdc6346c3dc";  // Twilio auth token
    ProgressBar mProgressBar; // progress bar to show progress on message sending
    private Button mSendBtn;
    private static final int MIC_PERMISSION_REQUEST_CODE = 1;
    TextView sendingOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending);
        sendingOTP = findViewById(R.id.otp_tv);
        mSendBtn = findViewById(R.id.otp_send_button);
        Intent intent = getIntent();
        firstName = intent.getStringExtra(ContactFragment.EXTRA_FIRSTNAME);
        lastName = intent.getStringExtra(ContactFragment.EXTRA_LASTNAME);
        phoneNo = intent.getStringExtra(ContactFragment.EXTRA_PHONE_NO);
        mContact = new Contact(firstName, lastName, phoneNo);
        messageViewModel= ViewModelProviders.of(this).get(MessageViewModel.class);

        randomNo();
        //obtainPermission();

        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(sendingOTP.getText().toString());

            }
        });

    }

//    private void obtainPermission() {
//        ActivityCompat.requestPermissions(this,
//                new String[]{Manifest.permission.RECORD_AUDIO}, MIC_PERMISSION_REQUEST_CODE);
//    }

    private void randomNo() {
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        String no = n+ " ";
        sendingOTP.setText("Hi. Your OTP is: " + no);

    }

    private void sendMessage(String message) {

        final String body = message;
        String from = "+12013714801";
        //tested the app on my own number
        //String to ="+919718980265";

        String to = mContact.getPhoneno().trim();

        // I have added Kisan network 's number in my api , but twilio allows only numbers who are verified to receive messages.
        Log.d("phone_no", to);
        String base64EncodedCredentials = "Basic " + Base64.encodeToString(
                (ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes(), Base64.NO_WRAP
        );

        Map<String, String> data = new HashMap<>();
        data.put("From", from);
        data.put("To", to);
        data.put("Body", body);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.twilio.com/2010-04-01/")
                .build();
        TwilioApi api = retrofit.create(TwilioApi.class);


        api.sendMessage(ACCOUNT_SID, base64EncodedCredentials, data).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.wtf("URL Twilio", call.request().url() + "");
                if (response.isSuccessful()) {
                    Log.d("TAG", "onResponse->success");
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    Log.i("date",formattedDate);
                    Log.i("name",mContact.getFirstname());
                    Message message=new Message(formattedDate,mContact.getFirstname(),mContact.getLastname(),mContact.getPhoneno(),body);
                    messageViewModel.insert(message);
                    Toast.makeText(SendingActivity.this, " Congrats , you would soon receive an otp", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SendingActivity.this, MainActivity.class);
                    startActivity(intent);


                } else {
                    Log.d("TAG", "onResponse->failure");

                    Toast.makeText(SendingActivity.this, " There is a error , the number might be registered on a DND , or incorrect", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG", "onFailure");
            }
        });
    }

    interface TwilioApi {
        @FormUrlEncoded
        @POST("Accounts/{ACCOUNT_SID}/Messages")
        Call<ResponseBody> sendMessage(
                @Path("ACCOUNT_SID") String accountSId,
                @Header("Authorization") String signature,
                @FieldMap Map<String, String> metadata
        );
    }
}



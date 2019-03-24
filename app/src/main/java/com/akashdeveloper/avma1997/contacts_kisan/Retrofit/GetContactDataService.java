package com.akashdeveloper.avma1997.contacts_kisan.Retrofit;

import com.akashdeveloper.avma1997.contacts_kisan.POJO.ContactList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetContactDataService {

        @GET("bins/18jgfi")
        Call<ContactList> getContactData();
    }


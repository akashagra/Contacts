package com.akashdeveloper.avma1997.contacts_kisan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.akashdeveloper.avma1997.contacts_kisan.POJO.Contact;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactFragment extends Fragment {
    RecyclerView recyclerView;
    ContactAdapter adapter;
    ArrayList<Contact> contacts;
    DividerItemDecoration decoration;
    public static final int ADD_NOTE_REQUEST=1;
    public static final String EXTRA_FIRSTNAME = "com.akashdeveloper.avma1997.architectureexample.EXTRA_FIRSTNAME";
    public static final String EXTRA_LASTNAME = "com.akashdeveloper.avma1997.architectureexample.EXTRA_LASTNAME";
    public static final String EXTRA_PHONE_NO = "com.akashdeveloper.avma1997.architectureexample.EXTRA_PHONE_NO";

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_contact_list, container, false);
        recyclerView = v.findViewById(R.id.recycler_view_contacts);
        contacts=new ArrayList<>();
        adapter = new ContactAdapter(getContext(), contacts, new ContactAdapter.ContactClickListener() {
            public void onItemClick(View view, int position) {
                //Toast.makeText(getActivity(),position+"",Toast.LENGTH_SHORT).show();
                Log.i("pos",position+"");
                Contact contact= contacts.get(position);
                Intent intent= new Intent(getActivity(), ContactInfoActivity.class);
                intent.putExtra(EXTRA_FIRSTNAME,contact.getFirstname());
                intent.putExtra(EXTRA_LASTNAME,contact.getLastname());
                intent.putExtra(EXTRA_PHONE_NO,contact.getPhoneno());
                startActivity(intent);
            }



        });


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);


        /** Create handle for the RetrofitInstance interface*/
        GetContactDataService service = RetrofitInstance.getRetrofitInstance().create(GetContactDataService.class);

        /** Call the method with parameter in the interface to get the contact data*/
        Call<ContactList> call = service.getContactData();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                onDownloadComplete(response.body().getContactArrayList());

            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {

                Toast.makeText(getActivity(), "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;


    }

    private void onDownloadComplete(ArrayList<Contact> contactArrayList) {

        this.contacts.clear();
        this.contacts.addAll(contactArrayList);
        adapter.notifyDataSetChanged();

    }





    }


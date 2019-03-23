package com.akashdeveloper.avma1997.contacts_kisan.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
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

import com.akashdeveloper.avma1997.contacts_kisan.MessageAdapter;
import com.akashdeveloper.avma1997.contacts_kisan.MessageViewModel;
import com.akashdeveloper.avma1997.contacts_kisan.POJO.Message;
import com.akashdeveloper.avma1997.contacts_kisan.R;

import java.util.ArrayList;
import java.util.List;

public class SentMessageFragment extends Fragment {
    RecyclerView recyclerView;
    MessageAdapter adapter;
    ArrayList<Message> messages;
    DividerItemDecoration decoration;
    MessageViewModel messageViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_message, container, false);
        recyclerView = v.findViewById(R.id.recyclerview_messages);
        messages = new ArrayList<>();
        adapter = new MessageAdapter(getContext(),messages);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        fetchDataFromDatabase();
        return v;

    }

    public void fetchDataFromDatabase() {
    messageViewModel= ViewModelProviders.of(this).get(MessageViewModel.class);
    messageViewModel.getAllMessages().observe(this, new Observer<List<Message>>() {
        @Override
        public void onChanged(@Nullable final List<Message> messageArrayList) {
            // Update the cached copy of the words in the adapter.
            messages.clear();
            messages.addAll(messageArrayList);
            adapter.notifyDataSetChanged();
        }
    });
}
}

package com.akashdeveloper.avma1997.contacts_kisan;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {

    private MessageRepository mRepository;

        private LiveData<List<Message>> mAllMessages;

        public MessageViewModel (Application application) {
            super(application);
            mRepository = new MessageRepository(application);
            mAllMessages = mRepository.getAllMessages();
        }

        LiveData<List<Message>> getAllWords() { return mAllMessages; }

        public void insert(Message message) { mRepository.insert(message); }
    }


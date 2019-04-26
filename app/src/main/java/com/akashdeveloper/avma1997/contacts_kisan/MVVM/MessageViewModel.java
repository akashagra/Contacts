package com.akashdeveloper.avma1997.contacts_kisan.MVVM;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.akashdeveloper.avma1997.contacts_kisan.POJO.Message;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {

    private MessageRepository mRepository;

        private LiveData<List<Message>> mAllMessages;

        public MessageViewModel (Application application) {
            super(application);
            mRepository = new MessageRepository(application);
            mAllMessages = mRepository.getAllMessages();
        }

         public LiveData<List<Message>> getAllMessages() { return mAllMessages; }

        public void insert(Message message) { mRepository.insert(message); }
    }


package com.akashdeveloper.avma1997.contacts_kisan;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MessageRepository {

        private MessageDao messageDao;
        private LiveData<List<Message>> mAllMessages;

         public MessageRepository(Application application) {
            MessageDatabase db = MessageDatabase.getInstance(application);
            messageDao = db.messageDao();
            mAllMessages = messageDao.getAllMessages();
        }

        LiveData<List<Message>> getAllMessages() {
            return mAllMessages;
        }


        public void insert (Message message) {
            new insertAsyncTask(messageDao).execute(message);
        }

        private static class insertAsyncTask extends AsyncTask<Message, Void, Void> {

            private MessageDao mAsyncTaskDao;

            insertAsyncTask(MessageDao dao) {
                mAsyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(final Message... params) {
                mAsyncTaskDao.insert(params[0]);
                return null;
            }
        }
    }


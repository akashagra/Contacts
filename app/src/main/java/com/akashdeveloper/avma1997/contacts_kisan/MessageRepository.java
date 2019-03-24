package com.akashdeveloper.avma1997.contacts_kisan;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.akashdeveloper.avma1997.contacts_kisan.POJO.Message;

import java.util.List;

public class MessageRepository {
    // repository class is created to keep all the business logic in this class .
    // Its not a good practice to write the business logic on an activity like Main Activity

        private MessageDao messageDao;
        private LiveData<List<Message>> mAllMessages;

         public MessageRepository(Application application) {
            MessageDatabase db = MessageDatabase.getInstance(application); // creating the instance of database
            messageDao = db.messageDao();// getting the accesss ti dao object
            mAllMessages = messageDao.getAllMessages();// fetching data from room
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


package com.akashdeveloper.avma1997.contacts_kisan;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;

import com.akashdeveloper.avma1997.contacts_kisan.POJO.Message;

@Database(entities = {Message.class}, version = 1)
public abstract class MessageDatabase extends RoomDatabase {

    private static MessageDatabase instance;

    public abstract MessageDao messageDao();

    public static synchronized MessageDatabase getInstance(Context context) {
        if (instance == null) {
            Room.databaseBuilder(context.getApplicationContext(),
                    MessageDatabase.class, "message_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build();

        }
        return instance;

    }

    // anonymous classes
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            new PopulateDbAsyncTask(instance).execute();
            super.onCreate(db);
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private  MessageDao messageDao;

        private PopulateDbAsyncTask(MessageDatabase db) {
            messageDao = db.messageDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            // This can be used to insert default values as well

            return null;
        }
    }


}

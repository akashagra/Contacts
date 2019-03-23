package com.akashdeveloper.avma1997.contacts_kisan;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.provider.ContactsContract;

import java.util.List;

@Dao
public interface MessageDao {
    @Insert
    void insert(Message message);

    @Update
    void update(Message message);

    @Delete
    void delete(Message message);

    @Query("Delete FROM message_table")
    void deleteAllNotes();

    @Query("Select * from message_table ")
    LiveData<List<Message>> getAllMessages();

}
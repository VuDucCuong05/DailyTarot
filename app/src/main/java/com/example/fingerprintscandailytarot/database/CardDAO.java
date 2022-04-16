package com.example.fingerprintscandailytarot.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fingerprintscandailytarot.model.Card;

import java.util.List;

@Dao
public interface CardDAO {

    @Insert
    void insertCard(Card card);

    @Query("SELECT * FROM Card ORDER BY id DESC")
    LiveData<List<Card>> getListCard();

    @Delete
    void deleteCard(Card card);
}

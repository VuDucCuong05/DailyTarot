package com.example.fingerprintscandailytarot.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fingerprintscandailytarot.model.Card;
import com.example.fingerprintscandailytarot.model.Language;

import java.util.List;

@Dao
public interface LanguageDAO {
    @Insert
    void insertLanguage(Language language);

    @Update
    void updateLanguage(Language language);

    @Query("SELECT * FROM LANGUAGE")
    LiveData<List<Language>> getAllLanguage();
}

package com.example.fingerprintscandailytarot.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fingerprintscandailytarot.model.Card;

@Database(entities = {Card.class}, version = 1)
public abstract class CardDatabase extends RoomDatabase {

    private static final String DATABASENAME = "card.database";

    private static CardDatabase instance;

    public static synchronized CardDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), CardDatabase.class, DATABASENAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()

                    .build();
        }
        return instance;
    }

    public abstract CardDAO cardDAO();

}

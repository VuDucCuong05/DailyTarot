package com.example.fingerprintscandailytarot.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fingerprintscandailytarot.R;
import com.example.fingerprintscandailytarot.model.Card;
import com.example.fingerprintscandailytarot.model.Language;

@Database(entities = {Language.class}, version = 1)
public abstract class LanguageDatabase extends RoomDatabase {

    private static final String DATABASENAMELANGUAGE = "language.database";

    public abstract LanguageDAO languageDAO();

    private static LanguageDatabase instance;

    public static synchronized LanguageDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), LanguageDatabase.class, DATABASENAMELANGUAGE)
                    .allowMainThreadQueries()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private LanguageDAO languageDAO;

        private PopulateDbAsyncTask(LanguageDatabase db) {
            languageDAO = db.languageDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            languageDAO.insertLanguage(new Language(R.drawable.ic_america,R.string.tv_americ,true,"en"));
            languageDAO.insertLanguage(new Language(R.drawable.ic_southkorea1,R.string.tv_england,false,"en"));
            languageDAO.insertLanguage(new Language(R.drawable.ic_belgium,R.string.tv_belgium,false,"wa"));
            languageDAO.insertLanguage(new Language(R.drawable.ic_brazil,R.string.tv_brazil,false,"BR"));
            languageDAO.insertLanguage(new Language(R.drawable.ic_netherlands,R.string.tv_netherland,false,"BQ"));
            languageDAO.insertLanguage(new Language(R.drawable.ic_south_korea,R.string.tv_south_korea,false,"ko"));
            return null;
        }
    }
}

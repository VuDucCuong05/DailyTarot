package com.example.fingerprintscandailytarot.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.fingerprintscandailytarot.database.LanguageDAO;
import com.example.fingerprintscandailytarot.database.LanguageDatabase;
import com.example.fingerprintscandailytarot.model.Language;

import java.util.List;

public class LanguageRepository {
    private LanguageDAO mLanguageDAO;
    private LiveData<List<Language>> mAllLanguage;

    public LanguageRepository(Application application) {
        LanguageDatabase db = LanguageDatabase.getInstance(application);
        mLanguageDAO = db.languageDAO();
        mAllLanguage = mLanguageDAO.getAllLanguage();
    }

    public LiveData<List<Language>> getAllLanguage(){
        return mAllLanguage;
    }

    public void updateLanguage(Language language){
        new updateAsyncTask(mLanguageDAO).execute(language);
    }
    private static class updateAsyncTask extends AsyncTask<Language,Void,Void> {
        private LanguageDAO languageDAO;

        updateAsyncTask(LanguageDAO dao){
            languageDAO = dao;
        }

        @Override
        protected Void doInBackground(Language... languages) {
            languageDAO.updateLanguage(languages[0]);
            return null;
        }
    }

}

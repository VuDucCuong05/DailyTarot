package com.example.fingerprintscandailytarot.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.fingerprintscandailytarot.database.CardDAO;
import com.example.fingerprintscandailytarot.database.CardDatabase;
import com.example.fingerprintscandailytarot.model.Card;

import java.util.List;

public class CardRepository {

    private CardDAO mCardDAO;
    private LiveData<List<Card>> mAListLiveData;

    public CardRepository(Application application) {
        CardDatabase db = CardDatabase.getInstance(application);
        mCardDAO = db.cardDAO();
        mAListLiveData = mCardDAO.getListCard();
    }


    public LiveData<List<Card>> getmAListLiveData() {
        return mAListLiveData;
    }
    public void insertCard(Card card){
        new insertAsyncTask(mCardDAO).execute(card);
    }
    private static class insertAsyncTask extends AsyncTask<Card,Void,Void> {
        private CardDAO mCardDAO;

        insertAsyncTask(CardDAO dao){
            mCardDAO = dao;
        }

        @Override
        protected Void doInBackground(Card... cards) {
            mCardDAO.insertCard(cards[0]);
            return null;
        }
    }

    public void deleteCard(Card card){
        new deleteAsyncTask(mCardDAO).execute(card);
    }
    private static class deleteAsyncTask extends AsyncTask<Card,Void,Void>{
        private CardDAO mCardDAO;

        deleteAsyncTask(CardDAO dao) {
            mCardDAO = dao;
        }

        @Override
        protected Void doInBackground(Card... cards) {
            mCardDAO.deleteCard(cards[0]);
            return null;
        }
    }
}

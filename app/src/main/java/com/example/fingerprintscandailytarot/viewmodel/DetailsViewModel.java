package com.example.fingerprintscandailytarot.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fingerprintscandailytarot.model.Card;
import com.example.fingerprintscandailytarot.model.CardShow;
import com.example.fingerprintscandailytarot.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;


public class DetailsViewModel extends AndroidViewModel{

    private CardRepository mRepository;
    private LiveData<List<Card>> mLiveCard;

    private MutableLiveData<Boolean> ckInternet;

    public DetailsViewModel(Application application) {
        super(application);
        mRepository = new CardRepository(application);
        mLiveCard = mRepository.getmAListLiveData();

        ckInternet = new MutableLiveData<>();
    }

    public LiveData<List<Card>> getmLiveCard() {
        return mLiveCard;
    }

    public void insetCard(Card card){
        mRepository.insertCard(card);
    }

    public void deleteCard(Card card){
        mRepository.deleteCard(card);
    }

    public List<CardShow> getList3Detail(Context context, List<Card> listDataBasse){
        List<Card> list1 = listDataBasse;
        List<CardShow> list = new ArrayList<>();
        if(list1.size() !=0){
            Card card = list1.get(0);

            list = new ArrayList<>();

            list.add(new CardShow("LOVE", card.getContentLove(),card.getTimeLoading()));
            list.add(new CardShow("HEALTH", card.getContentHealth(),card.getTimeLoading()));
            list.add(new CardShow("CAREER", card.getContentCareer(),card.getTimeLoading()));
            Log.e("prox",""+list1.size());
        }
        return list;


    }


    public LiveData<Boolean> getCkInternet() {
        return ckInternet;
    }

    public void setCkInternet(boolean ck) {
       ckInternet.postValue(ck);
    }
}

package com.example.fingerprintscandailytarot.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fingerprintscandailytarot.database.Api.Methods;
import com.example.fingerprintscandailytarot.database.Api.RetrofitClient;
import com.example.fingerprintscandailytarot.database.CardDatabase;
import com.example.fingerprintscandailytarot.model.Card;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivityViewModel extends ViewModel {

    private MutableLiveData<Boolean> mCkLoadCallApi;
    private MutableLiveData<Integer> mCountClick;


    private MutableLiveData<Boolean> mCkLoadCarrer;
    private MutableLiveData<Boolean> mCkLoadHealth;

    private List<Card> mListCardCallSuccsess;

    private List<String> mListStrings;


    public SplashActivityViewModel() {
        mCountClick = new MutableLiveData<>();

        mCkLoadCallApi = new MutableLiveData<>();
        mCkLoadCarrer = new MutableLiveData<>();
        mCkLoadHealth = new MutableLiveData<>();

        mListCardCallSuccsess = new ArrayList<>();
        mListStrings = new ArrayList<>();

    }

    public MutableLiveData<Boolean> getCkLoadCallApi() {
        return mCkLoadCallApi;
    }

    public void setCkLoadCallApi(boolean ckLoadCallApi) {
        mCkLoadCallApi.postValue(ckLoadCallApi);
    }

    public MutableLiveData<Boolean> getmCkLoadCarrer() {
        return mCkLoadCarrer;
    }

    public void setmCkLoadCarrer(Boolean b) {
        mCkLoadCarrer.postValue(b);
    }

    public MutableLiveData<Boolean> getmCkLoadHealth() {
        return mCkLoadHealth;
    }

    public void setmCkLoadHealth(Boolean b) {
        mCkLoadHealth.postValue(b);
    }

    public MutableLiveData<Integer> getmCountClick() {
        return mCountClick;
    }

    public void setmCountClick(int count) {
        mCountClick.postValue(count);
    }

    public List<Card> getmListCardCallSuccsess() {
        return mListCardCallSuccsess;
    }

    public void setmListCardCallSuccsess(Card card) {
        mListCardCallSuccsess.add(card);
    }

    public void callApi(Context context) {
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);

        Call<Card> callCareer = methods.getCACardCareer();
        mListCardCallSuccsess.clear();
        mListStrings.clear();

        callCareer.enqueue(new Callback<Card>() {
            @Override
            public void onResponse(Call<Card> call, Response<Card> response) {
                if (response.body() != null) {
                    Card card = response.body();
                    Card card1 = new Card(card.getContentLove());

                    mListStrings.add(card.getContentLove());
                    mListCardCallSuccsess.add(card1);

                    mCkLoadCarrer.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<Card> call, Throwable t) {
                mCkLoadCallApi.setValue(false);
            }
        });

    }

    public void callApiHealth(Context context) {

        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Card> callHealth = methods.getCardHealth();
        callHealth.enqueue(new Callback<Card>() {
            @Override
            public void onResponse(Call<Card> call, Response<Card> response) {
                if (response.body() != null) {
                    Card card = response.body();

                    Card card1 = new Card(card.getContentLove());
                    mListStrings.add(card.getContentLove());
                    mListCardCallSuccsess.add(card1);

                    mCkLoadHealth.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<Card> call, Throwable t) {
                mCkLoadCallApi.setValue(false);
            }
        });
    }

    public void callApiLove(Context context) {

        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Card> call = methods.getCardLove();
        call.enqueue(new Callback<Card>() {
            @Override
            public void onResponse(Call<Card> call, Response<Card> response) {
                if (response.body() != null) {
                    Card card = response.body();

                    DateFormat df = new SimpleDateFormat("hh:mma::EEE, d MMM yyyy");
                    String time = df.format(Calendar.getInstance().getTime());

                    Card card1 = new Card(card.getContentLove());

                    mListStrings.add(card.getContentLove());
                    mListCardCallSuccsess.add(card1);


                    if(mListCardCallSuccsess.size() == 3){

                        Card card2 = new Card(mListStrings.get(0),mListStrings.get(1),mListStrings.get(2),time);
                        CardDatabase.getInstance(context).cardDAO().insertCard(card2);

                        Log.e("prox","test"+card2.toString());
                        mCkLoadCallApi.setValue(true);

                    }else{
                        mCkLoadCallApi.setValue(false);

                    }
                }
            }

            @Override
            public void onFailure(Call<Card> call, Throwable t) {
                mCkLoadCallApi.setValue(false);
            }
        });
    }



    // call list(3 card)
    // call succsess thì lưu list vào list tong ( 1thuoojc tinh thoi gian )

}

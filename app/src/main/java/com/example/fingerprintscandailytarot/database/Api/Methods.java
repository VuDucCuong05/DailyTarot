package com.example.fingerprintscandailytarot.database.Api;

import com.example.fingerprintscandailytarot.model.Card;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {


    @GET("generate?lang=en&title=LOVE")
    Call<Card> getCardLove();
    @GET("generate?lang=en&title=HEALTH")
    Call<Card> getCardHealth();
    @GET("generate?lang=en&title=CAREER")
    Call<Card> getCACardCareer();
}

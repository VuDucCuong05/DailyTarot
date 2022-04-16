package com.example.fingerprintscandailytarot.database.Api;

import android.view.Gravity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    // http://178.79.156.246:8080/generate?lang=en&title=HEALTH

    private static Retrofit retrofit;
    private static  String BASE_URL = "http://178.79.156.246:8080/";

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}

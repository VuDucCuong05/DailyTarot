package com.example.fingerprintscandailytarot.model.datalocal;

import android.content.Context;

import com.example.fingerprintscandailytarot.model.Card;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataLocalManager {

    private static final String PREF_LIST_PREDICTION = "PREF_LIST_PREDICTION";

    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context){
        instance  = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }
    public static DataLocalManager getInstance(){
        if(instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setListPrediction(List<Card> listCard){
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(listCard).getAsJsonArray();
        String strJsonArray = jsonArray.toString();
        DataLocalManager.getInstance().mySharedPreferences.putStringValues(PREF_LIST_PREDICTION,strJsonArray);
    }
    public static List<Card> getListPrediction(){
        String strJsonArray = DataLocalManager.getInstance().mySharedPreferences.getString(PREF_LIST_PREDICTION);
        List<Card> listPredicton = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJsonArray);
            JSONObject jsonObject;
            Card card;
            Gson gson = new Gson();
            for (int i=0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                card = gson.fromJson(jsonObject.toString(), Card.class);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listPredicton;
    }

}

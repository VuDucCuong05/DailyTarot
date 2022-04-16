package com.example.fingerprintscandailytarot.model.datalocal;


import com.example.fingerprintscandailytarot.model.datalocal.DataLocalManager;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}

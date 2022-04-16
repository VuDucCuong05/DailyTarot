package com.example.fingerprintscandailytarot.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fingerprintscandailytarot.R;
import com.example.fingerprintscandailytarot.broadcastreceiver.InternetBroadcastReceiver;
import com.example.fingerprintscandailytarot.locale.LocaleHelper;
import com.example.fingerprintscandailytarot.view.SettingActivity;
import com.example.fingerprintscandailytarot.view.SplashActivity;

public abstract class BaseAcitivity extends AppCompatActivity {

    protected InternetBroadcastReceiver mInternetBroadcastReceiver;
    private LocaleHelper localeHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mInternetBroadcastReceiver = new InternetBroadcastReceiver();
        localeHelper = new LocaleHelper(this);
        localeHelper.updateResources(localeHelper.getLang());

        initView();
        initData();
        initEvent();


    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();

    public void getActivity(Class t) {
        Intent intent = new Intent(this, t);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mInternetBroadcastReceiver, intentFilter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mInternetBroadcastReceiver);
    }


    protected Dialog mDialogLoading;

    public void showDialogLoading(Context context) {


        if (mDialogLoading != null) {
            mDialogLoading.dismiss();
        }

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loadding);
        mDialogLoading = dialog;
        mDialogLoading.setCancelable(false);
        mDialogLoading.show();
    }

    public void hideDialogLoading(Context context) {
        if (mDialogLoading != null) {
            mDialogLoading.dismiss();
        }
    }


    public boolean isNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            if (network == null) {
                return false;
            }
            return true;
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo == null) {
                return false;
            }
            return true;
        }
    }

}

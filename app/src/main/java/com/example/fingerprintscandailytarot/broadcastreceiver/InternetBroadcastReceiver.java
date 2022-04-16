package com.example.fingerprintscandailytarot.broadcastreceiver;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import com.example.fingerprintscandailytarot.R;

import java.net.NetworkInterface;

public class InternetBroadcastReceiver extends BroadcastReceiver {

    private Dialog mDialogLoading;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (isNetWorkAvailable(context)) {
//                if (mDialogLoading != null) {
//                    mDialogLoading.dismiss();
//                }
            } else {
//                if (mDialogLoading != null) {
//                    mDialogLoading.dismiss();
//                }
//                Dialog dialog = new Dialog(context);
//                dialog.setContentView(R.layout.dialog_loadding);
//
//                mDialogLoading = dialog;
//                mDialogLoading.setCancelable(false);
//                mDialogLoading.show();
            }
        }
    }

    public boolean isNetWorkAvailable(Context context) {
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

//        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

    }
}

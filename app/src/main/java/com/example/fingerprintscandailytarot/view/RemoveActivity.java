package com.example.fingerprintscandailytarot.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fingerprintscandailytarot.R;
import com.example.fingerprintscandailytarot.base.BaseAcitivity;

public class RemoveActivity extends BaseAcitivity {

    private ImageView imgClose;
    private ImageView imgRemoveWeekly;
    private ImageView imgRemoveMonthly;
    private ImageView imgRemoveYearly;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_remove;
    }

    @Override
    protected void initView() {
        imgClose = findViewById(R.id.img_close_remove);
        imgRemoveWeekly = findViewById(R.id.layout);
        imgRemoveMonthly = findViewById(R.id.layout1);
        imgRemoveYearly = findViewById(R.id.layout2);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.anim_input_left,R.anim.anim_ouput_left);
            }
        });

        imgRemoveWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "layout ", Toast.LENGTH_SHORT).show();
            }
        });
        imgRemoveMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "layout 1", Toast.LENGTH_SHORT).show();
            }
        });
        imgRemoveYearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "layout 2", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
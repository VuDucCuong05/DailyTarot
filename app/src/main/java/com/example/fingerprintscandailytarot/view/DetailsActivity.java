package com.example.fingerprintscandailytarot.view;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fingerprintscandailytarot.database.Api.Methods;
import com.example.fingerprintscandailytarot.database.Api.RetrofitClient;
import com.example.fingerprintscandailytarot.database.CardDatabase;
import com.example.fingerprintscandailytarot.model.AdapterCardDetail;
import com.example.fingerprintscandailytarot.R;
import com.example.fingerprintscandailytarot.base.BaseAcitivity;
import com.example.fingerprintscandailytarot.model.Card;
import com.example.fingerprintscandailytarot.viewmodel.DetailsViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends BaseAcitivity {
    ImageView imageMeteor;
    ImageView imageMeteor1;

    ImageView imgStar;
    ImageView imgStar1;
    ImageView imgStar2;

    Animation animationUtils;
    Animation animationUtils1;
    Animation starSmall;
    Animation starSmall1;
    Animation starSmall2;
    private AdapterCardDetail adapterCardDetail;
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;

    private ConstraintLayout imgArrowDown;
    private ImageView imgSetting;
    private Button btNewPrediction;

    private DetailsViewModel detailsViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        imageMeteor = findViewById(R.id.star);
        imageMeteor1 = findViewById(R.id.star1);

        imgStar = findViewById(R.id.star_small);
        imgStar1 = findViewById(R.id.star_small1);
        imgStar2 = findViewById(R.id.star_small2);
        viewPager2 = findViewById(R.id.view_pager_prediction);
        circleIndicator3 = findViewById(R.id.ciresle_prediction);

        imgArrowDown = findViewById(R.id.ll_search);
        imgSetting = findViewById(R.id.img_setting);
        btNewPrediction = findViewById(R.id.bt_new_prediction);

        adapterCardDetail = new AdapterCardDetail(this);

        detailsViewModel = new ViewModelProvider(DetailsActivity.this).get(DetailsViewModel.class);

    }

    @Override
    protected void initData() {

        detailsViewModel.getmLiveCard().observe(this, new Observer<List<Card>>() {
            @Override
            public void onChanged(List<Card> list) {
                viewPager2.setAdapter(adapterCardDetail);
                adapterCardDetail.setListCard(detailsViewModel.getList3Detail(DetailsActivity.this, list));
                circleIndicator3.setViewPager(viewPager2);
            }
        });

    }

    @Override
    protected void initEvent() {

        animationUtils = AnimationUtils.loadAnimation(this, R.anim.anim_star);
        animationUtils1 = AnimationUtils.loadAnimation(this, R.anim.anim_star1);
        starSmall = AnimationUtils.loadAnimation(this, R.anim.star_small);
        starSmall1 = AnimationUtils.loadAnimation(this, R.anim.star_small1);
        starSmall2 = AnimationUtils.loadAnimation(this, R.anim.star_small2);

        imageMeteor.setVisibility(View.VISIBLE);
        imageMeteor.startAnimation(animationUtils);
        imageMeteor1.setVisibility(View.VISIBLE);
        imageMeteor1.startAnimation(animationUtils1);
        imgStar.startAnimation(starSmall);
        imgStar1.startAnimation(starSmall1);
        imgStar2.startAnimation(starSmall2);

        imgArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, HistoryActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_down, R.anim.anim_up);
            }
        });
        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, SettingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_input_right, R.anim.anim_ouput_right);
                finish();
            }
        });

        btNewPrediction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mInternetBroadcastReceiver.isNetWorkAvailable(DetailsActivity.this)){
                    Intent intent = new Intent(DetailsActivity.this, SplashActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_input_left, R.anim.anim_ouput_left);
                    finish();
                }else{
                    Toast.makeText(getBaseContext(), "not", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}
package com.example.fingerprintscandailytarot.view;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fingerprintscandailytarot.R;
import com.example.fingerprintscandailytarot.base.BaseAcitivity;
import com.example.fingerprintscandailytarot.viewmodel.DetailsViewModel;
import com.example.fingerprintscandailytarot.viewmodel.SplashActivityViewModel;

public class SplashActivity extends BaseAcitivity implements View.OnClickListener {

    Handler handlerCkInternet;

    Runnable runnableCkInternet;

    ImageView imgCardBig;
    ImageView imgQuestion;
    ImageView imgCardLeft;
    ImageView imgCardRight;

    ImageView imgFingerprint;
    ImageView imgEllipseSmall;
    ImageView imgEllipseBig;
    TextView tvMessLoading;
    TextView tvLoadingpPrediction;

    private Animation anim_zoomIn;
    private Animation anim_zoomIn1;
    private Animation anim_zoomOut;
    private Animation anim_zoomOut1;
    private Animation anim_rotate_left;
    private Animation anim_rotate_left_out;
    private Animation anim_rotate_right;
    private Animation anim_rotate_right_out;
    private Animation anim_rotate;
    Animation animationUtils;
    Animation animationUtils1;
    private boolean checkClick;
    private SplashActivityViewModel splashActivityViewModel;
    private DetailsViewModel detailsViewModel;


    private Handler handler;
    private Runnable runnable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void initView() {

        imgCardBig = findViewById(R.id.img_card_big);
        imgCardLeft = findViewById(R.id.img_card_leff);
        imgCardRight = findViewById(R.id.img_card_right);
        imgQuestion = findViewById(R.id.img_question_big);
        imgFingerprint = findViewById(R.id.img_fingerprint);
        tvMessLoading = findViewById(R.id.tv_status_loading);
        imgEllipseSmall = findViewById(R.id.img_ellipse_small);
        imgEllipseBig = findViewById(R.id.img_ellipse_big);
        tvLoadingpPrediction = findViewById(R.id.tv_mess_start_scan);

        anim_zoomIn = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        anim_zoomIn1 = AnimationUtils.loadAnimation(this, R.anim.anim_scale1);
        anim_zoomOut = AnimationUtils.loadAnimation(this, R.anim.anim_scale_out);
        anim_zoomOut1 = AnimationUtils.loadAnimation(this, R.anim.anim_scale_out1);

        animationUtils = AnimationUtils.loadAnimation(this, R.anim.anim_star);
        animationUtils1 = AnimationUtils.loadAnimation(this, R.anim.anim_star1);
        anim_rotate_left = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_left);
        anim_rotate_left_out = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_left_out);

        anim_rotate_right = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_right);
        anim_rotate_right_out = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_right_out);

        anim_rotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);

        splashActivityViewModel = new ViewModelProvider(this).get(SplashActivityViewModel.class);

        handler = new Handler();


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

        imgCardBig.setOnClickListener(this);

        ImageView imageView = findViewById(R.id.star);
        ImageView imageView1 = findViewById(R.id.star1);

        imageView.setVisibility(View.VISIBLE);
        imageView.startAnimation(animationUtils);
        imageView1.setVisibility(View.VISIBLE);
        imageView1.startAnimation(animationUtils1);

        // nếu có internot thì loading quang cáo


    }

    @Override
    public void onClick(View view) {


        if (view == imgCardBig && !checkClick) {
            checkClick = true;
            imgQuestion.startAnimation(anim_zoomIn1);
            imgCardBig.startAnimation(anim_zoomIn);
            imgCardLeft.startAnimation(anim_rotate_left);
            imgCardRight.startAnimation(anim_rotate_right);

            final int[] count_time = new int[1];
            count_time[0] = 0;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    imgFingerprint.setVisibility(View.VISIBLE);
                    tvMessLoading.setVisibility(View.VISIBLE);
                    imgFingerprint.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            count_time[0]++;
                            splashActivityViewModel.setmCountClick(count_time[0]);
                            return true;
                        }
                    });


//                    imgFingerprint.setOnTouchListener(new View.OnTouchListener() {
//                        @Override
//                        public boolean onTouch(View view, MotionEvent motionEvent) {
//                            switch (motionEvent.getAction()) {
//                                case MotionEvent.ACTION_DOWN:
//                                    Log.e("prox", "down");
//
//                                    break;
//
//                                case MotionEvent.ACTION_UP:
//                                    Log.e("prox", "up");
//                                    break;
//                            }
//
//                            return true;
//                        }
//                    });


                }
            }, 1000);


            splashActivityViewModel.getmCountClick().observe(SplashActivity.this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    switch (integer) {
                        case 1:
                            imgFingerprint.setImageResource(R.drawable.ic_van_tay1);
                            tvMessLoading.setText(R.string.tv_scan_50);
                            handler.postDelayed(runnable = new Runnable() {
                                @Override
                                public void run() {
                                    if (integer == 1) {
                                        count_time[0] = 0;
                                        splashActivityViewModel.setmCountClick(0);
                                        imgFingerprint.setImageResource(R.drawable.ic_van_tay);
                                        tvMessLoading.setText(R.string.tv_start_scan);
                                    }
                                }
                            }, 1000);

                            break;
                        case 2:
                            imgFingerprint.setImageResource(R.drawable.ic_van_tay2);
                            tvMessLoading.setText(R.string.tv_scan_80);
                            imgEllipseSmall.setVisibility(View.VISIBLE);

                            handler.removeCallbacks(runnable);

                            handler.postDelayed(runnable = new Runnable() {
                                @Override
                                public void run() {
                                    if (integer == 2) {
                                        count_time[0] = 1;
                                        splashActivityViewModel.setmCountClick(1);
                                        imgEllipseSmall.setVisibility(View.GONE);
                                    }
                                }
                            }, 1000);

                            break;
                        case 3:
                            imgFingerprint.setImageResource(R.drawable.ic_van_tay3);
                            tvMessLoading.setText(R.string.tv_scan_100);
                            imgEllipseBig.setVisibility(View.VISIBLE);
                            handler.removeCallbacks(runnable);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    imgFingerprint.setVisibility(View.GONE);
                                    tvMessLoading.setVisibility(View.GONE);
                                    imgEllipseSmall.setVisibility(View.GONE);
                                    imgEllipseBig.setVisibility(View.GONE);
                                }
                            }, 500);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    imgQuestion.startAnimation(anim_zoomOut1);
                                    imgCardBig.startAnimation(anim_zoomOut);

                                    imgCardLeft.startAnimation(anim_rotate_left_out);
                                    imgCardRight.startAnimation(anim_rotate_right_out);
                                }
                            }, 500);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvLoadingpPrediction.setVisibility(View.VISIBLE);

                                    // check có mạng với call api
                                    if(mInternetBroadcastReceiver.isNetWorkAvailable(SplashActivity.this)){
                                        Toast.makeText(getBaseContext(), "ok", Toast.LENGTH_SHORT).show();
                                    }else{
//                                        showDialogLoading(SplashActivity.this);


                                    }
                                    // call api
                                    splashActivityViewModel.callApi(SplashActivity.this);

                                    splashActivityViewModel.getmCkLoadCarrer().observe(SplashActivity.this, new Observer<Boolean>() {
                                        @Override
                                        public void onChanged(Boolean aBoolean) {
                                            if(aBoolean){
                                                splashActivityViewModel.callApiHealth(SplashActivity.this);
                                            }
                                        }
                                    });
                                    splashActivityViewModel.getmCkLoadHealth().observe(SplashActivity.this, new Observer<Boolean>() {
                                        @Override
                                        public void onChanged(Boolean aBoolean) {
                                            if(aBoolean){
                                                splashActivityViewModel.callApiLove(SplashActivity.this);
                                            }
                                        }
                                    });
                                    // check call api
                                    splashActivityViewModel.getCkLoadCallApi().observe(SplashActivity.this, new Observer<Boolean>() {
                                        @Override
                                        public void onChanged(Boolean aBoolean) {
                                            if (aBoolean) {

                                                Intent intent = new Intent(SplashActivity.this, DetailsActivity.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.anim_input_right, R.anim.anim_ouput_right);
                                                finish();
                                            }else{
                                                Toast.makeText(getBaseContext(), "error", Toast.LENGTH_SHORT).show();
                                                splashActivityViewModel.callApi(SplashActivity.this);
                                            }
                                        }
                                    });


                                }
                            }, 2000);
                            break;
                    }

                }
            });


        }



    }


}
package com.example.fingerprintscandailytarot.view;

import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fingerprintscandailytarot.base.BaseAcitivity;
import com.example.fingerprintscandailytarot.locale.LocaleHelper;
import com.example.fingerprintscandailytarot.model.AdapterLanguage;
import com.example.fingerprintscandailytarot.R;
import com.example.fingerprintscandailytarot.model.ItemLangguage;
import com.example.fingerprintscandailytarot.model.Language;
import com.example.fingerprintscandailytarot.repository.LanguageRepository;
import com.example.fingerprintscandailytarot.viewmodel.SettingViewModel;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends BaseAcitivity {

    ImageView imgArrowDown;
    RecyclerView lvLanguage;
    private AdapterLanguage adapterLanguage;
    boolean ck;

    private ImageView imgClose;
    private ImageView imgLanguage;

    private SwitchCompat swNotification;
    private ConstraintLayout llLanguage;
    private LinearLayout llRemoveAds;
    private LinearLayout llRateApp;
    private LinearLayout llShareApp;
    private LinearLayout llContactUs;

    private SettingViewModel mSettingViewModel;
    private LocaleHelper localeHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        imgArrowDown = findViewById(R.id.img_arrow_down);
        lvLanguage = findViewById(R.id.lv_language);
        imgClose = findViewById(R.id.img_close);
        swNotification = findViewById(R.id.sw_notificaiton);
        imgLanguage = findViewById(R.id.img_language);

        llLanguage = findViewById(R.id.ll_language);
        llRemoveAds = findViewById(R.id.ll_remove);
        llRateApp = findViewById(R.id.ll_rate_aps);
        llShareApp = findViewById(R.id.ll_share_app);
        llContactUs = findViewById(R.id.ll_contact_us);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseContext(),
                1, RecyclerView.VERTICAL, false);

        lvLanguage.setLayoutManager(layoutManager);
        adapterLanguage = new AdapterLanguage(this);

        mSettingViewModel = new ViewModelProvider(SettingActivity.this).get(SettingViewModel.class);
        localeHelper = new LocaleHelper(this);

    }

    @Override
    protected void initData() {
        lvLanguage.setAdapter(adapterLanguage);
        mSettingViewModel.getAllLanguage().observe(this, new Observer<List<Language>>() {
            @Override
            public void onChanged(List<Language> languages) {
                adapterLanguage.setLanguage(languages);
                for (Language language : languages) {
                    if (language.isCheck()) {
                        imgLanguage.setImageResource(language.getImage());
                    }
                }
            }
        });

    }

    @Override
    protected void initEvent() {

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finish();
//                overridePendingTransition(R.anim.anim_input_left, R.anim.anim_ouput_left);
                Intent intent = new Intent(SettingActivity.this,DetailsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_input_left,R.anim.anim_ouput_left);
                finish();
            }
        });
        Animation animationUtils = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_arrow);
        Animation animArrowout = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_arrow_out);

        llLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ck) {
                    lvLanguage.setVisibility(View.VISIBLE);
                    lvLanguage.setAdapter(adapterLanguage);
                    imgArrowDown.startAnimation(animationUtils);
                    ck = true;
                } else {
                    imgArrowDown.startAnimation(animArrowout);
                    lvLanguage.setVisibility(View.GONE);
                    ck = false;
                }
            }
        });

        adapterLanguage.setItemLangguage(new ItemLangguage() {
            @Override
            public void clicktv(Language language) {
                final List<Language>[] languageList = new List[]{new ArrayList<>()};
                mSettingViewModel.getAllLanguage().observe(SettingActivity.this, new Observer<List<Language>>() {
                    @Override
                    public void onChanged(List<Language> languages) {
                        languageList[0] = languages;
                    }
                });

                for (Language l : languageList[0]) {
                    if (l.getId() == language.getId()) {
                        language.setCheck(true);
                        language.setId(language.getId());
                        mSettingViewModel.updateLaguage(language);

                    } else {
                        l.setCheck(false);
                        l.setId(l.getId());
                        mSettingViewModel.updateLaguage(l);
                    }
                }
                imgArrowDown.startAnimation(animArrowout);
                lvLanguage.setVisibility(View.GONE);
                ck = false;

                // language
                localeHelper.updateResources(language.getMa());
                Intent intent = new Intent(SettingActivity.this,DetailsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_input_left,R.anim.anim_ouput_left);
                finish();

            }
        });

        llRemoveAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvLanguage.setVisibility(View.GONE);
                getActivity(RemoveActivity.class);
                overridePendingTransition(R.anim.anim_input_right, R.anim.anim_ouput_right);
            }
        });

        llRateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvLanguage.setVisibility(View.GONE);
                getDialogRateApp(Gravity.CENTER);
            }
        });
        // share app
        llShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Link download app");
                String link = "https://play.google.com/store/apps/details?id=info.vazquezsoftware.tarot";
                intent.putExtra(Intent.EXTRA_TEXT, link);
                startActivity(Intent.createChooser(intent, "Share link app"));
            }
        });


        SharedPreferences sharedPreferences = getSharedPreferences("ckNotofication", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        swNotification.setChecked(sharedPreferences.getBoolean("notification", true));
        swNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    editor.putBoolean("notification", true);
                    editor.commit();
                } else {
                    editor.putBoolean("notification", false);
                    editor.commit();
                }
            }
        });



        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Restora Neue Regular.ttf");
        //add kiểu font vào textview font

    }

    private void getDialogRateApp(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_rate);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams wLayoutParams = window.getAttributes();
        wLayoutParams.gravity = gravity;
        window.setAttributes(wLayoutParams);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(false);
        } else {
            dialog.setCancelable(false);
        }

        dialog.show();

        TextView tvTitleDialog = dialog.findViewById(R.id.tv_rate_your_experience);
        Button btNoLater = dialog.findViewById(R.id.bt_no_later);
        Button btRateNow = dialog.findViewById(R.id.bt_rate_now);
        RatingBar btRatingBar = dialog.findViewById(R.id.ratingbar_rate);

        btNoLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTitleDialog.setText(R.string.tv_thanks_for_the_rating);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 1000);
            }
        });
        btRateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTitleDialog.setText(R.string.tv_thanks_for_the_rating);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();

                        int noofstars = (int) btRatingBar.getRating();
                        Log.e("prox", "ratingbar" + noofstars);

                    }
                }, 1000);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SettingActivity.this,DetailsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_input_left,R.anim.anim_ouput_left);
        finish();
    }
}
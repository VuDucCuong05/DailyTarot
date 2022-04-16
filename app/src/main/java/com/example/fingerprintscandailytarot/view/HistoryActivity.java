package com.example.fingerprintscandailytarot.view;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fingerprintscandailytarot.R;
import com.example.fingerprintscandailytarot.base.BaseAcitivity;
import com.example.fingerprintscandailytarot.model.AdapterCardHistory;
import com.example.fingerprintscandailytarot.model.Card;
import com.example.fingerprintscandailytarot.viewmodel.DetailsViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoryActivity extends BaseAcitivity {

    private AdapterCardHistory adapterCardHistory;
    private List<Card> mListCard;
    private ListView lvHistoryPrediction;
    private ConstraintLayout imgArrowUp;
    private DetailsViewModel detailsViewModel;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    protected void initView() {
        imgArrowUp = findViewById(R.id.ll_search);
        lvHistoryPrediction = findViewById(R.id.lv_history_prediction);
        detailsViewModel = new ViewModelProvider(HistoryActivity.this).get(DetailsViewModel.class);
    }

    @Override
    protected void initData() {
        mListCard = new ArrayList<>();
        detailsViewModel.getmLiveCard().observe(this, new Observer<List<Card>>() {
            @Override
            public void onChanged(List<Card> list) {
                mListCard = list;
                adapterCardHistory = new AdapterCardHistory(list, HistoryActivity.this, R.layout.item_history);
                lvHistoryPrediction.setAdapter(adapterCardHistory);
            }
        });

    }

    @Override
    protected void initEvent() {

        imgArrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.anim_down1, R.anim.anim_up1);
            }
        });

        lvHistoryPrediction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                detailsViewModel.deleteCard(mListCard.get(i));
                Card card = mListCard.get(i);

                DateFormat df = new SimpleDateFormat("hh:mma::EEE, d MMM yyyy");
                String date = df.format(Calendar.getInstance().getTime());

                Card card1 = new Card(card.getContentLove(),card.getContentHealth(),card.getContentCareer(),date);
                detailsViewModel.insetCard(card1);

                finish();
                overridePendingTransition(R.anim.anim_down1, R.anim.anim_up1);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.anim_down1, R.anim.anim_up1);
    }
}

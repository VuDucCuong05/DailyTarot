package com.example.fingerprintscandailytarot.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fingerprintscandailytarot.R;

import java.text.DateFormat;
import java.util.List;

public class AdapterCardHistory extends BaseAdapter {
    private List<Card> mListCard;

    private Context mContext;
    private int layout;

    public AdapterCardHistory(List<Card> mListCard, Context mContext, int layout) {
        this.mListCard = mListCard;
        this.mContext = mContext;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        if (mListCard == null) {
            return 0;
        }
        return mListCard.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout, null);

        TextView tvTime = view.findViewById(R.id.tv_time_history);
        TextView tvDate = view.findViewById(R.id.tv_date_history);

        Card card = mListCard.get(i);
        String[] parts =card.getTimeLoading().split("::");
        if(parts.length == 2){
            String part1 = parts[0]; // 004
            String part2 = parts[1];
            tvTime.setText(part1);
            tvDate.setText(part2);
        }
        return view;

    }


}
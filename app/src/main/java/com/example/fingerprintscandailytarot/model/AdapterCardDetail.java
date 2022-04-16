package com.example.fingerprintscandailytarot.model;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fingerprintscandailytarot.R;

import java.util.List;

public class AdapterCardDetail extends RecyclerView.Adapter<AdapterCardDetail.ViewHodel> {
    private List<CardShow> mListCard;
//
    private LayoutInflater mInflater;

    public AdapterCardDetail(Context context) {
        mInflater = LayoutInflater.from(context);
    }
    //

//    public AdapterCardDetail(List<Card> mListCard) {
//        this.mListCard = mListCard;
//    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.item_card,parent,false);
        ViewHodel viewHodel = new ViewHodel(view);
        return viewHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
        CardShow card = mListCard.get(position);
        holder.tvTitleCard.setText(card.getTitle());
        holder.tvContentCard.setText(card.getContent());


        String[] parts =card.getTime().split("::");
        if(parts.length == 2){
            String part1 = parts[0]; // 004
            String part2 = parts[1];
            holder.tvTime.setText(part1);
        }
    }

    public void setListCard(List<CardShow> list){
        mListCard = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (mListCard == null){
            return 0;
        }
        return mListCard.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {

        TextView tvTitleCard;
        TextView tvContentCard;
        TextView tvTime;

        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            tvTitleCard = itemView.findViewById(R.id.tv_card_prediction);
            tvContentCard = itemView.findViewById(R.id.tv_content_card);
            tvTime = itemView.findViewById(R.id.tv_time_read);

        }
    }
}

package com.example.fingerprintscandailytarot.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fingerprintscandailytarot.R;

import java.util.List;

public class AdapterLanguage extends RecyclerView.Adapter<AdapterLanguage.ViewHolder> {

    private LayoutInflater mLayoutInflater;

    private List<Language> mListLanguage;
    private ItemLangguage itemLangguage;

    public AdapterLanguage(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setItemLangguage(ItemLangguage itemLangguage) {
        this.itemLangguage = itemLangguage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_language,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Language language = mListLanguage.get(position);

        holder.tvNameLanguage.setText(language.getName());
        holder.imgIconLanguage.setImageResource(language.getImage());

        if(language.isCheck()){
            holder.imgCheckLanguage.setVisibility(View.VISIBLE);
        }else{
            holder.imgCheckLanguage.setVisibility(View.GONE);
        }

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemLangguage.clicktv(language);

            }
        });
    }

    public void setLanguage(List<Language> languages){
        mListLanguage = languages;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mListLanguage == null){
            return 0;
        }
        return mListLanguage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameLanguage;
        ImageView imgIconLanguage;
        ImageView imgCheckLanguage;
        ConstraintLayout layoutItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameLanguage = itemView.findViewById(R.id.tv_name_language);
            imgIconLanguage = itemView.findViewById(R.id.img_icon_language);
            imgCheckLanguage = itemView.findViewById(R.id.img_icon_check);
            layoutItem = itemView.findViewById(R.id.layout_item);
        }
    }
}

package com.example.fingerprintscandailytarot.custom_textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class RestoraTextView extends AppCompatTextView {
    public RestoraTextView(@NonNull Context context) {
        super(context);
        setFontsTextView();
    }

    public RestoraTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFontsTextView();

    }

    public RestoraTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontsTextView();

    }
    private void setFontsTextView(){
        Typeface typeface = Utils.getRestotaTypeface(getContext());
        setTypeface(typeface);
    }
}

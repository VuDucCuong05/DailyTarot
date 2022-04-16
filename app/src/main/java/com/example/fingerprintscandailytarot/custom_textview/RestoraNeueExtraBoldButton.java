package com.example.fingerprintscandailytarot.custom_textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

public class RestoraNeueExtraBoldButton extends AppCompatButton {
    public RestoraNeueExtraBoldButton(@NonNull Context context) {
        super(context);
        setFontsTextView();
    }

    public RestoraNeueExtraBoldButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFontsTextView();

    }

    public RestoraNeueExtraBoldButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontsTextView();

    }
    private void setFontsTextView(){
        Typeface typeface = Utils.getRestotaNeueExtraBoldTypeface(getContext());
        setTypeface(typeface);
    }
}

package com.example.fingerprintscandailytarot.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fingerprintscandailytarot.model.Language;
import com.example.fingerprintscandailytarot.repository.LanguageRepository;

import java.util.List;

public class SettingViewModel extends AndroidViewModel {

    private LanguageRepository mLanguageRepository;
    private LiveData<List<Language>> mAlListLiveData;

    public SettingViewModel(Application application) {
        super(application);
        mLanguageRepository = new LanguageRepository(application);
        mAlListLiveData = mLanguageRepository.getAllLanguage();
    }

    public LiveData<List<Language>> getAllLanguage(){
        return mAlListLiveData;
    }
    public void updateLaguage(Language language) {
        mLanguageRepository.updateLanguage(language);
    }

    // lưu ngôn ngữ đc chọn vào 1 biến
}

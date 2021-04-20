package com.example.testapp.mvvm.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.testapp.mvvm.models.Currencies;
import com.example.testapp.mvvm.repository.CurrenciesRepository;

import java.util.List;

public class CurrenciesViewModel extends AndroidViewModel {

    private CurrenciesRepository mRepository;
    private LiveData<List<Currencies>> mCurrencies;

    public CurrenciesViewModel(@NonNull Application application) {
        super(application);

        mRepository = new CurrenciesRepository(application);
        mCurrencies = mRepository.getAllCurrencies();
    }

    public LiveData<List<Currencies>> getCurrencies() {
        return mCurrencies;
    }
}

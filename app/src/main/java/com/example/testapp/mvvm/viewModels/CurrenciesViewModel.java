package com.example.testapp.mvvm.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.testapp.mvvm.models.Currencies;
import com.example.testapp.mvvm.repository.CurrenciesRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CurrenciesViewModel extends AndroidViewModel {

    private CurrenciesRepository mRepository;
    private List<Currencies> mCurrencies;

    public CurrenciesViewModel(@NonNull Application application) {
        super(application);

        mRepository = new CurrenciesRepository(application);
        mCurrencies = mRepository.getAllCurrencies();
    }

    public List<String> getCurrenciesNames() {
        if (mCurrencies != null) {
            return mCurrencies.stream().map(Currencies::getName).collect(toList());
        }else {
            return new ArrayList<>();
        }
    }
}

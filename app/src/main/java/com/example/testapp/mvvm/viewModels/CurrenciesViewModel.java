package com.example.testapp.mvvm.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.testapp.DI.ServiceLocator;
import com.example.testapp.mvvm.models.Currencies;
import com.example.testapp.mvvm.repository.CurrenciesRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CurrenciesViewModel extends AndroidViewModel {

    private CurrenciesRepository mRepository;
    private LiveData<List<Currencies>> mCurrencies;

    public CurrenciesViewModel(@NonNull Application application) {
        super(application);

        mRepository = ServiceLocator.getInstance().getRepository();
        mCurrencies = mRepository.getAllCurrencies();
    }

    public LiveData<List<Currencies>> getCurrencies() {
        return mCurrencies;
    }
}

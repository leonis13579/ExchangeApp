package com.example.testapp.mvvm.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.testapp.Database.CurrenciesDatabase;
import com.example.testapp.Database.Dao.CurrenciesDao;
import com.example.testapp.mvvm.models.Currencies;

import java.util.List;

public class CurrenciesRepository {
    private CurrenciesDao mCurrenciesDao;
    private LiveData<List<Currencies>> mAllCurrencies;

    public CurrenciesRepository(Application application) {
        CurrenciesDatabase db = CurrenciesDatabase.getDatabase(application);
        mCurrenciesDao = db.currenciesDao();


        mAllCurrencies = mCurrenciesDao.getAll();
    }

    public LiveData<List<Currencies>> getAllCurrencies() {
        return mAllCurrencies;
    }
}

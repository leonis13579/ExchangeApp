package com.example.testapp.mvvm.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.testapp.Database.CurrenciesDatabase;
import com.example.testapp.Database.Dao.CurrenciesDao;
import com.example.testapp.Network.NetworkLogic;
import com.example.testapp.DI.ServiceLocator;
import com.example.testapp.mvvm.models.Currencies;

import java.util.List;

public class CurrenciesRepository {
    private CurrenciesDao mCurrenciesDao;
    private LiveData<List<Currencies>> mAllCurrencies;

    public CurrenciesRepository(Context context) {
        CurrenciesDatabase db = CurrenciesDatabase.getDatabase(context);
        mCurrenciesDao = db.currenciesDao();
        mAllCurrencies = mCurrenciesDao.getAll();
    }

    public LiveData<List<Currencies>> getAllCurrencies() {
        return mAllCurrencies;
    }

    public double getCurrenciesCourse(String fromCurrencyName, String toCurrencyName) throws RuntimeException {
        updateData(fromCurrencyName);

        for (Currencies currency : mAllCurrencies.getValue()) {
            if (currency.getName().equals(fromCurrencyName)) {
                if (currency.getCurrencyValue().containsKey(toCurrencyName)) {
                    return currency.getCurrencyValue().get(toCurrencyName);
                }
            }
        }

        throw new RuntimeException("Can't get data for this currencies");
    }

    private void updateData(String fromCurrencyName) {
        for (Currencies currency : mAllCurrencies.getValue()) {
            if (currency.getName().equals(fromCurrencyName)) {
                NetworkLogic.getInstance().getCurrencyValues(fromCurrencyName,
                        (currencyValues) -> {
                            currency.setCurrencyValue(currencyValues);
                            CurrenciesDatabase.databaseWriteExecutor.execute(() -> {
                                mCurrenciesDao.update(currency);
                            });
                        });
            }
        }
    }
}

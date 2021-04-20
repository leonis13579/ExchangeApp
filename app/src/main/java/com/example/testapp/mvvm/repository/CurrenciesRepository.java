package com.example.testapp.mvvm.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.testapp.Database.CurrenciesDatabase;
import com.example.testapp.Database.Dao.CurrenciesDao;
import com.example.testapp.Network.NetworkLogic;
import com.example.testapp.mvvm.models.Currencies;

import java.util.List;
import java.util.Map;

public class CurrenciesRepository {
    private CurrenciesDao mCurrenciesDao;
    private List<Currencies> mAllCurrencies;

    public CurrenciesRepository(Application application) {
        CurrenciesDatabase db = CurrenciesDatabase.getDatabase(application);
        mCurrenciesDao = db.currenciesDao();

        CurrenciesDatabase.databaseWriteExecutor.execute(() -> {
            mAllCurrencies = mCurrenciesDao.getAll().getValue();
        });
    }

    public List<Currencies> getAllCurrencies() {
        return mAllCurrencies;
    }

    public double getCurrenciesCourse(String fromCurrencyName, String toCurrencyName) throws RuntimeException {
        updateData(fromCurrencyName);

        for (Currencies currency : mAllCurrencies) {
            if (currency.getName().equals(fromCurrencyName)) {
                if (currency.getCurrencyValue().containsKey(toCurrencyName)) {
                    return currency.getCurrencyValue().get(toCurrencyName);
                }
            }
        }

        throw new RuntimeException("Can't get data for this currencies");
    }

    private void updateData(String fromCurrencyName) {
        for (Currencies currency : mAllCurrencies) {
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

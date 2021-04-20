package com.example.testapp.mvvm.viewModels;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testapp.R;
import com.example.testapp.mvvm.models.Currencies;
import com.example.testapp.mvvm.repository.CurrenciesRepository;
import com.example.testapp.mvvm.views.CurrenciesFragment;

import java.util.List;

public class ExchangeViewModel extends AndroidViewModel {

    private MutableLiveData<String> currencyFrom = new MutableLiveData<>("");
    private MutableLiveData<String> currencyTo = new MutableLiveData<>("");
    private MutableLiveData<Double> sumFrom = new MutableLiveData<>();
    private MutableLiveData<Double> sumTo = new MutableLiveData<>();

    public ExchangeViewModel(final Application application) {
        super(application);
    }

    public void ChangeCurrency() {
        String tmp = currencyFrom.getValue();
        currencyFrom.setValue(currencyTo.getValue());
        currencyTo.setValue(tmp);
    }

    public MutableLiveData<String> getCurrencyFrom() {
        return currencyFrom;
    }

    public MutableLiveData<String> getCurrencyTo() {
        return currencyTo;
    }

    public MutableLiveData<Double> getSumFrom() {
        return sumFrom;
    }

    public MutableLiveData<Double> getSumTo() {
        return sumTo;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom.setValue(currencyFrom);
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo.setValue(currencyTo);
    }
}
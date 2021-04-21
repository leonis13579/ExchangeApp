package com.example.testapp.mvvm.viewModels;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testapp.DI.ServiceLocator;
import com.example.testapp.R;
import com.example.testapp.mvvm.models.Currencies;
import com.example.testapp.mvvm.repository.CurrenciesRepository;
import com.example.testapp.mvvm.views.CurrenciesFragment;

import java.util.List;

public class ExchangeViewModel extends AndroidViewModel {

    private CurrenciesRepository mRepository;

    private MutableLiveData<String> currencyFrom = new MutableLiveData<>("");
    private MutableLiveData<String> currencyTo = new MutableLiveData<>("");
    private MutableLiveData<Double> sumFrom = new MutableLiveData<>();
    private MutableLiveData<Double> sumTo = new MutableLiveData<>();

    public ExchangeViewModel(final Application application) {
        super(application);

        mRepository = ServiceLocator.getInstance().getRepository();
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

    public void setSumFrom(double sumFrom) {
        this.sumFrom.setValue(sumFrom);
    }

    public void CountSumTo() {
        try {
            if (currencyFrom.getValue() != null && !currencyFrom.getValue().isEmpty() &&
                    currencyTo.getValue() != null && !currencyTo.getValue().isEmpty()) {
                sumTo.setValue(sumFrom.getValue() * mRepository.getCurrenciesCourse(currencyFrom.getValue(), currencyTo.getValue()));
            } else {
                Toast.makeText(getApplication().getApplicationContext(), "Need to choose currencies first", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
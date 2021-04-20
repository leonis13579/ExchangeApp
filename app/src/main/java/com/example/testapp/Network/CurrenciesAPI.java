package com.example.testapp.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CurrenciesAPI {
    @GET("v6/latest/{fromCurrency}")
    Call<String> getCurrencyInfo(@Path("fromCurrency") String fromCurrencyText);
}

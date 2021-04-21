package com.example.testapp.Network;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CurrenciesAPI {
    @GET("v6/latest/{fromCurrency}")
    Call<Map<String, Object>> getCurrencyInfo(@Path("fromCurrency") String fromCurrencyText);
}

package com.example.testapp.Network;

import android.os.Debug;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkLogic {
    private Retrofit mRetrofit;

    public NetworkLogic() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://open.exchangerate-api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getCurrencyValues(String fromCurrencyName, Consumer<Map<String, Double>> CallBack) {
        CurrenciesAPI api = mRetrofit.create(CurrenciesAPI.class);

        api.getCurrencyInfo(fromCurrencyName).enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                Object rawData = response.body().get("rates");
                if (rawData instanceof Map) {
                    CallBack.accept((Map<String, Double>) rawData);
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }
}

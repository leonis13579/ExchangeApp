package com.example.testapp.Network;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkLogic {
    private static NetworkLogic instance = null;
    public static NetworkLogic getInstance() {
        if (instance == null) {
            instance = new NetworkLogic();
        }
        return instance;
    }

    private Retrofit mRetrofit;

    private NetworkLogic() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://open.exchangerate-api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getCurrencyValues(String fromCurrencyName, Consumer<Map<String, Double>> CallBack) {
        CurrenciesAPI api = mRetrofit.create(CurrenciesAPI.class);

        api.getCurrencyInfo(fromCurrencyName).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Map mapResponse = new Gson().fromJson(response.body(), Map.class);
                Object rawData = mapResponse.get("rates");
                if (rawData instanceof Map) {
                    CallBack.accept((Map<String, Double>) rawData);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}

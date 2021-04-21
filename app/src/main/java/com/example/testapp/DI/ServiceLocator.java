package com.example.testapp.DI;

import android.content.Context;

import com.example.testapp.Database.CurrenciesDatabase;
import com.example.testapp.Network.NetworkLogic;
import com.example.testapp.mvvm.repository.CurrenciesRepository;

public class ServiceLocator {

    private static ServiceLocator serviceLocator;

    private CurrenciesRepository mRepository;

    private NetworkLogic mNetworkLogic;

    private Context context;

    private ServiceLocator(Context context) {
        serviceLocator = this;

        mRepository = new CurrenciesRepository(context);
        mNetworkLogic = new NetworkLogic();
    }



    public static ServiceLocator getInstance() {
        return serviceLocator;
    }

    public static void init (Context context){
        serviceLocator = new ServiceLocator(context);
    }

    public CurrenciesRepository getRepository() {
        return mRepository;
    }

    public NetworkLogic getNetworkLogic() {
        return mNetworkLogic;
    }
}

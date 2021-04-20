package com.example.testapp.mvvm.views;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testapp.R;
import com.example.testapp.databinding.ExchangeFragmentBinding;
import com.example.testapp.mvvm.viewModels.ExchangeViewModel;

import java.util.List;

public class ExchangeFragment extends Fragment {

    private ExchangeViewModel mViewModel;
    ExchangeFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ExchangeFragmentBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ExchangeViewModel.class);

        mViewModel.getCurrencyFrom().observe(getViewLifecycleOwner(), currency -> {
            binding.currencyFromText.setText(currency);
        });
        mViewModel.getCurrencyTo().observe(getViewLifecycleOwner(), currency -> {
            binding.currencyToText.setText(currency);
        });


        binding.changeCurrencies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.ChangeCurrency();
            }
        });

        binding.currencyFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrenciesFragment cf = new CurrenciesFragment();
                cf.show(getParentFragmentManager(), "CurrencyFrom");
            }
        });
    }
}
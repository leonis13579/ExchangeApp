package com.example.testapp.mvvm.views;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
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

        binding.sumTo.setKeyListener(null);

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

        mViewModel.getSumFrom().observe(getViewLifecycleOwner(), sumFrom -> {
            if (mViewModel.getCurrencyTo().getValue() != null &&
                    mViewModel.getCurrencyFrom().getValue() != null) {
                mViewModel.CountSumTo();
            }
        });

        mViewModel.getSumTo().observe(getViewLifecycleOwner(), sumTo -> {
            binding.sumTo.setText(sumTo.toString());
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
                CurrenciesFragment cf = new CurrenciesFragment(mViewModel.getCurrencyFrom());
                cf.show(getParentFragmentManager(), "CurrencyFrom");
            }
        });

        binding.currencyTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrenciesFragment cf = new CurrenciesFragment(mViewModel.getCurrencyTo());
                cf.show(getParentFragmentManager(), "CurrencyTo");
            }
        });

        binding.sumFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.setSumFrom(s.length() > 0 ? Double.parseDouble(s.toString()) : 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
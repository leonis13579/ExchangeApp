package com.example.testapp.mvvm.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.testapp.adapters.CurrenciesAdapter;
import com.example.testapp.databinding.CurrenciesFragmentBinding;
import com.example.testapp.mvvm.models.Currencies;
import com.example.testapp.mvvm.viewModels.CurrenciesViewModel;
import com.example.testapp.mvvm.viewModels.ExchangeViewModel;

public class CurrenciesFragment extends DialogFragment {
    private CurrenciesViewModel mViewModel;
    private MutableLiveData<String> changingCurrency;
    CurrenciesFragmentBinding binding;

    public CurrenciesFragment(MutableLiveData<String> changinCurrency) {
        this.changingCurrency = changinCurrency;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CurrenciesFragmentBinding.inflate(inflater, container, false);
        View v = binding.getRoot();

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CurrenciesViewModel.class);

        binding.currenciesRecyclerView.setAdapter(new CurrenciesAdapter(this, mViewModel.getCurrenciesNames()));

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (changingCurrency.getValue() != null && !changingCurrency.getValue().equals("")) {
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Need to choose a currency", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public MutableLiveData<String> getChangingCurrency() {
        return changingCurrency;
    }
}

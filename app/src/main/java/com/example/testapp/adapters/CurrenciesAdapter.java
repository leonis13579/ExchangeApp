package com.example.testapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.adapters.viewHolders.CurrenciesViewHolder;
import com.example.testapp.databinding.CurrencyElementBinding;
import com.example.testapp.mvvm.models.Currencies;
import com.example.testapp.mvvm.views.CurrenciesFragment;

import java.util.List;

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesViewHolder> {

    private final List<String> currencies;
    private final CurrenciesFragment currenciesFragment;

    public CurrenciesAdapter(CurrenciesFragment currenciesFragment, List<String> currencies) {
        this.currenciesFragment = currenciesFragment;
        this.currencies = currencies;
    }

    @NonNull
    @Override
    public CurrenciesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CurrencyElementBinding ceb = CurrencyElementBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CurrenciesViewHolder(ceb);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrenciesViewHolder holder, int position) {
        holder.getRadioButton().setText(currencies.get(position));
        holder.getRadioButton().setChecked(currenciesFragment.getChangingCurrency().getValue().equals(currencies.get(position)));
        holder.getRadioButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currenciesFragment.getChangingCurrency().setValue(currencies.get(position));
                currenciesFragment.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }
}

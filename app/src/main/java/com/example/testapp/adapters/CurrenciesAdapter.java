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
import com.example.testapp.mvvm.models.Currencies;

import java.util.List;

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesViewHolder> {

    List<Currencies> currencies;

    public CurrenciesAdapter(LiveData<List<Currencies>> currencies) {
        this.currencies = currencies.getValue();
    }

    @NonNull
    @Override
    public CurrenciesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_element, parent);
        return new CurrenciesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrenciesViewHolder holder, int position) {
        holder.getRadioButton().setText(currencies.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }
}

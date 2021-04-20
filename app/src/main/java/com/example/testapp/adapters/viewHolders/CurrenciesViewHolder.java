package com.example.testapp.adapters.viewHolders;

import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.databinding.CurrencyElementBinding;

public class CurrenciesViewHolder extends RecyclerView.ViewHolder{

    RadioButton radioButton;

    public CurrenciesViewHolder(@NonNull View itemView) {
        super(itemView);
        CurrencyElementBinding binding = CurrencyElementBinding.bind(itemView);
        radioButton = binding.rButton;
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }
}

package com.example.testapp.adapters.viewHolders;

import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.databinding.CurrencyElementBinding;

public class CurrenciesViewHolder extends RecyclerView.ViewHolder{

    RadioButton radioButton;

    public CurrenciesViewHolder(CurrencyElementBinding ceb) {
        super(ceb.getRoot());
        radioButton = ceb.rButton;
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }
}

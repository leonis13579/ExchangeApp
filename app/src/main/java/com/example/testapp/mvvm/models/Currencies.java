package com.example.testapp.mvvm.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.testapp.Database.MapConverter;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.TreeMap;

@Entity
public class Currencies {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo
    private int id;

    @NotNull
    @ColumnInfo
    private String name;

    @ColumnInfo
    @TypeConverters({MapConverter.class})
    private Map<String, Double> currencyValue;

    public Currencies(@NotNull String name) {
        this.name = name;
        this.currencyValue = new TreeMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public Map<String, Double> getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(Map<String, Double> currencyValue) {
        this.currencyValue = currencyValue;
    }
}

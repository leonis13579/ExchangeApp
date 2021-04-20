package com.example.testapp.Database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.util.Map;

public class MapConverter {
    @TypeConverter
    public String fromMap(Map map) {
        return new Gson().toJson(map);
    }

    @TypeConverter
    public Map toMap(String json) {
        return new Gson().fromJson(json, Map.class);
    }
}

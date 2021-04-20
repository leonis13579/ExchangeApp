package com.example.testapp.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.testapp.mvvm.models.Currencies;

import java.util.List;

@Dao
public interface CurrenciesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Currencies currencies);

    @Query("DELETE FROM currencies")
    void deleteAll();

    @Update
    void update(Currencies currency);

    @Query("SELECT * FROM currencies")
    LiveData<List<Currencies>> getAll();
}

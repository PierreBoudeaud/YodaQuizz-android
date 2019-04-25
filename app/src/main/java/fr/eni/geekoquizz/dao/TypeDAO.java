package fr.eni.geekoquizz.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.eni.geekoquizz.bo.Type;

@Dao
public interface TypeDAO {
    @Query("SELECT * FROM types")
    LiveData<List<Type>> get();

    @Query("SELECT * FROM types WHERE type_id = :id LIMIT 1")
    LiveData<Type> get(int id);

    @Insert
    void insert(Type type);

    @Insert
    void insertAll(List<Type> types);
}

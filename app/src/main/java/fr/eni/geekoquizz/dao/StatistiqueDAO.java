package fr.eni.geekoquizz.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.eni.geekoquizz.bo.Statistique;

@Dao
public interface StatistiqueDAO {
    @Query("SELECT * FROM statistiques WHERE statistique_quizzId = :quizzId")
    LiveData<List<Statistique>> getAllByQuizz(int quizzId);

    @Query("SELECT * FROM statistiques WHERE statistique_id = :id LIMIT 1")
    LiveData<Statistique> get(int id);

    @Insert
    void insert(Statistique statistique);
}

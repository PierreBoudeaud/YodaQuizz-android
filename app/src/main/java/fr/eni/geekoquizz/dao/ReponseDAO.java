package fr.eni.geekoquizz.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.eni.geekoquizz.bo.Reponse;

@Dao
public interface ReponseDAO {
    @Query("SELECT * FROM reponses")
    LiveData<List<Reponse>> get();

    @Query("SELECT * FROM reponses WHERE reponse_id = :id LIMIT 1")
    LiveData<Reponse> get(int id);

    @Query("SELECT r.* FROM Reponses r WHERE r.reponse_questionId = :quizzId")
    LiveData<List<Reponse>> getByQuestion(int quizzId);

    @Insert
    void insert(Reponse reponse);
}

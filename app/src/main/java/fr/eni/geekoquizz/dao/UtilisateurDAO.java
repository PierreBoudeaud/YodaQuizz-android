package fr.eni.geekoquizz.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.eni.geekoquizz.bo.Utilisateur;

@Dao
public interface UtilisateurDAO {
    @Query("SELECT * FROM utilisateurs")
    LiveData<List<Utilisateur>> get();

    @Query("SELECT * FROM utilisateurs WHERE utilisateur_id = :id LIMIT 1")
    LiveData<Utilisateur> get(int id);

    @Insert
    void insert(Utilisateur utilisateur);
}

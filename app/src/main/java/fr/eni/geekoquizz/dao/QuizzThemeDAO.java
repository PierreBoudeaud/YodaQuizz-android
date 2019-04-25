package fr.eni.geekoquizz.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import fr.eni.geekoquizz.bo.QuizzTheme;

@Dao
public interface QuizzThemeDAO {
    @Insert
    void insert(QuizzTheme quizzTheme);
}

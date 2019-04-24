package fr.eni.geekoquizz.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.eni.geekoquizz.bo.Question;
import fr.eni.geekoquizz.bo.Theme;

@Dao
public interface ThemeDAO {
    @Query("SELECT * FROM themes")
    LiveData<List<Theme>> get();

    @Query("SELECT * FROM themes WHERE theme_id = :id LIMIT 1")
    LiveData<Theme> get(int id);

    @Query("SELECT t.* FROM QuizzTheme qt INNER JOIN Themes t ON qt.themeId = t.theme_id WHERE qt.quizzId = :quizzId")
    LiveData<List<Theme>> getByquizz(int quizzId);

    @Insert
    void insert(Theme theme);
}

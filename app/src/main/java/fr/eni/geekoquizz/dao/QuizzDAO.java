package fr.eni.geekoquizz.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.eni.geekoquizz.bo.Quizz;

@Dao
public interface QuizzDAO {
    @Query("SELECT * FROM Quizz")
    LiveData<List<Quizz>> get();

    @Query("SELECT * FROM Quizz WHERE quizz_id = :id LIMIT 1")
    LiveData<Quizz> get(int id);

    @Insert
    void insert(Quizz quizz);

    @Query("SELECT COUNT(*) FROM questions que INNER JOIN Quizz qui ON que.question_quizzId = qui.quizz_id WHERE qui.quizz_id = :id")
    LiveData<Integer> nbQuestions(int id);
}

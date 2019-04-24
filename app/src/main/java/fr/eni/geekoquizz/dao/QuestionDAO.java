package fr.eni.geekoquizz.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.eni.geekoquizz.bo.Question;

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM questions INNER JOIN Reponses WHERE question_quizzId = :quizzId")
    LiveData<List<Question>> get(int quizzId);

    @Query("SELECT * FROM questions INNER JOIN Reponses WHERE question_quizzId = :quizzId AND question_id = :questionId LIMIT 1")
    LiveData<Question> get(int quizzId, int questionId);
}

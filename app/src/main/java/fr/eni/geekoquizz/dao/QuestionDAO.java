package fr.eni.geekoquizz.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.eni.geekoquizz.bo.Question;
import fr.eni.geekoquizz.bo.Quizz;

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM questions INNER JOIN Reponses WHERE question_quizzId = :quizzId")
    LiveData<List<Question>> getAllByQuizz(int quizzId);

    @Query("SELECT * FROM questions INNER JOIN Reponses WHERE question_id = :questionId LIMIT 1")
    LiveData<Question> get(int questionId);

    @Insert
    void insert(Question quizz);
}

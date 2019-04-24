package fr.eni.geekoquizz.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import fr.eni.geekoquizz.bo.Question;
import fr.eni.geekoquizz.repository.QuestionRepository;

public class QuestionViewModel extends AndroidViewModel {
    QuestionRepository questionRepository;

    public QuestionViewModel(@NonNull Application application) {
        super(application);
        questionRepository = new QuestionRepository(application);
    }

    public void insert(Question question) {
        questionRepository.insert(question);
    }

    public LiveData<Question> get(int id) {
        return questionRepository.get(id);
    }

    public LiveData<List<Question>> getByQuizz(int quizzId) {
        return questionRepository.getAllByQuizz(quizzId);
    }
}

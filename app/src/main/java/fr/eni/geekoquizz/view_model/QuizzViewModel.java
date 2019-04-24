package fr.eni.geekoquizz.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.repository.QuizzRepository;

public class QuizzViewModel extends AndroidViewModel {
    private LiveData<List<Quizz>> quizz;
    QuizzRepository quizzRepository;

    public QuizzViewModel(@NonNull Application application) {
        super(application);
        quizzRepository = new QuizzRepository(application);
        init();
    }

    private void init() {
        quizz = quizzRepository.get();
    }

    public void insert(Quizz quizz) {
        quizzRepository.insert(quizz);
    }

    public LiveData<List<Quizz>> get() {
        return quizz;
    }

    public LiveData<Quizz> get(int id) {
        return quizzRepository.get(id);
    }

    public LiveData<Integer> getNbQuestions(int id) {
        return quizzRepository.getNbQuestionOfQuizz(id);
    }
}

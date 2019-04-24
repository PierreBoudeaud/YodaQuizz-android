package fr.eni.geekoquizz.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import fr.eni.geekoquizz.bo.Reponse;
import fr.eni.geekoquizz.repository.ReponseRepository;

public class ReponseViewModel extends AndroidViewModel {
    ReponseRepository reponseRepository;

    public ReponseViewModel(@NonNull Application application) {
        super(application);
        reponseRepository = new ReponseRepository(application);
    }

    public void insert(Reponse reponse) {
        reponseRepository.insert(reponse);
    }

    public LiveData<Reponse> get(int id) {
        return reponseRepository.get(id);
    }

    public LiveData<List<Reponse>> getByQuestion(int questionId) {
        return reponseRepository.getAllByQuestion(questionId);
    }
}

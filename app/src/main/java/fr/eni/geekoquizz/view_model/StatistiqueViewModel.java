package fr.eni.geekoquizz.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import fr.eni.geekoquizz.bo.Statistique;
import fr.eni.geekoquizz.repository.StatistiqueRepository;

public class StatistiqueViewModel extends AndroidViewModel {
    StatistiqueRepository statistiqueRepository;

    public StatistiqueViewModel(@NonNull Application application) {
        super(application);
        statistiqueRepository = new StatistiqueRepository(application);
    }

    public void insert(Statistique statistique) {
        statistiqueRepository.insert(statistique);
    }

    public LiveData<Statistique> get(int id) {
        return statistiqueRepository.get(id);
    }

    public LiveData<List<Statistique>> getByQuizz(int quizzId) {
        return statistiqueRepository.getAllByQuizz(quizzId);
    }
}

package fr.eni.geekoquizz.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.bo.Theme;
import fr.eni.geekoquizz.repository.QuizzRepository;
import fr.eni.geekoquizz.repository.ThemeRepository;

public class ThemeViewModel extends AndroidViewModel {
    private LiveData<List<Theme>> themes;
    ThemeRepository themeRepository;

    public ThemeViewModel(@NonNull Application application) {
        super(application);
        themeRepository = new ThemeRepository(application);
        init();
    }

    private void init() {
        themes = themeRepository.get();
    }

    public void insert(Theme theme) {
        themeRepository.insert(theme);
    }

    public LiveData<List<Theme>> get() {
        return themes;
    }

    public LiveData<Theme> get(int id) {
        return themeRepository.get(id);
    }

    public LiveData<List<Theme>> getByQuizz(int quizzId) {
        return themeRepository.getByQuizz(quizzId);
    }
}

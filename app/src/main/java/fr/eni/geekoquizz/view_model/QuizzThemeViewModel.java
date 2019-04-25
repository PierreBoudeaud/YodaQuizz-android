package fr.eni.geekoquizz.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import fr.eni.geekoquizz.bo.QuizzTheme;
import fr.eni.geekoquizz.repository.QuizzThemeRepository;

public class QuizzThemeViewModel extends AndroidViewModel {
    QuizzThemeRepository quizzThemeRepository;

    public QuizzThemeViewModel(@NonNull Application application) {
        super(application);
        quizzThemeRepository = new QuizzThemeRepository(application);
    }

    public void insert(QuizzTheme theme) {
        quizzThemeRepository.insert(theme);
    }


}

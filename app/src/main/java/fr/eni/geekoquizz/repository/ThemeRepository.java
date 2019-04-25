package fr.eni.geekoquizz.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.bo.Theme;
import fr.eni.geekoquizz.dao.Bdd;
import fr.eni.geekoquizz.dao.ThemeDAO;

public class ThemeRepository {
    ThemeDAO themeDAO;

    public ThemeRepository(Context context) {
        Bdd bdd = Bdd.getInstance(context);
        themeDAO = bdd.themeDAO();
    }

    public LiveData<List<Theme>> get() {
        return themeDAO.get();
    }

    public LiveData<Theme> get(int id) {
        return themeDAO.get(id);
    }

    public LiveData<List<Theme>> getByQuizz(int quizzId) {
        return themeDAO.getByquizz(quizzId);
    }

    public void insert(Theme theme) {
        ThemeRepository.AsyncInsert async = new ThemeRepository.AsyncInsert();
        async.execute(theme);
    }

    public void insertAll(List<Theme> themes) {
        ThemeRepository.AsyncMultipleInsert async = new ThemeRepository.AsyncMultipleInsert();
        async.execute(themes);
    }

    public class AsyncInsert extends AsyncTask<Theme, Void, Void> {
        @Override
        protected Void doInBackground(Theme... themes) {
            themeDAO.insert(themes[0]);
            return null;
        }
    }

    public class AsyncMultipleInsert extends AsyncTask<List<Theme>, Void, Void> {
        @Override
        protected Void doInBackground(List<Theme>... themes) {
            themeDAO.insertAll(themes[0]);
            return null;
        }
    }
}

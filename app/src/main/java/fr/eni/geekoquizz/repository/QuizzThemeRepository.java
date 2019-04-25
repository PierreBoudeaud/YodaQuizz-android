package fr.eni.geekoquizz.repository;

import android.content.Context;
import android.os.AsyncTask;

import fr.eni.geekoquizz.bo.QuizzTheme;
import fr.eni.geekoquizz.dao.Bdd;
import fr.eni.geekoquizz.dao.QuizzThemeDAO;

public class QuizzThemeRepository {
    QuizzThemeDAO quizzThemeDAO;

    public QuizzThemeRepository(Context context) {
        Bdd bdd = Bdd.getInstance(context);
        quizzThemeDAO = bdd.quizzThemeDAO();
    }

    public void insert(QuizzTheme quizzTheme) {
        QuizzThemeRepository.AsyncInsert async = new QuizzThemeRepository.AsyncInsert();
        async.execute(quizzTheme);
    }

    public class AsyncInsert extends AsyncTask<QuizzTheme, Void, Void> {
        @Override
        protected Void doInBackground(QuizzTheme... quizzThemes) {
            quizzThemeDAO.insert(quizzThemes[0]);
            return null;
        }
    }
}

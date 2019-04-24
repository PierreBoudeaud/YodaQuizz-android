package fr.eni.geekoquizz.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import fr.eni.geekoquizz.bo.Statistique;
import fr.eni.geekoquizz.dao.Bdd;
import fr.eni.geekoquizz.dao.StatistiqueDAO;

public class StatistiqueRepository {
    StatistiqueDAO statistiqueDAO;

    public StatistiqueRepository(Context context) {
        Bdd bdd = Bdd.getInstance(context);
        statistiqueDAO = bdd.statistiqueDAO();
    }

    public LiveData<List<Statistique>> getAllByQuizz(int quizzId) {
        return statistiqueDAO.getAllByQuizz(quizzId);
    }

    public LiveData<Statistique> get(int id) {
        return statistiqueDAO.get(id);
    }

    public void insert(Statistique statistique) {

    }

    public class AsyncInsert extends AsyncTask<Statistique, Void, Void> {
        @Override
        protected Void doInBackground(Statistique... statistiques) {
            statistiqueDAO.insert(statistiques[0]);
            return null;
        }
    }
}

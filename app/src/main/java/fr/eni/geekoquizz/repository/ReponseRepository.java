package fr.eni.geekoquizz.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import fr.eni.geekoquizz.bo.Reponse;
import fr.eni.geekoquizz.dao.Bdd;
import fr.eni.geekoquizz.dao.ReponseDAO;

public class ReponseRepository {
    ReponseDAO reponseDAO;

    public ReponseRepository(Context context) {
        Bdd bdd = Bdd.getInstance(context);
        reponseDAO = bdd.reponseDAO();
    }

    public LiveData<Reponse> get(int reponseId) {
        return reponseDAO.get(reponseId);
    }

    public LiveData<List<Reponse>> getAllByQuestion(int questionId) {
        return reponseDAO.getByQuestion(questionId);
    }

    public void insert(Reponse reponse) {
        AsyncInsert async = new AsyncInsert();
        async.execute(reponse);
    }

    public class AsyncInsert extends AsyncTask<Reponse, Void, Void> {
        @Override
        protected Void doInBackground(Reponse... reponse) {
            reponseDAO.insert(reponse[0]);
            return null;
        }
    }
}

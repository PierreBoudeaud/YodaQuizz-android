package fr.eni.geekoquizz.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import android.os.AsyncTask;
import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.dao.Bdd;
import fr.eni.geekoquizz.dao.QuizzDAO;

public class QuizzRepository {
    QuizzDAO quizzDAO;
    LiveData<List<Quizz>> quizz;

    public QuizzRepository(Context context) {
        Bdd bdd = Bdd.getInstance(context);
        quizzDAO = bdd.quizzDAO();
        quizz = quizzDAO.get();
    }

    public LiveData<List<Quizz>> get() {
        return quizz;
    }

    public LiveData<Quizz> get(int id) {
        return quizzDAO.get(id);
    }

    public LiveData<Integer> getNbQuestionOfQuizz(int id) { return quizzDAO.nbQuestions(id); }

    public void insert(Quizz quizz) {
        AsyncInsert async = new AsyncInsert();
        async.execute(quizz);
    }

    public class AsyncInsert extends AsyncTask<Quizz, Void, Void> {
        @Override
        protected Void doInBackground(Quizz... quizz) {
            quizzDAO.insert(quizz[0]);
            return null;
        }
    }
}

package fr.eni.geekoquizz.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import fr.eni.geekoquizz.bo.Question;
import fr.eni.geekoquizz.dao.Bdd;
import fr.eni.geekoquizz.dao.QuestionDAO;

public class QuestionRepository {
    QuestionDAO questionDAO;

    public QuestionRepository(Context context) {
        Bdd bdd = Bdd.getInstance(context);
        questionDAO = bdd.questionDAO();
    }

    public LiveData<List<Question>> getAllByQuizz(int quizzId) {
        return questionDAO.getAllByQuizz(quizzId);
    }

    public LiveData<Question> get(int questionId) {
        return questionDAO.get(questionId);
    }

    public void insert(Question question) {
        AsyncInsert async = new AsyncInsert();
        async.execute(question);
    }

    public class AsyncInsert extends AsyncTask<Question, Void, Void> {
        @Override
        protected Void doInBackground(Question... question) {
            questionDAO.insert(question[0]);
            return null;
        }
    }
}

package fr.eni.geekoquizz.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import fr.eni.geekoquizz.bo.Utilisateur;
import fr.eni.geekoquizz.dao.Bdd;
import fr.eni.geekoquizz.dao.UtilisateurDAO;

public class UtilisateurRepository {
    UtilisateurDAO utilisateurDAO;
    LiveData<List<Utilisateur>> utilisateurs;

    public UtilisateurRepository(Context context) {
        Bdd bdd = Bdd.getInstance(context);
        utilisateurDAO = bdd.utilisateurDAO();
        utilisateurs = utilisateurDAO.get();
    }

    public LiveData<List<Utilisateur>> get() {
        return utilisateurs;
    }

    public LiveData<Utilisateur> get(int id) {
        return utilisateurDAO.get(id);
    }

    public void insert(Utilisateur utilisateur) {

    }

    public class AsyncInsert extends AsyncTask<Utilisateur, Void, Void> {
        @Override
        protected Void doInBackground(Utilisateur... utilisateurs) {
            utilisateurDAO.insert(utilisateurs[0]);
            return null;
        }
    }
}

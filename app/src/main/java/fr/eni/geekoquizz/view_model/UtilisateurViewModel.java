package fr.eni.geekoquizz.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import fr.eni.geekoquizz.bo.Utilisateur;
import fr.eni.geekoquizz.repository.UtilisateurRepository;

public class UtilisateurViewModel extends AndroidViewModel {
    private LiveData<List<Utilisateur>> utilisateurs;
    UtilisateurRepository utilisateurRepository;

    public UtilisateurViewModel(@NonNull Application application) {
        super(application);
        utilisateurRepository = new UtilisateurRepository(application);
        init();
    }

    private void init() {
        utilisateurs = utilisateurRepository.get();
    }

    public void insert(Utilisateur utilisateur) {
        utilisateurRepository.insert(utilisateur);
    }

    public LiveData<List<Utilisateur>> get() {
        return utilisateurs;
    }

    public LiveData<Utilisateur> get(int id) {
        return utilisateurRepository.get(id);
    }
}

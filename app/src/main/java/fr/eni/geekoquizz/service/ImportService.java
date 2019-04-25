package fr.eni.geekoquizz.service;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.eni.geekoquizz.bo.Question;
import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.bo.QuizzTheme;
import fr.eni.geekoquizz.bo.Reponse;
import fr.eni.geekoquizz.bo.Theme;
import fr.eni.geekoquizz.bo.Utilisateur;
import fr.eni.geekoquizz.view_model.QuestionViewModel;
import fr.eni.geekoquizz.view_model.QuizzThemeViewModel;
import fr.eni.geekoquizz.view_model.QuizzViewModel;
import fr.eni.geekoquizz.view_model.ReponseViewModel;
import fr.eni.geekoquizz.view_model.ThemeViewModel;
import fr.eni.geekoquizz.view_model.TypeViewModel;
import fr.eni.geekoquizz.view_model.UtilisateurViewModel;

public class ImportService {

    private String urlAPI ="http://yoda.pboudeaud.fr/api";

    AppCompatActivity view;

    List<Theme> themes;

    List<fr.eni.geekoquizz.bo.Type> types;

    List<Utilisateur> utilisateurs;

    public ImportService(AppCompatActivity view)
    {
        this.view = view;
    }

    public void importListQuizz()
    {
        QuizzViewModel quizzViewModel = ViewModelProviders.of(view).get(QuizzViewModel.class);
        quizzViewModel.get().observe(view, new Observer<List<Quizz>>() {
            @Override
            public void onChanged(@Nullable List<Quizz> quizzes) {
                Log.i("Import", quizzes.toString());

            }
        });
    }

    private String importFromBack(String url) {
        String json = "";
        GetJSON task = new GetJSON();
        try {
            json = task.execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return json;
    }

    public void importAll() {
        importThemes();
        importTypes();
        importUtilisateurs();
        importQuizz();
    }

    public void importThemes() {
        String json = importFromBack(urlAPI + "/themes");

        List<fr.eni.geekoquizz.boback.Theme> listThemeBack;
        List<Theme>lesThemes = new ArrayList<>();
        Type listType = new TypeToken<ArrayList<fr.eni.geekoquizz.boback.Theme>>(){}.getType();
        listThemeBack = new Gson().fromJson(json,listType);
        ThemeViewModel themeVM = ViewModelProviders.of(view).get(ThemeViewModel.class);
        for (fr.eni.geekoquizz.boback.Theme themeBO: listThemeBack)
        {
            Theme theme = themeBO.toTheme();
            lesThemes.add(theme);
            try{
                themeVM.insert(theme);
            } catch(Exception ex) {
                Log.i("Import", ex.getMessage());
            }


        }
        themes = lesThemes;
    }

    public void importTypes() {
        String json = importFromBack(urlAPI + "/types");

        List<fr.eni.geekoquizz.boback.Type> listTypeBack;
        List<fr.eni.geekoquizz.bo.Type>lesTypes = new ArrayList<>();
        Type listType = new TypeToken<ArrayList<fr.eni.geekoquizz.boback.Type>>(){}.getType();
        listTypeBack = new Gson().fromJson(json,listType);
        TypeViewModel typeVM = ViewModelProviders.of(view).get(TypeViewModel.class);
        for (fr.eni.geekoquizz.boback.Type typeBO: listTypeBack)
        {fr.eni.geekoquizz.bo.Type type = typeBO.toType();
            lesTypes.add(type);

            try{
                typeVM.insert(type);
            } catch(Exception ex) {
                Log.i("Import", ex.getMessage());
            }

        }

    }

    public void importUtilisateurs() {
        String json = importFromBack(urlAPI + "/utilisateurs");

        List<fr.eni.geekoquizz.boback.Utilisateur> listUtilisateurBack;
        List<Utilisateur>lesUtilisateurs = new ArrayList<>();
        Type listType = new TypeToken<ArrayList<fr.eni.geekoquizz.boback.Utilisateur>>(){}.getType();
        listUtilisateurBack = new Gson().fromJson(json,listType);
        UtilisateurViewModel utilisateurVM = ViewModelProviders.of(view).get(UtilisateurViewModel.class);
        for (fr.eni.geekoquizz.boback.Utilisateur utilisateurBO: listUtilisateurBack)
        {
            Utilisateur utilisateur = utilisateurBO.toUtilisateur();
            lesUtilisateurs.add(utilisateur);
            try{
                utilisateurVM.insert(utilisateur);
            } catch(Exception ex) {
                Log.i("Import", ex.getMessage());
            }


        }
        utilisateurs = lesUtilisateurs;
        //utilisateurVM.insertAll(lesUtilisateurs);
    }

    private void importReponse(Reponse reponse) {
        ReponseViewModel reponseVM = ViewModelProviders.of(view).get(ReponseViewModel.class);
        reponseVM.insert(reponse);
    }

    private void importQuestion(Question question) {
        QuestionViewModel questionVM = ViewModelProviders.of(view).get(QuestionViewModel.class);
        questionVM.insert(question);
        for(Reponse reponse: question.getReponses()) {
            importReponse(reponse);
        }
    }

    public void importQuizz() {
        String json = importFromBack(urlAPI + "/quizz?valid=true");

        List<fr.eni.geekoquizz.boback.Quizz> listQuizzBack;
        Type listType = new TypeToken<ArrayList<fr.eni.geekoquizz.boback.Quizz>>(){}.getType();
        listQuizzBack = new Gson().fromJson(json,listType);
        for (fr.eni.geekoquizz.boback.Quizz quizzBO: listQuizzBack)
        {
            try{
                importQuizz(quizzBO.getId());
            } catch(Exception ex) {
                Log.i("Import", ex.getMessage());
            }
        }
    }

    private void importQuizz(final int quizzId) {
        String json = importFromBack(urlAPI + "/quizz/" + quizzId);

        fr.eni.geekoquizz.boback.Quizz quizzBack;
        quizzBack = new Gson().fromJson(json, fr.eni.geekoquizz.boback.Quizz.class);
        Quizz quizz = quizzBack.toQuizz();
        QuizzViewModel quizzVM = ViewModelProviders.of(view).get(QuizzViewModel.class);
        quizzVM.insert(quizz);
        QuizzThemeViewModel quizzThemeVM = ViewModelProviders.of(view).get(QuizzThemeViewModel.class);
        for (QuizzTheme quizzTheme: quizz.getThemes()) {
            quizzThemeVM.insert(quizzTheme);
        }

        for(Question question: quizz.getQuestions()) {
            importQuestion(question);
        }
    }
}

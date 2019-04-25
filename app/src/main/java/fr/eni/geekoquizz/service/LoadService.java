package fr.eni.geekoquizz.service;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.eni.geekoquizz.activity.ImportActivity;
import fr.eni.geekoquizz.bo.Question;
import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.bo.Reponse;
import fr.eni.geekoquizz.bo.Theme;
import fr.eni.geekoquizz.bo.Type;
import fr.eni.geekoquizz.bo.Utilisateur;
import fr.eni.geekoquizz.view_model.QuestionViewModel;
import fr.eni.geekoquizz.view_model.QuizzViewModel;
import fr.eni.geekoquizz.view_model.ReponseViewModel;
import fr.eni.geekoquizz.view_model.ThemeViewModel;
import fr.eni.geekoquizz.view_model.TypeViewModel;
import fr.eni.geekoquizz.view_model.UtilisateurViewModel;

public class LoadService {
    AppCompatActivity view;
    boolean isNotFinish;

    public LoadService(AppCompatActivity view) {
        this.view = view;
    }

    public List<Quizz> loadAllQuizz() {
        final List<Quizz> listQuizz = new ArrayList<>();
        QuizzViewModel quizzVM = ViewModelProviders.of(view).get(QuizzViewModel.class);
        quizzVM.get().observe(view, new Observer<List<Quizz>>() {
            @Override
            public void onChanged(@Nullable List<Quizz> quizzes) {
                for(Quizz quizz: quizzes) {
                    Quizz unquizz = loadQuizz(quizz.getId());
                    listQuizz.add(unquizz);
                }
            }
        });
        return listQuizz;
    }

    public Quizz loadQuizz(final int quizzId) {
        final Quizz unQuizz = new Quizz();
        boolean isNotFinish = true;
        QuizzViewModel quizzViewModel = ViewModelProviders.of(view).get(QuizzViewModel.class);
        quizzViewModel.get(2).observe(view, new Observer<Quizz>() {
            @Override
            public void onChanged(@Nullable final Quizz quizz) {
                unQuizz.setId(quizz.getId());
                unQuizz.setNom(quizz.getNom());
                unQuizz.setTypeId(quizz.getTypeId());
                unQuizz.setDescription(quizz.getDescription());
                unQuizz.setDateModif(quizz.getDateModif());
                unQuizz.setDateCrea(quizz.getDateCrea());
                unQuizz.setDifficulte(quizz.getDifficulte());
                unQuizz.setVersion(quizz.getVersion());
                Log.i("ImportQuizz", quizz.toString());
                ThemeViewModel themeViewModel = ViewModelProviders.of(view).get(ThemeViewModel.class);
                themeViewModel.getByQuizz(quizz.getId()).observe(view, new Observer<List<Theme>>() {
                    @Override
                    public void onChanged(@Nullable List<Theme> themes) {
                        unQuizz.setListThemes(themes);
                    }
                });
                UtilisateurViewModel utilisateurVM = ViewModelProviders.of(view).get(UtilisateurViewModel.class);
                utilisateurVM.get(quizz.getUtilisateurId()).observe(view, new Observer<Utilisateur>() {
                    @Override
                    public void onChanged(@Nullable Utilisateur utilisateur) {
                        unQuizz.setUtilisateur(utilisateur);
                    }
                });
                TypeViewModel typeVM = ViewModelProviders.of(view).get(TypeViewModel.class);
                typeVM.get(quizz.getTypeId()).observe(view, new Observer<Type>() {
                    @Override
                    public void onChanged(@Nullable Type type) {
                        unQuizz.setType(type);
                    }
                });
                QuestionViewModel questionVM = ViewModelProviders.of(view).get(QuestionViewModel.class);
                questionVM.getByQuizz(quizz.getId()).observe(view, new Observer<List<Question>>() {
                    @Override
                    public void onChanged(@Nullable List<Question> questions) {
                        unQuizz.setQuestions(questions);
                        ReponseViewModel reponseVM = ViewModelProviders.of(view).get(ReponseViewModel.class);
                        for(final Question question: questions) {
                            reponseVM.getByQuestion(question.getId()).observe(view, new Observer<List<Reponse>>() {
                                @Override
                                public void onChanged(@Nullable List<Reponse> reponses) {
                                    Log.i("Reponses", reponses.toString());
                                    question.setReponses(reponses);
                                }
                            });
                        }
                        Log.i("Import", quizz.toString());
                        LoadService.this.isNotFinish = false;
                    }
                });
            }
        });
        while(isNotFinish){

        }
        return unQuizz;
    }
}

package fr.eni.geekoquizz.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.activity.QuizzActivity;
import fr.eni.geekoquizz.adapter.ListStatAdapter;
import fr.eni.geekoquizz.bo.Question;
import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.bo.Reponse;
import fr.eni.geekoquizz.bo.Statistique;
import fr.eni.geekoquizz.bo.Theme;
import fr.eni.geekoquizz.bo.Type;
import fr.eni.geekoquizz.bo.Utilisateur;
import fr.eni.geekoquizz.ui.infostat.InfoFragment;
import fr.eni.geekoquizz.ui.infostat.StatFragment;
import fr.eni.geekoquizz.view_model.QuestionViewModel;
import fr.eni.geekoquizz.view_model.QuizzViewModel;
import fr.eni.geekoquizz.view_model.ReponseViewModel;
import fr.eni.geekoquizz.view_model.ThemeViewModel;
import fr.eni.geekoquizz.view_model.TypeViewModel;
import fr.eni.geekoquizz.view_model.UtilisateurViewModel;

public class InfostatActivity extends AppCompatActivity {

    private Fragment monFragment = null;

    private Quizz MonQuizz = new Quizz();

    private List<Statistique> ListStat;

    private int idQuizz;

    private TextView tvNbQuestion,tvType,DescriptionQuizz,tvAuteur,tvDate,textView12;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infostat__activity);

        idQuizz = getIntent().getIntExtra("QuizzStat",0);
        //MonQuizz = Parcels.unwrap(getIntent().getParcelableExtra("QuizzStat"));

        QuizzViewModel quizzVM = ViewModelProviders.of(this).get(QuizzViewModel.class);
        quizzVM.get(idQuizz).observe(this, new Observer<Quizz>() {
            @Override
            public void onChanged(@Nullable Quizz quizz) {
                MonQuizz = quizz;
                ThemeViewModel themeViewModel = ViewModelProviders.of(InfostatActivity.this).get(ThemeViewModel.class);
                themeViewModel.getByQuizz(quizz.getId()).observe(InfostatActivity.this, new Observer<List<Theme>>() {
                    @Override
                    public void onChanged(@Nullable List<Theme> themes) {
                        MonQuizz.setListThemes(themes);
                    }
                });
                UtilisateurViewModel utilisateurVM = ViewModelProviders.of(InfostatActivity.this).get(UtilisateurViewModel.class);
                utilisateurVM.get(quizz.getUtilisateurId()).observe(InfostatActivity.this, new Observer<Utilisateur>() {
                    @Override
                    public void onChanged(@Nullable Utilisateur utilisateur) {
                        MonQuizz.setUtilisateur(utilisateur);
                    }
                });
                TypeViewModel typeVM = ViewModelProviders.of(InfostatActivity.this).get(TypeViewModel.class);
                typeVM.get(quizz.getTypeId()).observe(InfostatActivity.this, new Observer<Type>() {
                    @Override
                    public void onChanged(@Nullable Type type) {
                        MonQuizz.setType(type);
                    }
                });
                QuestionViewModel questionVM = ViewModelProviders.of(InfostatActivity.this).get(QuestionViewModel.class);
                questionVM.getByQuizz(quizz.getId()).observe(InfostatActivity.this, new Observer<List<Question>>() {
                    @Override
                    public void onChanged(@Nullable List<Question> questions) {
                        MonQuizz.setQuestions(questions);
                        ReponseViewModel reponseVM = ViewModelProviders.of(InfostatActivity.this).get(ReponseViewModel.class);
                        for(final Question question: questions) {
                            reponseVM.getByQuestion(question.getId()).observe(InfostatActivity.this, new Observer<List<Reponse>>() {
                                @Override
                                public void onChanged(@Nullable List<Reponse> reponses) {
                                    question.setReponses(reponses);
                                }
                            });
                        }
                        Log.i("Import", MonQuizz.toString());
                        InfostatActivity.this.setTitle(MonQuizz.getNom());

                        if (savedInstanceState == null)
                        {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_interchangeable, InfoFragment.newInstance(MonQuizz)).commit();
                        }
                    }
                });

            }
        });
    }

    public void GetBtnInfo(View view) {
        //Toast.makeText(this, "Bouton Information", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_interchangeable,InfoFragment.newInstance(MonQuizz)).commit();
    }

    public void GetBtnPlay(View v) {
        Intent intentPlay = new Intent(v.getContext(), QuizzActivity.class);
        intentPlay.putExtra("QuizzPlay", Parcels.wrap(MonQuizz));
        v.getContext().startActivity(intentPlay);
    }

    public void GetBtnStat(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_interchangeable, StatFragment.newInstance(MonQuizz)).commit();
    }

    public void GetDescriptTheme(View view){
        int IdTheme = 0;
        switch(view.getId()){
            case R.id.ivTheme1:
                IdTheme = 0;
                break;
            case R.id.ivTheme2:
                IdTheme = 1;
                break;
            case R.id.ivTheme3:
                IdTheme = 2;
                break;
            case R.id.ivTheme4:
                IdTheme = 3;
                break;
            case R.id.ivTheme5:
                IdTheme = 4;
                break;
            case R.id.ivTheme6:
                IdTheme = 5;
                break;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        builder.setTitle(MonQuizz.getThemes().get(IdTheme).getTheme().getNom());
        builder.setIcon(Integer.valueOf(MonQuizz.getThemes().get(IdTheme).getTheme().getIcon()));
        builder.setMessage(MonQuizz.getThemes().get(IdTheme).getTheme().getDescription());
        builder.setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

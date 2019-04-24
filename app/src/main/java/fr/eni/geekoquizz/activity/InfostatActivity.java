package fr.eni.geekoquizz.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

public class InfostatActivity extends AppCompatActivity {

    private Fragment monFragment = null;

    private Quizz MonQuizz;

    private List<Statistique> ListStat;

    private TextView tvNbQuestion,tvType,DescriptionQuizz,tvAuteur,tvDate,textView12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infostat__activity);

        int IdQuizz = getIntent().getIntExtra("IdQuizz",0);

        Type MonType = new Type("QCM","Que des QCMs","");
        Utilisateur User = new Utilisateur(1,"Valoo22",new Date());
        //Liste des Themes
        List<Theme> ListThemes = new ArrayList<>();
            ListThemes.add(new Theme("Jeu Vidéo","Description : Jeu Vidéo",String.valueOf(R.drawable.jeux_video)));
            ListThemes.add(new Theme("serie_tele","Description : serie_tele",String.valueOf(R.drawable.serie_tele)));
            ListThemes.add(new Theme("film","Description : film",String.valueOf(R.drawable.film)));
            ListThemes.add(new Theme("anime","Description : anime",String.valueOf(R.drawable.anime)));
            ListThemes.add(new Theme("pop_culture","Description : pop_culture",String.valueOf(R.drawable.pop_culture)));
            ListThemes.add(new Theme("livre","Description : livre",String.valueOf(R.drawable.livre)));
            ListThemes.add(new Theme("livre","Description : livre",String.valueOf(R.drawable.livre)));

        List<Question> ListQuest = new ArrayList<>();
        List<Reponse> ListReps = new ArrayList<>();
            ListQuest.add(new Question("Ceci est une question ?",new Date(), new Date(), String.valueOf(R.drawable.quizz1_01), 10, 5, ListReps));
            ListQuest.add(new Question("Ceci est une question ?",new Date(), new Date(), String.valueOf(R.drawable.quizz1_02), 10, 5, ListReps));
            ListQuest.add(new Question("Ceci est une question ?",new Date(), new Date(), String.valueOf(R.drawable.quizz1_03), 10, 5, ListReps));

        MonQuizz = new Quizz(IdQuizz, "Jeux-vidéo années 90",(float)2.6f, new Date(), new Date(2019, 04, 18), "Description : Redécouvrez les jeux-vidéo de son age d'or grâce a ce petit quizz développé par mes soins.", 1, User, null, ListThemes, MonType, null);
        MonQuizz.setQuestions(ListQuest);

        List<Statistique> ListStat0 = new ArrayList<>();
            ListStat0.add(new Statistique(new Date(2019, 04, 18),56,3,MonQuizz,User));
            ListStat0.add(new Statistique(new Date(),75,2,MonQuizz,User));
            ListStat0.add(new Statistique(new Date(),86,1,MonQuizz,User));
        ListStat = ListStat0;
        MonQuizz.setStatistiques(ListStat);

        this.setTitle(MonQuizz.getNom());

        if (savedInstanceState == null)
        {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_interchangeable, InfoFragment.newInstance(MonQuizz)).commit();
        }
    }

    public void GetBtnInfo(View view) {
        //Toast.makeText(this, "Bouton Information", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_interchangeable,InfoFragment.newInstance(MonQuizz)).commit();
    }

    public void GetBtnPlay(View v) {
        Intent intent = new Intent(v.getContext(), QuizzActivity.class);
        v.getContext().startActivity(intent);

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

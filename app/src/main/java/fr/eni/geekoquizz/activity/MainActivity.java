package fr.eni.geekoquizz.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.adapter.ListQuizzAdapter;
import fr.eni.geekoquizz.adapter.ModifQuizzAdapter;
import fr.eni.geekoquizz.bo.Question;
import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.bo.Reponse;
import fr.eni.geekoquizz.bo.Statistique;
import fr.eni.geekoquizz.bo.Theme;
import fr.eni.geekoquizz.bo.Type;
import fr.eni.geekoquizz.bo.Utilisateur;
import fr.eni.geekoquizz.service.ImportQuizz;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static List<Quizz> lesQuizz = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        switchLayout(R.id.nav_list_quizz);
        toggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        NavigationView navigationView = findViewById(R.id.nav_view);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        View headerView = navigationView.getHeaderView(0);
        TextView tvPseudo = (TextView)headerView.findViewById(R.id.tvPseudo);
        tvPseudo.setText(sp.getString("example_text",""));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id)
        {
            case R.id.nav_list_quizz:
                switchLayout(R.id.nav_list_quizz);
                break;
            case R.id.nav_modif_quizz:
                switchLayout(R.id.nav_modif_quizz);
                break;
            case R.id.nav_theme:
                switchLayout(R.id.nav_theme);
                break;
            case R.id.nav_type_de_quizz:
                switchLayout(R.id.nav_type_de_quizz);
                break;
            case R.id.nav_parametre:
                Intent intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                break;
            default:

                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void switchLayout(int idItem)
    {
        Type MonType = new Type("QCM","Que des QCMs","");
        Utilisateur User = new Utilisateur(1,"Valoo22",new Date());

        //Liste des Themes
        List<Theme> ListThemes = new ArrayList<>();
            Theme Theme1 = new Theme("anime","Description : anime","anime");ListThemes.add(Theme1);
            Theme Theme2 = new Theme("Bande Déssinée","Description : Bande Déssinée","BD");ListThemes.add(Theme2);//sans icon
            //Theme Theme3 = new Theme("film","Description : film","film");ListThemes.add(Theme3);
            //Theme Theme4 = new Theme("Jeu Vidéo","Description : Jeu Vidéo","jeux_video");ListThemes.add(Theme4);
            Theme Theme5 = new Theme("livre","Description : livre","livre");ListThemes.add(Theme5);
            Theme Theme6 = new Theme("Musique","Description : Musique","musique");ListThemes.add(Theme6);//sans icon
            Theme Theme7 = new Theme("Personnage","Description : Personnage","perso");ListThemes.add(Theme7);//sans icon
            Theme Theme8 = new Theme("pop_culture","Description : pop_culture","pop_culture");ListThemes.add(Theme8);
            Theme Theme9 = new Theme("Science Fiction","Description : Science Fiction","sf");ListThemes.add(Theme9);//sans icon
            Theme Theme10 = new Theme("Série télé","Description : Série télé","serie_tele");ListThemes.add(Theme10);

        List<Quizz> ListQuizz = new ArrayList<>();

        List<Question> ListQuest = new ArrayList<>();
        List<Reponse> ListReps = new ArrayList<>();
            ListQuest.add(new Question("Ceci est une question ?",new Date(), new Date(), String.valueOf(R.drawable.quizz1_01), 10, 5, ListReps));
            ListQuest.add(new Question("Ceci est une question ?",new Date(), new Date(), String.valueOf(R.drawable.quizz1_02), 10, 5, ListReps));
            ListQuest.add(new Question("Ceci est une question ?",new Date(), new Date(), String.valueOf(R.drawable.quizz1_03), 10, 5, ListReps));

        Quizz MonQuizz = new Quizz(5, "Jeux-vidéo années 90",(float)2.6f, new Date(2019, 04, 18), new Date(), "Description : Redécouvrez les jeux-vidéo de son age d'or grâce a ce petit quizz développé par mes soins.", 1, User, ListQuest, ListThemes, MonType, null);
        ListQuizz.add(MonQuizz);

        ListThemes = new ArrayList<>();
            Theme Theme11 = new Theme("anime","Description : anime","anime");ListThemes.add(Theme11);

            Quizz MonDeuxiemeQuizz = new Quizz(8, "Série Télé",(float)3.5f, new Date(), new Date(2019, 04, 18), "Description : C'est génial, je suis trop content de faire ça !", 2, User, ListQuest, ListThemes, MonType, null);
        ListQuizz.add(MonDeuxiemeQuizz);


        RecyclerView recyclerViewQuizz = (RecyclerView) findViewById(R.id.rvQuizz);
        ImageButton btnAddQuizz = (ImageButton)findViewById(R.id.btnAddQuizz);
        recyclerViewQuizz.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerQuizz = new LinearLayoutManager(this);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerViewQuizz.getContext(),DividerItemDecoration.VERTICAL);
        recyclerViewQuizz.addItemDecoration(divider);
        recyclerViewQuizz.setLayoutManager(layoutManagerQuizz);
        switch(idItem)
        {
            case R.id.nav_list_quizz :
                this.setTitle("Liste des Quizz");
                RecyclerView.Adapter LisyAdapterQuizz = new ListQuizzAdapter(lesQuizz);
                recyclerViewQuizz.setAdapter(LisyAdapterQuizz);
                btnAddQuizz.setVisibility(View.GONE);
                break;
            case R.id.nav_modif_quizz :
                this.setTitle("Modifier un Quizz");
                RecyclerView.Adapter ModifAdapterQuizz = new ModifQuizzAdapter(lesQuizz);
                recyclerViewQuizz.setAdapter(ModifAdapterQuizz);
                btnAddQuizz.setVisibility(View.VISIBLE);
                break;
            case R.id.nav_theme :

                break;
            case R.id.nav_type_de_quizz :

                break;
        }
    }
}

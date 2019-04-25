package fr.eni.geekoquizz.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.adapter.ListQuizzAdapter;
import fr.eni.geekoquizz.adapter.ModifQuizzAdapter;
import fr.eni.geekoquizz.bo.Question;
import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.bo.Reponse;
import fr.eni.geekoquizz.bo.Theme;
import fr.eni.geekoquizz.bo.Type;
import fr.eni.geekoquizz.bo.Utilisateur;
import fr.eni.geekoquizz.service.ImportService;
import fr.eni.geekoquizz.view_model.QuestionViewModel;
import fr.eni.geekoquizz.view_model.QuizzViewModel;
import fr.eni.geekoquizz.view_model.ReponseViewModel;
import fr.eni.geekoquizz.view_model.ThemeViewModel;
import fr.eni.geekoquizz.view_model.TypeViewModel;
import fr.eni.geekoquizz.view_model.UtilisateurViewModel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static List<Quizz> lesQuizz = new ArrayList<>();
    public static boolean isNotLoaded = true;
    ImportService importService;

    public static MainActivity INSTANCE;

    public MainActivity() {
        this.importService = new ImportService(this);
        INSTANCE = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isNotLoaded) {
            isNotLoaded = !isNotLoaded;
            importService.importAll();
//            QuizzViewModel quizzViewModel = ViewModelProviders.of(this).get(QuizzViewModel.class);
        QuizzViewModel quizzViewModel = QuizzViewModel.getInstance(this);
        quizzViewModel.get().observe(this, new Observer<List<Quizz>>() {
            @Override
            public void onChanged(@Nullable List<Quizz> quizzes) {
                MainActivity.lesQuizz = quizzes;
                for (final Quizz quizz: quizzes) {
                    ThemeViewModel themeViewModel = ViewModelProviders.of(MainActivity.this).get(ThemeViewModel.class);
                    themeViewModel.getByQuizz(quizz.getId()).observe(MainActivity.this, new Observer<List<Theme>>() {
                        @Override
                        public void onChanged(@Nullable List<Theme> themes) {
                            quizz.setListThemes(themes);
                        }
                    });
                    UtilisateurViewModel utilisateurVM = ViewModelProviders.of(MainActivity.this).get(UtilisateurViewModel.class);
                    utilisateurVM.get(quizz.getUtilisateurId()).observe(MainActivity.this, new Observer<Utilisateur>() {
                        @Override
                        public void onChanged(@Nullable Utilisateur utilisateur) {
                            quizz.setUtilisateur(utilisateur);
                        }
                    });
                    TypeViewModel typeVM = ViewModelProviders.of(MainActivity.this).get(TypeViewModel.class);
                    typeVM.get(quizz.getTypeId()).observe(MainActivity.this, new Observer<Type>() {
                        @Override
                        public void onChanged(@Nullable Type type) {
                            quizz.setType(type);
                        }
                    });
                    QuestionViewModel questionVM = ViewModelProviders.of(MainActivity.this).get(QuestionViewModel.class);
                    questionVM.getByQuizz(quizz.getId()).observe(MainActivity.this, new Observer<List<Question>>() {
                        @Override
                        public void onChanged(@Nullable List<Question> questions) {
                            quizz.setQuestions(questions);
                            ReponseViewModel reponseVM = ViewModelProviders.of(MainActivity.this).get(ReponseViewModel.class);
                            for(final Question question: questions) {
                                reponseVM.getByQuestion(question.getId()).observe(MainActivity.this, new Observer<List<Reponse>>() {
                                    @Override
                                    public void onChanged(@Nullable List<Reponse> reponses) {
                                        question.setReponses(reponses);
                                    }
                                });
                            }
                            Toast.makeText(MainActivity.this, "Loading finished", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        }


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
        TextView tvPseudo = (TextView) headerView.findViewById(R.id.tvPseudo);
        tvPseudo.setText(sp.getString("example_text", ""));
        /*importService.importThemes();
        importService.importTypes();
        importService.importUtilisateurs();
        importService.importQuizz();*/
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
        switch (id) {
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
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:

                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void switchLayout(final int idItem) {
        final RecyclerView recyclerViewQuizz = (RecyclerView) findViewById(R.id.rvQuizz);
        final ImageButton btnAddQuizz = (ImageButton) findViewById(R.id.btnAddQuizz);
        recyclerViewQuizz.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerQuizz = new LinearLayoutManager(this);
        final DividerItemDecoration divider = new DividerItemDecoration(recyclerViewQuizz.getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewQuizz.addItemDecoration(divider);
        recyclerViewQuizz.setLayoutManager(layoutManagerQuizz);
        switch (idItem) {
            case R.id.nav_list_quizz:
                MainActivity.this.setTitle("Liste des Quizz");
                RecyclerView.Adapter LisyAdapterQuizz = new ListQuizzAdapter(lesQuizz);
                recyclerViewQuizz.setAdapter(LisyAdapterQuizz);
                btnAddQuizz.setVisibility(View.GONE);
                break;
            case R.id.nav_modif_quizz:
                MainActivity.this.setTitle("Modifier un Quizz");
                RecyclerView.Adapter ModifAdapterQuizz = new ModifQuizzAdapter(lesQuizz);
                recyclerViewQuizz.setAdapter(ModifAdapterQuizz);
                btnAddQuizz.setVisibility(View.VISIBLE);
                break;
            case R.id.nav_theme:

                break;
            case R.id.nav_type_de_quizz:

                break;
        }
    }

}

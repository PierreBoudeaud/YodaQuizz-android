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

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.adapter.ListQuizzAdapter;
import fr.eni.geekoquizz.adapter.ModifQuizzAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        String[][] infos = {
                {"-700094","-700095","-700096","Jeux-vidéo années 90","50","2.5f","QCM (1 réponse possible)","-700002","-700002","-700002","-700002","-700002","Valoo22","12/04/2019","Description : Redécouvrez les jeux-vidéo de son age d'or grâce a ce petit quizz développé par mes soins."},
                {"-700094","-700095","-700096","Jeux-vidéo années 90","50","2.5f","QCM (1 réponse possible)","-700002","-700002","-700002","-700002","-700002","Valoo22","12/04/2019","Description : Redécouvrez les jeux-vidéo de son age d'or grâce a ce petit quizz développé par mes soins."}
        };
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
                RecyclerView.Adapter LisyAdapterQuizz = new ListQuizzAdapter(infos);
                recyclerViewQuizz.setAdapter(LisyAdapterQuizz);
                btnAddQuizz.setVisibility(View.GONE);
                break;
            case R.id.nav_modif_quizz :
                this.setTitle("Modifier un Quizz");
                RecyclerView.Adapter ModifAdapterQuizz = new ModifQuizzAdapter(infos);
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

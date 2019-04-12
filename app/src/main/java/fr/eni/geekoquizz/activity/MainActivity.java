package fr.eni.geekoquizz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.adapter.QuizzAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView.LayoutManager LayoutManagerQuizz;

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
        this.setTitle(R.string.title_activity_list_quizz);

        RecyclerView recyclerViewQuizz = (RecyclerView) findViewById(R.id.rvQuizz);
        recyclerViewQuizz.setHasFixedSize(true);

        LayoutManagerQuizz = new LinearLayoutManager(this);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerViewQuizz.getContext(),DividerItemDecoration.VERTICAL);

        recyclerViewQuizz.setLayoutManager(LayoutManagerQuizz);

        String[][] infos = {
                {"-700094","-700095","-700096","Jeux-vidéo années 90","50","2.5f","QCM (1 réponse possible)","-700002","-700002","-700002","-700002","-700002","Valoo22","12/04/2019","Description : Redécouvrez les jeux-vidéo de son age d'or grâce a ce petit quizz développé par mes soins."},
                {"-700094","-700095","-700096","Jeux-vidéo années 90","50","2.5f","QCM (1 réponse possible)","-700002","-700002","-700002","-700002","-700002","Valoo22","12/04/2019","Description : Redécouvrez les jeux-vidéo de son age d'or grâce a ce petit quizz développé par mes soins."}
        };

        RecyclerView.Adapter AdapterQuizz = new QuizzAdapter(infos);

        recyclerViewQuizz.setAdapter(AdapterQuizz);

        recyclerViewQuizz.addItemDecoration(divider);

        toggle.syncState();
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

                break;
            case R.id.nav_modif_quizz:

                break;
            case R.id.nav_parametre:
                Intent intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_theme:

                break;
            case R.id.nav_type_de_quizz:

                break;
            default:

                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

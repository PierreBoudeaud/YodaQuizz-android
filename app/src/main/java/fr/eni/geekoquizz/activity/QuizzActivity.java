package fr.eni.geekoquizz.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.bo.Question;
import fr.eni.geekoquizz.bo.Reponse;

public class QuizzActivity extends AppCompatActivity {

    private ProgressBar time;
    private int timelapsed;
    private Reponse RepA = new Reponse("Blanc",true);
    private Reponse RepB = new Reponse("Bleu",false);
    private Reponse RepC = new Reponse("Rouge",false);
    private Reponse RepD = new Reponse("Jaune",false);
    List<Reponse> lesReponses = new ArrayList<>();
    private Question uneQuestion = new Question("Quelle est la couleur du prépuce blanc d'Henry IV ?",new Date(),new Date(),"",0,0, lesReponses);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        time = (ProgressBar)findViewById(R.id.progressBar);

        CountDownTimer timer = new CountDownTimer(30000,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setProgress(300 - ((int)millisUntilFinished*10/1000));
            }

            @Override
            public void onFinish() {
                Toast.makeText(QuizzActivity.this,"T'est nul connard",Toast.LENGTH_LONG).show();
            }
        }.start();
    }


    public void onClickRepA(View view) {

    }

    public void onClickRepB(View view) {
    }

    public void onClickRepC(View view) {
    }

    public void onClickRepD(View view) {
    }
}

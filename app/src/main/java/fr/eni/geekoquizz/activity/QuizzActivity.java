package fr.eni.geekoquizz.activity;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.bo.Question;
import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.bo.Reponse;
import fr.eni.geekoquizz.bo.Statistique;
import fr.eni.geekoquizz.bo.Theme;
import fr.eni.geekoquizz.bo.Type;
import fr.eni.geekoquizz.bo.Utilisateur;

public class QuizzActivity extends AppCompatActivity {

    private ProgressBar time;
    private TextView tvQuestion;
    private TextView tvNbQuestion;
    private Button btnRepA, btnRepB, btnRepC, btnRepD, btnJoker;
    private CountDownTimer timer, timeafterlose, timeafterwin;
    private Boolean isGoodResponse;
    private int nbCorrectResponse, nbResponseFound, indexQuestion, Score;
    private Reponse RepA1 = new Reponse("Blanc",true),RepB1 = new Reponse("Bleu",false),RepC1 = new Reponse("Rouge",false),RepD1 = new Reponse("Jaune",false);
    private Reponse RepA2 =new Reponse("Valoo", true), RepB2=new Reponse("Pierre", true), RepC2=new Reponse("Antoine", true), RepD2=new Reponse("Anthony", false);
    private Question Question1 = new Question("Quelle est la couleur du cheval blanc d'Henry IV ?",new Date(),new Date(),"",0,0,  new ArrayList<Reponse>(){{add(RepA1);add(RepB1); add(RepC1); add(RepD1);}});
    private Question Question2 = new Question("Quelle sont les créateurs de cette application",new Date(),new Date(),"",0,0,  new ArrayList<Reponse>(){{add(RepA2);add(RepB2); add(RepC2); add(RepD2);}});
    private Question uneQuestion;
    private Utilisateur unUtilisateur = new Utilisateur();
    private List<Theme> lesThemes = new ArrayList<>();
    private Type unType = new Type();
    private List<Statistique> lesStatistiques = new ArrayList<>();
    private Quizz unQuizz = new Quizz(0,"Quizz trop bien",2.5f,new Date(),new Date(), "Petite Quizz de test", 1, unUtilisateur, new ArrayList<Question>(){{add(Question1);add(Question2);}}, lesThemes, unType, lesStatistiques);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        this.setTitle(unQuizz.getNom());
        tvQuestion = findViewById(R.id.tvQuestion);
        tvNbQuestion = findViewById(R.id.tvNbQuestionquizz);
        btnRepA = findViewById(R.id.btn_repA);
        btnRepB = findViewById(R.id.btn_repB);
        btnRepC = findViewById(R.id.btn_repC);
        btnRepD = findViewById(R.id.btn_repD);
        btnJoker = findViewById(R.id.btn_joker);
        time = findViewById(R.id.progressBar);
        timer = new CountDownTimer(30000,100)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                time.setProgress(300 - ((int)millisUntilFinished*10/1000));
            }
            @Override
            public void onFinish()
            {
                btnRepA.setClickable(false);
                btnRepB.setClickable(false);
                btnRepC.setClickable(false);
                btnRepD.setClickable(false);
                timer.cancel();
                if(isGoodResponse)
                {
                    Toast.makeText(QuizzActivity.this,"Bien joué",Toast.LENGTH_LONG).show();
                    timeafterwin = new CountDownTimer(3000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            if((indexQuestion+1)<=unQuizz.getQuestions().size())
                            initQuestion(unQuizz.getQuestions().get(indexQuestion),unQuizz.getQuestions().size());
                            else
                                onQuizzFinished();
                        }
                    }.start();
                }
                else
                {
                    Toast.makeText(QuizzActivity.this,"T'est nul !",Toast.LENGTH_LONG).show();
                    timeafterlose = new CountDownTimer(3000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            if((indexQuestion+1)<=unQuizz.getQuestions().size())
                            initQuestion(unQuizz.getQuestions().get(indexQuestion),unQuizz.getQuestions().size());
                            else
                                onQuizzFinished();
                        }
                    }.start();
                }
            }
        };
        initQuizz(unQuizz);
    }

    public void initQuizz(Quizz unQuizz)
    {
        Score = 0;
        indexQuestion = 0;
        initQuestion(unQuizz.getQuestions().get(indexQuestion),unQuizz.getQuestions().size());
    }

    public void initQuestion(Question puneQuestion, int nbQuestion)
    {
        btnRepA.setClickable(true);
        btnRepB.setClickable(true);
        btnRepC.setClickable(true);
        btnRepD.setClickable(true);
        btnRepA.setBackgroundColor(Color.TRANSPARENT);
        btnRepB.setBackgroundColor(Color.TRANSPARENT);
        btnRepC.setBackgroundColor(Color.TRANSPARENT);
        btnRepD.setBackgroundColor(Color.TRANSPARENT);
        isGoodResponse = false;
        this.uneQuestion = puneQuestion;
        nbCorrectResponse = uneQuestion.getNbCorrectResponse();
        nbResponseFound = 0;
        tvQuestion.setText(uneQuestion.getIntitule());
        tvNbQuestion.setText("Question N°"+ (indexQuestion + 1)+"/"+nbQuestion);
        btnRepA.setText(uneQuestion.getReponses().get(0).getNom());
        btnRepB.setText(uneQuestion.getReponses().get(1).getNom());
        btnRepC.setText(uneQuestion.getReponses().get(2).getNom());
        btnRepD.setText(uneQuestion.getReponses().get(3).getNom());
        indexQuestion = indexQuestion+1;
        timer.start();
    }

    public void onClickRepA(View view) {
        if(uneQuestion.getReponses().get(0).isCorrect())
        {
            if (!uneQuestion.hasMultipleResponses())
            {
                btnRepA.setBackgroundColor(Color.GREEN);
                isGoodResponse = true;
                Score = Score + 1;
                timer.onFinish();
            }
            else
            {
                btnRepA.setBackgroundColor(Color.GREEN);
                nbResponseFound = nbResponseFound + 1;
                if(nbResponseFound == nbCorrectResponse)
                {
                    isGoodResponse = true;
                    Score = Score + 1;
                    timer.onFinish();
                }
            }
        }
        else
        {
            btnRepA.setBackgroundColor(Color.RED);
            isGoodResponse = false;
            timer.onFinish();
        }
    }

    public void onClickRepB(View view) {
        if(uneQuestion.getReponses().get(1).isCorrect())
        {
            if (!uneQuestion.hasMultipleResponses())
            {
                btnRepB.setBackgroundColor(Color.GREEN);
                isGoodResponse = true;
                Score = Score + 1;
                timer.onFinish();
            }
            else
            {
                btnRepB.setBackgroundColor(Color.GREEN);
                nbResponseFound = nbResponseFound + 1;
                if(nbResponseFound == nbCorrectResponse)
                {
                    isGoodResponse = true;
                    Score = Score + 1;
                    timer.onFinish();
                }
            }
        }
        else
        {
            btnRepB.setBackgroundColor(Color.RED);
            isGoodResponse = false;
            timer.onFinish();
        }
    }

    public void onClickRepC(View view) {
        if(uneQuestion.getReponses().get(2).isCorrect())
        {
            if (!uneQuestion.hasMultipleResponses())
            {
                btnRepC.setBackgroundColor(Color.GREEN);
                isGoodResponse = true;
                Score = Score + 1;
                timer.onFinish();
            }
            else
            {
                btnRepC.setBackgroundColor(Color.GREEN);
                nbResponseFound = nbResponseFound + 1;
                if(nbResponseFound == nbCorrectResponse)
                {
                    isGoodResponse = true;
                    Score = Score + 1;
                    timer.onFinish();
                }
            }
        }
        else
        {
            btnRepC.setBackgroundColor(Color.RED);
            isGoodResponse = false;
            timer.onFinish();
        }
    }

    public void onClickRepD(View view) {
        if(uneQuestion.getReponses().get(3).isCorrect())
        {
            if (!uneQuestion.hasMultipleResponses())
            {
                btnRepD.setBackgroundColor(Color.GREEN);
                isGoodResponse = true;
                Score = Score + 1;
                timer.onFinish();
            }
            else
            {
                btnRepD.setBackgroundColor(Color.GREEN);
                nbResponseFound = nbResponseFound + 1;
                if(nbResponseFound == nbCorrectResponse)
                {
                    isGoodResponse = true;
                    Score = Score + 1;
                    timer.onFinish();
                }
            }
        }
        else
        {
            btnRepD.setBackgroundColor(Color.RED);
            isGoodResponse = false;
            timer.onFinish();
        }
    }

    public void bntJokerOnCkick(View view) {
        Toast.makeText(this, "Démerde Toi !!!",Toast.LENGTH_LONG).show();
        btnJoker.setClickable(false);
        btnJoker.setBackgroundColor(Color.LTGRAY);
    }

    public void onQuizzFinished()
    {
        setContentView(R.layout.quizz_finish);
        ImageView imgGif = findViewById(R.id.ivGif);
        TextView tvResultat = findViewById(R.id.tvResultat);
        tvResultat.setText("Votre Score = " + Score);
        Glide.with(this).load("file:///android_asset/imggif.gif").into(imgGif);
    }
}

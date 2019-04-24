package fr.eni.geekoquizz.activity;

import android.app.Dialog;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    private int nbCorrectResponse, nbResponseFound, indexQuestion, nbCorrectRep,NbQuestion,points,totalPoints;
    private Reponse RepA1 = new Reponse("Blanc",true),RepB1 = new Reponse("Bleu",false),RepC1 = new Reponse("Rouge",false),RepD1 = new Reponse("Jaune",false);
    private Reponse RepA2 =new Reponse("Valoo", true), RepB2=new Reponse("Pierre", true), RepC2=new Reponse("Antoine", true), RepD2=new Reponse("Luke Skywalker", false);
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
        timer = new CountDownTimer(10000,100)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                points = ((int)millisUntilFinished*10/1000);
                time.setProgress(100 - ((int)millisUntilFinished*10/1000));
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
                    PopUpInterQuestion(true);
                }
                else
                {
                    PopUpInterQuestion(false);
                }
            }
        };
        initQuizz(unQuizz);
    }

    public void PopUpInterQuestion(boolean Correct){
        final Dialog dialog = new Dialog(QuizzActivity.this);
        dialog.setContentView(R.layout.popup_post_question);
        dialog.setCancelable(false);
        ImageView image = (ImageView) dialog.findViewById(R.id.ivYoda);
        TextView text = (TextView) dialog.findViewById(R.id.tvPhraseYoda);

        int random = new Random().nextInt((15 - 1)+1)+1;

        switch(random){
            case 1:
                image.setImageResource(R.drawable.yoda1);
                text.setText(getString(R.string.phraseyoda_1));
                break;
            case 2:
                image.setImageResource(R.drawable.yoda2);
                text.setText(getString(R.string.phraseyoda_2));
                break;
            case 3:
                image.setImageResource(R.drawable.yoda3);
                text.setText(getString(R.string.phraseyoda_3));
                break;
            case 4:
                image.setImageResource(R.drawable.yoda4);
                text.setText(getString(R.string.phraseyoda_4));
                break;
            case 5:
                image.setImageResource(R.drawable.yoda5);
                text.setText(getString(R.string.phraseyoda_5));
                break;
            case 6:
                image.setImageResource(R.drawable.yoda6);
                text.setText(getString(R.string.phraseyoda_6));
                break;
            case 7:
                image.setImageResource(R.drawable.yoda7);
                text.setText(getString(R.string.phraseyoda_7));
                break;
            case 8:
                image.setImageResource(R.drawable.yoda8);
                text.setText(getString(R.string.phraseyoda_8));
                break;
            case 9:
                image.setImageResource(R.drawable.yoda9);
                text.setText(getString(R.string.phraseyoda_9));
                break;
            case 10:
                image.setImageResource(R.drawable.yoda10);
                text.setText(getString(R.string.phraseyoda_10));
                break;
            case 11:
                image.setImageResource(R.drawable.yoda11);
                text.setText(getString(R.string.phraseyoda_11));
                break;
            case 12:
                image.setImageResource(R.drawable.yoda12);
                text.setText(getString(R.string.phraseyoda_12));
                break;
            case 13:
                image.setImageResource(R.drawable.yoda13);
                text.setText(getString(R.string.phraseyoda_13));
                break;
            case 14:
                image.setImageResource(R.drawable.yoda14);
                text.setText(getString(R.string.phraseyoda_14));
                break;
            case 15:
                image.setImageResource(R.drawable.yoda15);
                text.setText(getString(R.string.phraseyoda_15));
                break;
        }


        TextView text1 = (TextView) dialog.findViewById(R.id.tvReponseRes);
        //Toast.makeText(this, ""+ points, Toast.LENGTH_SHORT).show();
        if(points == 0){
            text1.setText(getString(R.string.reponseTime));
        }else{
            if(points <= 20){
                points = 3;
            }else{
                points = points /10;
            }
            if(Correct == true){
                text1.setText(getString(R.string.reponseCorrect));
            }else{
                points = 0;
                text1.setText(getString(R.string.reponseWrong));
            }
        }
        totalPoints += points;

        TextView text2 = (TextView) dialog.findViewById(R.id.tvNbQuestionquizz);
        text2.setText("Question N°"+ (indexQuestion)+"/"+NbQuestion);

        TextView text3 = (TextView) dialog.findViewById(R.id.tvReponsePts);
        text3.setText("+ "+points+" Pts");


        Button dialogButton = (Button) dialog.findViewById(R.id.btnQuittePopUp2);
        if((indexQuestion+1)<=unQuizz.getQuestions().size())
            dialogButton.setText(R.string.btnPopUpNext);
        else
            dialogButton.setText(R.string.btnPopUpEnd);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if((indexQuestion+1)<=unQuizz.getQuestions().size())
                    initQuestion(unQuizz.getQuestions().get(indexQuestion),unQuizz.getQuestions().size());
                else
                    onQuizzFinished();
            }
        });
        dialog.show();
    }

    public void initQuizz(Quizz unQuizz)
    {
        nbCorrectRep = 0;
        indexQuestion = 0;
        initQuestion(unQuizz.getQuestions().get(indexQuestion),unQuizz.getQuestions().size());
    }

    public void initQuestion(Question puneQuestion, int nbQuestion)
    {
        NbQuestion = nbQuestion;
        btnRepA.setClickable(true);
        btnRepB.setClickable(true);
        btnRepC.setClickable(true);
        btnRepD.setClickable(true);
        btnRepA.setBackgroundResource(R.drawable.btn_background_question);
        btnRepB.setBackgroundResource(R.drawable.btn_background_question);
        btnRepC.setBackgroundResource(R.drawable.btn_background_question);
        btnRepD.setBackgroundResource(R.drawable.btn_background_question);
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
                btnRepA.setBackgroundResource(R.drawable.btn_background_question_good);
                isGoodResponse = true;
                nbCorrectRep = nbCorrectRep + 1;
                timer.onFinish();
            }
            else
            {
                btnRepA.setBackgroundResource(R.drawable.btn_background_question_good);
                nbResponseFound = nbResponseFound + 1;
                if(nbResponseFound == nbCorrectResponse)
                {
                    isGoodResponse = true;
                    nbCorrectRep = nbCorrectRep + 1;
                    timer.onFinish();
                }
            }
        }
        else
        {
            btnRepA.setBackgroundResource(R.drawable.btn_background_question_bad);
            isGoodResponse = false;
            timer.onFinish();
        }
    }

    public void onClickRepB(View view) {
        if(uneQuestion.getReponses().get(1).isCorrect())
        {
            if (!uneQuestion.hasMultipleResponses())
            {
                btnRepB.setBackgroundResource(R.drawable.btn_background_question_good);
                isGoodResponse = true;
                nbCorrectRep = nbCorrectRep + 1;
                timer.onFinish();
            }
            else
            {
                btnRepB.setBackgroundResource(R.drawable.btn_background_question_good);
                nbResponseFound = nbResponseFound + 1;
                if(nbResponseFound == nbCorrectResponse)
                {
                    isGoodResponse = true;
                    nbCorrectRep = nbCorrectRep + 1;
                    timer.onFinish();
                }
            }
        }
        else
        {
            btnRepB.setBackgroundResource(R.drawable.btn_background_question_bad);
            isGoodResponse = false;
            timer.onFinish();
        }
    }

    public void onClickRepC(View view) {
        if(uneQuestion.getReponses().get(2).isCorrect())
        {
            if (!uneQuestion.hasMultipleResponses())
            {
                btnRepC.setBackgroundResource(R.drawable.btn_background_question_good);
                isGoodResponse = true;
                nbCorrectRep = nbCorrectRep + 1;
                timer.onFinish();
            }
            else
            {
                btnRepC.setBackgroundResource(R.drawable.btn_background_question_good);
                nbResponseFound = nbResponseFound + 1;
                if(nbResponseFound == nbCorrectResponse)
                {
                    isGoodResponse = true;
                    nbCorrectRep = nbCorrectRep + 1;
                    timer.onFinish();
                }
            }
        }
        else
        {
            btnRepC.setBackgroundResource(R.drawable.btn_background_question_bad);
            isGoodResponse = false;
            timer.onFinish();
        }
    }

    public void onClickRepD(View view) {
        if(uneQuestion.getReponses().get(3).isCorrect())
        {
            if (!uneQuestion.hasMultipleResponses())
            {
                btnRepD.setBackgroundResource(R.drawable.btn_background_question_good);
                isGoodResponse = true;
                nbCorrectRep = nbCorrectRep + 1;
                timer.onFinish();
            }
            else
            {
                btnRepD.setBackgroundResource(R.drawable.btn_background_question_good);
                nbResponseFound = nbResponseFound + 1;
                if(nbResponseFound == nbCorrectResponse)
                {
                    isGoodResponse = true;
                    nbCorrectRep = nbCorrectRep + 1;
                    timer.onFinish();
                }
            }
        }
        else
        {
            btnRepD.setBackgroundResource (R.drawable.btn_background_question_bad);
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
        tvResultat.setText("Nombre de bonnes reponses : " + nbCorrectRep +" / "+NbQuestion);
        TextView tvResultatPts = findViewById(R.id.tvResultatPts);
        tvResultatPts.setText("Score : " + totalPoints);
        Glide.with(this).load("file:///android_asset/imggif.gif").into(imgGif);
        imgGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(QuizzActivity.this);
            }
        });
    }
}

package fr.eni.geekoquizz.activity;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.bo.Question;
import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.bo.Reponse;
import fr.eni.geekoquizz.bo.Statistique;
import fr.eni.geekoquizz.bo.Theme;
import fr.eni.geekoquizz.bo.Utilisateur;
import fr.eni.geekoquizz.view_model.QuestionViewModel;
import fr.eni.geekoquizz.view_model.QuizzViewModel;
import fr.eni.geekoquizz.view_model.ReponseViewModel;
import fr.eni.geekoquizz.view_model.StatistiqueViewModel;
import fr.eni.geekoquizz.view_model.ThemeViewModel;
import fr.eni.geekoquizz.view_model.UtilisateurViewModel;

public class QuizzActivity extends AppCompatActivity {

    private ProgressBar time;
    private TextView tvQuestion;
    private TextView tvNbQuestion;
    private Button btnRepA, btnRepB, btnRepC, btnRepD, btnJoker;
    private CountDownTimer timer;
    private Boolean isGoodResponse;
    private int nbCorrectResponse, nbResponseFound, indexQuestion, nbCorrectRep,NbQuestion,points,totalPoints,idQuizz;
    private Question uneQuestion;
    private Quizz unQuizz;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ThemeViewModel themeVM = ViewModelProviders.of(this).get(ThemeViewModel.class);
        themeVM.get().observe(this, new Observer<List<Theme>>() {
            @Override
            public void onChanged(@Nullable List<Theme> themes) {
                Log.i("QuizzActivity", themes.toString());
            }
        });
        idQuizz = getIntent().getIntExtra("QuizzPlay",0);
        QuizzViewModel quizzVM = QuizzViewModel.getInstance(this);
        quizzVM.get(idQuizz).observe(this, new Observer<Quizz>() {
            @Override
            public void onChanged(@Nullable final Quizz quizz) {
                unQuizz = quizz;
                Log.i("QuizzActivity", "onChanged()");
                QuestionViewModel questionVM = ViewModelProviders.of(QuizzActivity.this).get(QuestionViewModel.class);
                questionVM.getByQuizz(quizz.getId()).observe(QuizzActivity.this, new Observer<List<Question>>() {
                    @Override
                    public void onChanged(@Nullable List<Question> questions) {
                        unQuizz.setQuestions(questions);
                        ReponseViewModel reponseVM = ViewModelProviders.of(QuizzActivity.this).get(ReponseViewModel.class);
                        for(final Question question: questions) {
                            reponseVM.getByQuestion(question.getId()).observe(QuizzActivity.this, new Observer<List<Reponse>>() {
                                @Override
                                public void onChanged(@Nullable List<Reponse> reponses) {
                                    question.setReponses(reponses);
                                }
                            });
                        }
                        Log.i("QuizzActivity", quizz.toString());
                        initQuizz(quizz);
                    }
                });
            }
        });
    }

    public void PopUpInterQuestion(final boolean Correct){
        QuestionViewModel questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        questionViewModel.get(indexQuestion).observe(this, new Observer<Question>() {
            @Override
            public void onChanged(@Nullable Question question) {
                //Update Nombre de fois ou la question a etait repondu
                if(Correct){
                    //Update Nombre de fois ou elle as bien etait repondu
                }
            }
        });

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

        TextView text2 = dialog.findViewById(R.id.tvNbQuestionquizz);
        text2.setText("Question N°"+ (indexQuestion)+"/"+NbQuestion);

        TextView text3 = dialog.findViewById(R.id.tvReponsePts);
        text3.setText("+ "+points+" Pts");


        Button dialogButton = dialog.findViewById(R.id.btnQuittePopUp2);
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
        this.unQuizz = unQuizz;

        QuizzActivity.this.setTitle(unQuizz.getNom());
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
        new DownloadImageTask((ImageView) findViewById(R.id.iv_Illustration)).execute("http://yoda.pboudeaud.fr/api/showFile/"+uneQuestion.getImage());
        indexQuestion = indexQuestion+1;
        timer.start();
    }

    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
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

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Utilisateur user = new Utilisateur(sp.getString("example_text", ""));

        UtilisateurViewModel utilisateurViewModel = ViewModelProviders.of(this).get(UtilisateurViewModel.class);
        utilisateurViewModel.insert(user);

        Statistique statistique = new Statistique(new Date(),totalPoints,nbCorrectRep,unQuizz,user);
        StatistiqueViewModel statistiqueViewModel = ViewModelProviders.of(this).get(StatistiqueViewModel.class);
        statistiqueViewModel.insert(statistique);

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

package fr.eni.geekoquizz.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.eni.geekoquizz.bo.Quizz;

public class ImportQuizz
{

    private String url="http://yoda.pboudeaud.fr/api/quizz?valid=true";
    private String jsonQuizz;

    public ImportQuizz()
    {
       GetJSON task = new GetJSON();
        try {
            jsonQuizz = task.execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Quizz> importListQuizz()
    {
        List<fr.eni.geekoquizz.boback.Quizz> lesQuizzBoBack = new ArrayList<>();
        List<Quizz>lesQuizz = new ArrayList<>();
        Type listType = new TypeToken<ArrayList<fr.eni.geekoquizz.boback.Quizz>>(){}.getType();
        lesQuizzBoBack = new Gson().fromJson(jsonQuizz,listType);
        for (fr.eni.geekoquizz.boback.Quizz unQuizz: lesQuizzBoBack)
        {
            lesQuizz.add(unQuizz.toQuizz());
        }
        return lesQuizz;
    }

}

package fr.eni.geekoquizz.service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fr.eni.geekoquizz.bo.Quizz;

import static android.support.constraint.Constraints.TAG;

public class ImportQuizz {

    private URL urlApi = null;
    private String jsonQuizz;

    public ImportQuizz()
    {
        try
        {
             urlApi = new URL("http://10.46.101.16:8080/api/quizz");
             HttpURLConnection api = (HttpURLConnection) urlApi.openConnection();
             BufferedReader buff = new BufferedReader(new InputStreamReader(api.getInputStream()));
             jsonQuizz = readStream(buff);
        }
        catch (MalformedURLException e)
        {
            Log.e(TAG, "ImportQuizz: " + e.getMessage());
        } catch (IOException e)
        {
            Log.e(TAG, "ImportQuizz: " + e.getMessage());
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

    public String readStream(BufferedReader buff) throws IOException
    {
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line = buff.readLine()) != null)
        {
            stringBuffer.append(line);
        }
        return stringBuffer.toString();
    }
}

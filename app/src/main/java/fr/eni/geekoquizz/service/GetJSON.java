package fr.eni.geekoquizz.service;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetJSON extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {
        try
        {
            URL unUrl = new URL(urls[0]);
            HttpURLConnection api = (HttpURLConnection) unUrl.openConnection();
            InputStreamReader isr = new InputStreamReader(api.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            return readStream(br);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private String readStream(BufferedReader buff) throws IOException
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

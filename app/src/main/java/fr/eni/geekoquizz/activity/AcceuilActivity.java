package fr.eni.geekoquizz.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.dao.Bdd;
import fr.eni.geekoquizz.service.ImportService;
import fr.eni.geekoquizz.tools.Network;
import fr.eni.geekoquizz.view_model.QuizzViewModel;

public class AcceuilActivity extends AppCompatActivity {
    ImageView ivGif;
    CountDownTimer timer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        ivGif = findViewById(R.id.ivGif);
        Glide.with(this).load("file:///android_asset/yoda_acceuil.gif").into(ivGif);
        /*timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(AcceuilActivity.this, MainActivity.class);
                AcceuilActivity.this.startActivity(intent);
            }
        }.start();*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isNotUpdate()) {
            if(Network.isAvailable(this)) {
                if(isNotUpdate()) {
                    this.deleteDatabase("GeekoQuizz");
                    ImportService importService = new ImportService(this);
                    importService.importAll();
                    setUpdate();
                }

            } else {
                Toast.makeText(this, "Aucune connection internet détecté\nImport impossible", Toast.LENGTH_LONG);
                return;
            }
        }
        Intent intent = new Intent(AcceuilActivity.this, MainActivity.class);
        AcceuilActivity.this.startActivity(intent);
    }

    private static final String goodFileContents = "1";

    private boolean isNotUpdate() {
        String filename = "isUpdate";
        String content = "";
        try {
            FileInputStream inputStream = openFileInput(filename);
            StringBuffer fileContent = new StringBuffer("");

            byte[] buffer = new byte[1024];
            int n;

            while ((n = inputStream.read(buffer)) != -1)
            {
                fileContent.append(new String(buffer, 0, n));
            }
            content = fileContent.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return !content.equals(goodFileContents);
    }

    private void setUpdate() {
        String filename = "isUpdate";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(goodFileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package fr.eni.geekoquizz.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.service.ImportService;

public class AcceuilActivity extends AppCompatActivity {

    private static boolean isNotImported = true;
    ImageView ivGif;

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

        if(isNotImported) {
            isNotImported = !isNotImported;
            ImportService importService = new ImportService(this);
            AsyncImportTask task = new AsyncImportTask();
            task.execute(importService);
            importService.importAll();
            Intent intent = new Intent(AcceuilActivity.this, MainActivity.class);
            AcceuilActivity.this.startActivity(intent);
        } else {
            Intent intent = new Intent(AcceuilActivity.this, MainActivity.class);
            AcceuilActivity.this.startActivity(intent);
        }

    }

    public static class AsyncImportTask extends AsyncTask<ImportService, Void, Void> {

        @Override
        protected Void doInBackground(ImportService... importServices) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}

package fr.eni.geekoquizz.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import fr.eni.geekoquizz.R;

public class AcceuilActivity extends AppCompatActivity {

    ImageView ivGif;
    CountDownTimer timer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        ivGif = findViewById(R.id.ivGif);
        Glide.with(this).load("file:///android_asset/yoda_acceuil.gif").into(ivGif);
        timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(AcceuilActivity.this, MainActivity.class);
                AcceuilActivity.this.startActivity(intent);
            }
        }.start();
    }
}

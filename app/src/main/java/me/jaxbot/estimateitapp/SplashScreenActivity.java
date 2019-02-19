package me.jaxbot.estimateitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
    long Delay = 5000;

    private String username;
   // public String password = (String)getIntent().getSerializableExtra("password");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("The username is >>>>", (String)getIntent().getSerializableExtra("username"));
        username = (String)getIntent().getSerializableExtra("username");
        Log.d("printed again >>>>>>>>>", username);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash_screen);

        Timer RunSplash = new Timer();

        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {

                finish();
                Intent intent = new Intent(SplashScreenActivity.this, Main3Activity.class);
                intent.putExtra("user", username);
                //intent.putExtra("password", password);
                startActivity(intent);
            }
        };

        RunSplash.schedule(ShowSplash, Delay);
    }
}

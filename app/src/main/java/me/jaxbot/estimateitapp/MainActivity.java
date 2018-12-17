package me.jaxbot.estimateitapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.channels.CompletionHandler;
import java.util.Timer;
import java.util.TimerTask;

import wlcp.gameserver.api.WLCPGameServer;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(), "fonts/simple.ttf");


        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        Info = findViewById(R.id.tvInfo);
        Login = findViewById(R.id.btnLogin);

        Username.setTypeface(myCustomFont);
        Password.setTypeface(myCustomFont);
        Info.setTypeface(myCustomFont);
        Login.setTypeface(myCustomFont);

        Info.setText("Number of attempts remaining: " + counter);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Username.getText().toString(), Password.getText().toString());

            }
        });
    }

    @TargetApi(26)
    private void validate (final String userName, final String userPassword) {
        //if(userName.equals("admin") && userPassword.equals("1234")){
        if(!userName.equals("") && !userPassword.equals("")) {
            //Try connecting to server
            WLCPGameServerSingleton.getInstance().connectToServer(new CompletionHandler<Void, AppCompatActivity>() {
                @Override
                public void completed(Void result, AppCompatActivity activity) {
                    //Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                    intent.putExtra("username", userName);
                    Log.d("STATE", "I am getting here!");
                    startActivity(intent);
                }
                @Override
                public void failed(Throwable exc, AppCompatActivity activity) {
                    //TODO implement error handling (server connection)
                }
            }, this);

        } else {
            counter--;

            Info.setText("Number of attempts remaining: " + String.valueOf(counter));

            if (counter == 0){
                Login.setEnabled(false);
            }
        }
    }
}

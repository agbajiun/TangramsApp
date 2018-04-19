package me.jaxbot.estimateitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Main2Activity extends AppCompatActivity {

    private EditText Lobbyval;
    private Button SelectLobby;
    Spinner sp;
    private String gameLobbySelected;

    //private Spinner spinner = findViewById(R.id.spinner);
   //  = spinner.getSelectedItem().toString();
    //Making string array

    //define array adapter of string type
    ArrayAdapter <String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sp = findViewById(R.id.spinnerId);
        gameLobbySelected = sp.getSelectedItem().toString();

        //adminLobbyval = findViewById(R.id.lobby);
       SelectLobby = findViewById(R.id.selectLobbyBtn);
        SelectLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLobby(gameLobbySelected);
            }
        });
    }

    private void selectLobby (String gameLobby) {
        Log.d("STATE", gameLobby);
        Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
        startActivity(intent);
    }
}

package me.jaxbot.estimateitapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import wlcp.gameserver.api.IWLCPGameServer;
import wlcp.gameserver.api.WLCPBaseGameServerListener;
import wlcp.gameserver.api.WLCPGameServerListener;
import wlcp.shared.packets.GameLobbiesPacket;
import wlcp.shared.packets.GameLobbyInfo;

public class Main2Activity extends AppCompatActivity {

    private Button SelectGamePin;
    private EditText gamePin;
    private String gamePinString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gamePin = findViewById(R.id.gamePin);
        gamePinString = gamePin.toString();
        SelectGamePin = findViewById(R.id.gamePinBtn);
        SelectGamePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginToGame();
            }
        });
       // username = (String)getIntent().getSerializableExtra("username");
       // getLobbiesFromServer(username);
    }

    private void getLobbiesFromServer(final String username) {
        Thread thread = new Thread(new Runnable() {
           @Override
            public void run() {
               Bundle b = new Bundle();
               WLCPGameServerSingleton.getInstance().getGameServer().registerEventListener(new WLCPGameServerListenerImplActivity2());
               WLCPGameServerSingleton.getInstance().getGameServer().getGameLobbiesForUsername(username);
           }
        });
        thread.start();
    }

    private void loginToGame () {
        Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
        //intent.putExtra("username", username);
        startActivity(intent);
    }
}


class WLCPGameServerListenerImplActivity2 extends WLCPBaseGameServerListener implements WLCPGameServerListener {

    @Override
    public void gameLobbiesRecieved(IWLCPGameServer gameServer, GameLobbiesPacket packet) {
        for(GameLobbyInfo info : packet.getGameLobbyInfo()) {
            Log.d("Lobbies>>>>", info.gameLobbyName);
            //Intent lobbies = new Intent(info.gameLobbyName);
            //lobbies.putExtra("lobbyName", info.gameLobbyName);
            //LocalBroadcastManager.getInstance(getContext()).sendBroadcast(lobbies);
            //lobbyFromServer = info.gameLobbyName;
        }
    }
}
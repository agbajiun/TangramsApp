package me.jaxbot.estimateitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import wlcp.gameserver.api.IWLCPGameClient;
import wlcp.gameserver.api.WLCPGameClient;
import wlcp.gameserver.api.exception.WLCPGameInstanceOrUsernameDoesNotExistException;
import wlcp.gameserver.api.exception.WLCPGameServerCouldNotConnectException;
import wlcp.shared.message.PlayerAvaliableMessage;

public class SelectPlayerActivity extends AppCompatActivity {
    private Button Join;
    private ArrayList<String> availablePlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);

        Join = findViewById(R.id.join);
        availablePlayers = getIntent().getStringArrayListExtra("playerlist");

        Join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPlayer();

                /*Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        IWLCPGameClient gameClient = new WLCPGameClient("130.215.45.83", 3333, gamePinInt, username);
                        try {
                            gameClient.connect(players.get(0).team, players.get(0).player, new WLCPGameServerSessionHandlerImpl(gameClient));

                        } catch (WLCPGameServerCouldNotConnectException e) {
                            e.printStackTrace();
                        } // this should be in the next screen where they select their players and teams
                    }
                });
                thread.start();*/
            }
        });
    }


    private void selectPlayer () {
        Intent intent = new Intent(SelectPlayerActivity.this, Main3Activity.class);
        //intent.putExtra("players", (List<PlayerAvaliableMessage>)players);
        //intent.putStringArrayListExtra("playerlist", players);
        startActivity(intent);
    }
}

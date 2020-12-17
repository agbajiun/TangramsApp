package me.jaxbot.estimateitapp;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import wlcp.gameserver.api.IWLCPGameClient;
import wlcp.gameserver.api.WLCPGameClient;
import wlcp.gameserver.api.exception.WLCPGameInstanceOrUsernameDoesNotExistException;
import wlcp.gameserver.api.exception.WLCPGameServerCouldNotConnectException;
import wlcp.shared.message.PlayerAvaliableMessage;


public class Main2Activity extends AppCompatActivity {

    private Button SelectGamePin;
    private EditText gamePin;

    private String gamePinString;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //username = (String)getIntent().getSerializableExtra("username");
        username = getIntent().getStringExtra("username");

        SelectGamePin = findViewById(R.id.gamePinBtn);
        SelectGamePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamePin = findViewById(R.id.gamePin);
                gamePinString = gamePin.getText().toString();
                int gamePinInt = Integer.parseInt(gamePinString);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        IWLCPGameClient gameClient = new WLCPGameClient("130.215.45.83", 3333, gamePinInt, username);
                        try {
                            List<PlayerAvaliableMessage> players = gameClient.getPlayersAvailableFromGamePin();
                            players.size();
                            loginToGame(playerStrings(players), gamePinString, username);

                            playerStrings(players);

                            /*try {
                                gameClient.connect(players.get(0).team, players.get(0).player, new WLCPGameServerSessionHandlerImpl(gameClient));

                            } catch (WLCPGameServerCouldNotConnectException e) {
                                e.printStackTrace();
                            }*/ // this should be in the next screen where they select their players and teams

                        } catch (WLCPGameInstanceOrUsernameDoesNotExistException e) {
                            e.printStackTrace();
                            gamePinError();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();

            }
        });

    }


    private void loginToGame (ArrayList<TeamPlayer> players, String gamePin, String username) {
        Intent intent = new Intent(Main2Activity.this, SelectPlayerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("playerlist", players);
        intent.putExtras(bundle);
        intent.putExtra("gamePin", gamePin);
        intent.putExtra("user", username);
        //intent.putStringArrayListExtra("playerlist", players);

        //intent.putExtras(bundle); //figure out how to pass array of team player
        startActivity(intent);
    }


    private ArrayList<TeamPlayer> playerStrings (List<PlayerAvaliableMessage> players){
        ArrayList<TeamPlayer> teamPlayerStrings = new ArrayList<TeamPlayer>();
        for (int x = 0; x < players.size(); x++){

            int p = players.get(x).player;
            int t = players.get(x).team;

            TeamPlayer teamplayerStr = new TeamPlayer(p,t);
            teamPlayerStrings.add(teamplayerStr);
        }


        return teamPlayerStrings;
    }

    private void gamePinError(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText
                        (getApplicationContext(), "The Game Pin Or Username entered does not exist!", Toast.LENGTH_LONG)
                        .show();
            }
        });

    }
}



package me.jaxbot.estimateitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class SelectPlayerActivity extends AppCompatActivity {
    private Button Join;
    //private ArrayList<String> availablePlayers;
    private ArrayList<TeamPlayer> availablePlayers;
    private String player;
    private String team;
    private String username;
    private String gamePin;
    private String displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);

        Join = findViewById(R.id.join);
        //availablePlayers = getIntent().getStringArrayListExtra("playerlist");
        gamePin = getIntent().getStringExtra("gamePin");
        int gamePinInt = Integer.parseInt(gamePin);
        username = getIntent().getStringExtra("user");

        Bundle bundleObject = getIntent().getExtras();
        availablePlayers = (ArrayList<TeamPlayer>) bundleObject.getSerializable("playerlist");
        ArrayList<String> spinnerList = new ArrayList<String>();

        String[] teamPlayer = new String[2];

        for (int i=0; i< availablePlayers.size(); i++){
            spinnerList.add(availablePlayers.get(i).toString());
        }

        Spinner sp = findViewById(R.id.selectPlayer);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,spinnerList);


        //ArrayAdapter<TeamPlayer> adapter = new ArrayAdapter<TeamPlayer>(this,android.R.layout.simple_list_item_1,availablePlayers);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
            String selectedItemText = (String)adapterView.getItemAtPosition(position);
            String stripedTeamItem = selectedItemText.replace("Team ", "").replace(" Player", "");
            getTeamPlayer(stripedTeamItem);

            // Notify the selected item text
            Toast.makeText
                    (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                    .show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPlayer(player, team, gamePinInt, username);

            }
        });
    }


    private void selectPlayer (String selectedPlayer, String selectedTeam, int gamePinInt, String username) {
        Intent intent = new Intent(SelectPlayerActivity.this, Main3Activity.class);
        intent.putExtra("player", selectedPlayer);
        intent.putExtra("team", selectedTeam);
        intent.putExtra("gamePin", gamePin);
        intent.putExtra("username", username);

        startActivity(intent);
    }

    public void getTeamPlayer(String teamPlayerString){
        String[] teamPlayer = teamPlayerString.split(" ");
        team = teamPlayer[0];
        player = teamPlayer[1];

    }

    /*public void getDisplayText(String text) {
        displayText = text;
    }*/
}

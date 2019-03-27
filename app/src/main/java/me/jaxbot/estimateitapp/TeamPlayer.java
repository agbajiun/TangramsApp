package me.jaxbot.estimateitapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import wlcp.shared.message.PlayerAvaliableMessage;

/**
 * Created by estheragbaji on 2/22/19.
 */

public class TeamPlayer implements Serializable{
    int player;
    int team;

    TeamPlayer(int player, int team) {
       this.player = player;
       this.team = team;
    }

    /*TeamPlayer (String playerString){
        this.player=1;
        this.team=1;
    }*/

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Team ");
        str.append(this.team + 1);
        str.append(" Player ");
        str.append(this.player + 1);

        return str.toString();
    }

   /* @Override
    public void toTeamPlayer(String teamplayer){

    }*/
}


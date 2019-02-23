package me.jaxbot.estimateitapp;

import java.util.ArrayList;
import java.util.List;

import wlcp.shared.message.PlayerAvaliableMessage;

/**
 * Created by estheragbaji on 2/22/19.
 */

public class TeamPlayer {
    int player;
    int team;

    TeamPlayer(int player, int team) {
       this.player = player;
       this.team = team;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Player ");
        str.append(this.player);
        str.append(" Team ");
        str.append(this.team);
        return str.toString();
    }
}


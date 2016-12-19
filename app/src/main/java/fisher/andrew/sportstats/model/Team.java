package fisher.andrew.sportstats.model;

import java.util.ArrayList;


public class Team {

    private String name;
    ArrayList<Player> players;

    public Team(String name, ArrayList<Player> players) {
        this.name = name;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player newPlayer){
        players.add(newPlayer);
    }
}
//add index after generate

// ---TO ADD IF TIME---
//wins loses
//sport type
//overall team score
//make a team size limit eventually
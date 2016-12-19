package fisher.andrew.sportstats.model;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Team {

    private String name;
    ArrayList<Player> players;
    String index;

    public Team(){}

    public Team(String name, ArrayList<Player> players) {
        this.name = name;
        this.players = players;
        this.index = "none_specified";
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

    public String getIndex(){
        return index;
    }

    public void setIndex(String index){
        this.index=index;
    }
}
//add index after generate

// ---TO ADD IF TIME---
//wins loses
//sport type
//overall team score
//make a team size limit eventually
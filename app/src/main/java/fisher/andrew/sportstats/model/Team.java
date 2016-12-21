package fisher.andrew.sportstats.model;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Team {

    String name;
    ArrayList<Player> players= new ArrayList<>();;
    //    String index; is this needed?
    private String pushId;


    public Team(){}

    public Team(String name) {
        this.name = name;
//        this.players
//        this.index = "none_specified";
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

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

//    public String getIndex(){
//        return index;
//    }
//
//    public void setIndex(String index){
//        this.index=index;
//    }
}
//add index after generate

// ---TO ADD IF TIME---
//wins loses
//sport type
//overall team score
//make a team size limit eventually
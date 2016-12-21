package fisher.andrew.sportstats.model;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Team {

    String name;
    ArrayList<String> playerIds = new ArrayList<>();
    //    String index; is this needed?
    private String pushId;


    public Team(){}

    public Team(String name) {
        this.name = name;
//        this.playerIds
//        this.index = "none_specified";
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getPlayerIds() {
        return playerIds;
    }

    public void addPlayer(String newPlayer){
        playerIds.add(newPlayer);
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

}
//add index after generate

// ---TO ADD IF TIME---
//wins loses
//sport type
//overall team score
//make a team size limit eventually
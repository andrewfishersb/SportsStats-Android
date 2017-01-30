package fisher.andrew.sportstats.model;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;

import org.parceler.Parcel;

import fisher.andrew.sportstats.Constants;


//will this need an index for movement purposes...i think so
@Parcel
public class Player {
    //Player info
    String name;
    String height;
    int age;
    private String pushId;
    //have just team id or team name? with id ill still be able to access their name (try id for now)
    String teamId;

    //playerStats
    int freeThrows;
    int twoPointers;
    int threePointers;
    int assists;
    int rebounds;
    int blocks;
    int steals;
    int totalPoints;

    //Overall instance variables
     int overallTwoPointers;
     int overallThreePointers;
     int overallFreeThrows;
     int overallAssists;
     int overallRebounds;
     int overallBlocks;
     int overallSteals;
     int overallPoints;
     int gamesPlayed;





    public Player(){}

    public Player(String name, String height, int age){
        this.name = name;
        this.height = height;
        this.age = age;
        this.freeThrows=0;
        this.twoPointers =0;
        this.threePointers=0;
        this.assists=0;
        this.blocks=0;
        this.steals=0;
        this.totalPoints =0;
        this.rebounds = 0;
        this.overallTwoPointers = 0;
        this.overallThreePointers = 0;
        this.overallFreeThrows = 0;
        this.overallAssists = 0;
        this.overallRebounds = 0;
        this.overallBlocks = 0;
        this.overallSteals = 0;
        this.overallPoints = 0;
        this.gamesPlayed = 0;
        this.teamId = "free_agent";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFreeThrows() {
        return freeThrows;
    }

    public void setFreeThrow(int points) {
        this.freeThrows+=points;
        this.totalPoints+=points;
    }

    public int getTwoPointers() {
        return twoPointers;
    }

    public void setTwoPoints(int points) {
        this.twoPointers+= points;
        this.totalPoints+= (points*2);
    }

    public int getThreePointers() {
        return threePointers;
    }

    public void setThreePointer(int points) {
        this.threePointers+= points;
        this.totalPoints+= (points*3);
    }

    public int getAssists() {
        return assists;
    }

    public void setAssist(int assist) {
        this.assists+= assist;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlock(int block) {
        this.blocks+=block;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steal) {
        this.steals+=steal;
    }

    public void setTotalPoints(int threePointers, int twoPointers, int freeThrows){
        this.totalPoints = freeThrows + (2*twoPointers) + (3*threePointers);
    }

    public int getTotalPoints(){
        return this.totalPoints;
    }

    public int getRebounds(){
        return this.rebounds;
    }

    public void setRebound(int rebound){
        this.rebounds+= rebound;
    }


    public void endGameResetStats(DatabaseReference playerToSelectRef){
        //reset values - see if i can set value in a setter instead?
        this.freeThrows=0;
        this.twoPointers =0;
        this.threePointers=0;
        this.assists=0;
        this.blocks=0;
        this.steals=0;
        this.totalPoints =0;

        playerToSelectRef.child(Constants.FIREBASE_CHILD_TWO_POINTERS).setValue(0);
        playerToSelectRef.child(Constants.FIREBASE_CHILD_TOTAL_POINTS).setValue(0);
        playerToSelectRef.child(Constants.FIREBASE_CHILD_THREE_POINTERS).setValue(0);
        playerToSelectRef.child(Constants.FIREBASE_CHILD_FREE_THROWS).setValue(0);
        playerToSelectRef.child(Constants.FIREBASE_CHILD_REBOUNDS).setValue(0);
        playerToSelectRef.child(Constants.FIREBASE_CHILD_ASSISTS).setValue(0);
        playerToSelectRef.child(Constants.FIREBASE_CHILD_STEALS).setValue(0);
        playerToSelectRef.child(Constants.FIREBASE_CHILD_BLOCKS).setValue(0);


}

    public void endGameAddStatsToOverall(DatabaseReference playerToSelectRef){
        //set overall -> may eventually do division
        this.overallTwoPointers += getTwoPointers();
        this.twoPointers = getTwoPointers();
        this.overallThreePointers += getThreePointers();
        this.overallFreeThrows += getFreeThrows();
        this.overallAssists += getAssists();
        this.overallRebounds += getRebounds();
        this.overallBlocks += getBlocks();
        this.overallSteals += getSteals();
        this.overallPoints += getTotalPoints();

        Log.d("Total Points",Integer.toString(getTotalPoints()));

        playerToSelectRef.child(Constants.FIREBASE_CHILD_TWO_POINTERS_OVERALL).setValue(this.getOverallTwoPointers());
        playerToSelectRef.child(Constants.FIREBASE_CHILD_TOTAL_POINTS_OVERALL).setValue(this.getOverallPoints());
        playerToSelectRef.child(Constants.FIREBASE_CHILD_THREE_POINTERS_OVERALL).setValue(this.getOverallThreePointers());
        playerToSelectRef.child(Constants.FIREBASE_CHILD_FREE_THROWS_OVERALL).setValue(this.getOverallFreeThrows());
        playerToSelectRef.child(Constants.FIREBASE_CHILD_REBOUNDS_OVERALL).setValue(this.getOverallRebounds());
        playerToSelectRef.child(Constants.FIREBASE_CHILD_ASSISTS_OVERALL).setValue(this.getOverallAssists());
        playerToSelectRef.child(Constants.FIREBASE_CHILD_STEALS_OVERALL).setValue(this.getOverallSteals());
        playerToSelectRef.child(Constants.FIREBASE_CHILD_BLOCKS_OVERALL).setValue(this.getOverallBlocks());
        playerToSelectRef.child(Constants.FIREBASE_CHILD_GAMES_PLAYED).setValue(this.getGamesPlayed());


    }


    public int getOverallTwoPointers() {
        return overallTwoPointers;
    }

    public void setOverallTwoPointers(int overallTwoPointers) {
        this.overallTwoPointers = overallTwoPointers;
    }

    public int getOverallThreePointers() {
        return overallThreePointers;
    }

    public void setOverallThreePointers(int overallThreePointers) {
        this.overallThreePointers = overallThreePointers;
    }

    public int getOverallFreeThrows() {
        return overallFreeThrows;
    }

    public void setOverallFreeThrows(int overallFreeThrows) {
        this.overallFreeThrows = overallFreeThrows;
    }

    public int getOverallAssists() {
        return overallAssists;
    }

    public void setOverallAssists(int overallAssists) {
        this.overallAssists = overallAssists;
    }

    public int getOverallRebounds() {
        return overallRebounds;
    }

    public void setOverallRebounds(int overallRebounds) {
        this.overallRebounds = overallRebounds;
    }

    public int getOverallBlocks() {
        return overallBlocks;
    }

    public void setOverallBlocks(int overallBlocks) {
        this.overallBlocks = overallBlocks;
    }

    public int getOverallSteals() {
        return overallSteals;
    }

    public void setOverallSteals(int overallSteals) {
        this.overallSteals = overallSteals;
    }

    public int getOverallPoints() {
        return overallPoints;
    }

    public void setOverallPoints(int overallPoints) {
        this.overallPoints = overallPoints;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void addGamesPlayed() {
        this.gamesPlayed++;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public void setTeamId(String id){
        this.teamId = id;
    }

    public String getTeamId(){
        return this.teamId;
    }


}
// ---ADD LATER IF TIME---
//more stats
//rank playerIds
//personal information, age, height, weight? dob
//eventually have overall stats versus current game stats
//refractor add methods;
//set methods once game is over to change the playerIds overall stats
//export as csv file
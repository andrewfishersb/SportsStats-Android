package fisher.andrew.sportstats.model;

import org.parceler.Parcel;

@Parcel
public class Player {
    String name;
    String height;
    int age;
    int freeThrows;
    //a regular 2 pt basket
    int twoPointers;
    int threePointers;
    int assists;
    int rebounds;
    int blocks;
    int steals;
    int totalPoints;



    private String pushId;

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
        this.totalPoints =0 ;
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

    public void addFreeThrow() {
        this.freeThrows++;
        this.totalPoints++;
    }

    public int getTwoPointers() {
        return twoPointers;
    }

    public void addTwoPoints() {
        this.twoPointers++;
        this.totalPoints +=2;
    }

    public int getThreePointers() {
        return threePointers;
    }

    public void addThreePointer() {
        this.totalPoints += 3;
        this.threePointers++;
    }

    public int getAssists() {
        return assists;
    }

    public void addAssist() {
        this.assists++;
    }

    public int getBlocks() {
        return blocks;
    }

    public void addBlock() {
        this.blocks++;
    }

    public int getSteals() {
        return steals;
    }

    public void addSteal() {
        this.steals++;
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

    public void addRebound(){
        this.rebounds++;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }


}
// ---ADD LATER IF TIME---
//more stats
//rank players
//personal information, age, height, weight? dob
//eventually have overall stats versus current game stats
//refractor add methods;
//set methods once game is over to change the players overall stats
//export as csv file
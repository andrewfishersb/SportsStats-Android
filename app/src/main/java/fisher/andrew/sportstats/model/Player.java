package fisher.andrew.sportstats.model;

import org.parceler.Parcel;

@Parcel
public class Player {


    String name;
    String height;
    String age;
    int freeThrows;
    //a regular 2 pt basket
    int fieldGoals;
    int threePointers;
    int assists;
    int blocks;
    int steals;
    int totalPoints;

    public Player(String name, String height, String age){
        this.name = name;
        this.height = height;
        this.age = age;
        this.freeThrows=0;
        this.fieldGoals=0;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getFreeThrows() {
        return freeThrows;
    }

    public void addFreeThrow(int freeThrows) {
        this.freeThrows = freeThrows;
    }

    public int getFieldGoals() {
        return fieldGoals;
    }

    public void addFieldGoal(int fieldGoals) {
        this.fieldGoals++;
    }

    public int getThreePointers() {
        return threePointers;
    }

    public void addThreePointer() {
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
    public int setTotalPoints(int threePointers, int fieldGoals, int freeThrows){
        this.totalPoints = freeThrows + (2*fieldGoals) + (3*threePointers);
    }
    public int getTotalPoints(){
        return this.totalPoints;
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
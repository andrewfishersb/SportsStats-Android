# Sport Stats Tracker

An android app meant for tracking basketball stats

#### By **Andrew Fisher**

## Description
A user will be able to create their own teams and players and in doing so record statistics of these players. This current version will focus on basketball statistics.

## Specifications

|Behaviors                |
|------------------------- |
|1. User can create a player|
|2. User can record stats for said player (Points, Assists, Steals, Blocks)|
|3. User can create see an overview of said player|
|4. User can create a team|

#POSSIBLY Tomorrow Tasks
* double click vs single click
* start a game of only players for that team
* Player detail page for now just have overall stats
* a view all players section?
* Take a photo?


## Setup/Installation Requirements

```
 Clone from github
 Open up android studio
 plug in your own device or set up an emulator
 press run, select the device and the app should run
 if it does not start go to Build->Clean Project
```


## Future Goals and Ideas (will slowly work their way into the Specifications)
 * Looks like you have no teams click the plus to add one (or just pop a dialog for them to use)
 * Turn add a player into a DialogFragment - https://developer.android.com/reference/android/app/DialogFragment.html
 * First page might just have View teams or View players and then have the top bar available to add new teams and players
 * Height spinner, set a different default value i.e. to 6'2", limit the amount of items shown
 * More stats including 3s FTs attempts, Turnovers, fouls
 * Start a game, resets stats, but at the end of the game sends them to an overall, which can then find an average since they have the games played
 * Click vs double click to record certain stats
 * Substitutions
 * Record live time
 * expand to other sports
 * Refractor some activities into fragments (ie the format of the statistics)
 * Change CreateAPlayerActivity into a DialogFragment
 * Change around the database where player can hold a teamId but not need to wrapped in a team id on the outside
 * Swipe a player to sub, this may launch a dialog box where a user will choose a player to switch then with
 * Finish a game and once done see stats for everyone who took part



## Technologies Used

* Android
* Java
* XML
* Gradle
* Git


## Known Bugs or Improvements
* On Team detail in landscape mode the players may disappear if not when you turn back to portrait the same may happen

### License

*GPL*

Copyright (c) 2016 **Andrew Fisher**

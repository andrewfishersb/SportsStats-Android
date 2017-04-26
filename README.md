# Sport Stats Tracker


#### By **Andrew Fisher**

## Description
This Android Application lets a user keep track of a team and record statistics of their players. Ideally would be used by youth to high school coaches.

![sportstat1](https://cloud.githubusercontent.com/assets/17396138/25404513/262ce078-29b5-11e7-8670-1e1e564650ac.jpg)
![sportstat2](https://cloud.githubusercontent.com/assets/17396138/25404514/26345d26-29b5-11e7-82bd-d0c87984f9e6.jpg)

## User Stories
* As a user, I want to to have my own account.
* As a user, I want to create multiple teams.
* As a user, I want to create players for a specific teams.
* As a user, I want to record the statistics for one game of all the players on one team.
* As a user, I want to view all the players on a specific team.
* As a user, I want to view a players overall statistics and career averages.
* As a user, I want to see a leaderboard of all the players I have created.


## Specifications

|On Launch Options               |
|------------------------- |
| Create an account|
| Log into account|
| Automatically move to home page if logged in|


|Welcome Page          |
|------------------------- |
| Click on View Teams|
| Click on Leaderboard|


|Leaderboard    |
|------------------------- |
| View Leaders Overall in each statistic|
| View Leaders in Averages in each statistic|

|View All Teams    |
|------------------------- |
| Create a new Team|
| Click on a specific team|

|Teams Screen   |
|------------------------- |
| Create a new player - with their name, age and height|
| Start a game|
| Click on a specific player to go to their player page|

|Game Screen   |
|------------------------- |
| Click on a statistic on the grid under a certain player to add that to their personal statistic|
| End game will finish the game and calculate all players personal averages and statistics|

|Player Screen   |
|------------------------- |
| View players information - height, age, name, team|
| Take a profile picture|
| See players average statistics|
| See players overall statistics|

## Setup/Installation Requirements

```
 Clone from github
 Open up android studio
 plug in your own device or set up an emulator
 press run, select the device and the app should run
 if it does not start go to Build->Clean Project
```


## Future Goals and Ideas
 * Looks like you have no teams click the plus to add one (or just pop a dialog for them to use)
 * Height spinner, set a different default value i.e. to 6'2", limit the amount of items shown
 * Renovate to use SQLite instead of Firebase as I have been having some database issues.

## Technologies Used

* Android
* Java
* XML
* Gradle
* Git


## Known Bugs or Improvements
* If clicked wrong certain statistics may increase and decrease in a random manner. I believe this may have to do with Firebase which is why I will be working on translating the app into SQLite

### License

*GPL*

Copyright (c) 2016 **Andrew Fisher**

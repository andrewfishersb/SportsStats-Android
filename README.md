# Sport Stats Tracker

An android app meant for tracking basketball stats

#### By **Andrew Fisher**

## Description
A user will be able to create their own teams and players and in doing so record statistics of these players. This current version will focus on basketball statistics.

![sportstat1](https://cloud.githubusercontent.com/assets/17396138/25404513/262ce078-29b5-11e7-8670-1e1e564650ac.jpg)
![sportstat2](https://cloud.githubusercontent.com/assets/17396138/25404514/26345d26-29b5-11e7-82bd-d0c87984f9e6.jpg)

## Specifications

|Behaviors                |
|------------------------- |
|1. User can create a player|
|2. User can record stats for said player (Points, Assists, Steals, Blocks)|
|3. User can create see an overview of said player|
|4. User can create a team|


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

# Arcade machine game  
Our project is a fighting game that can be used on an arcade machine, but also on a computer, using a keyboard, produced as part of a software development project.


# Game

The game is a confrontation between two local players. The goal is to eliminate the other by shooting him to make him lose his whole life. It is possible to modify the characteristics of the characters in the option screen. The winner's score is entered in the scoreboard.

# Use
  
For the keyboard version the keys to operate the game are as follows
-   Z, Q, S, D: player 1 movement keys 
- R: first player fire button 
-  directional arrows: player 2 movement keys 
-  M: second player fire button 
- O: Option screen 
-  I: Information screen 
-  Entrance: Launch of the game
> On the end of game screen, the enter key allows you to return to the main menu.

To play the arcade version you must have 5 push buttons and two joysticks as well as two cards. On the first you have to put four buttons including the three from the menu which will be the first three and the fourth will be the player's fire button, as well as the joystick. On the second card you have to put the second joystick and a single button which will be the fire button of the second player. To play the game it is necessary to have an Intel or AMD processor

> La librairie Slick2D présente dans le projet ne supporte pas le processeur d'une Raspberry

## Libraries
  
To make the project work, you have to add the different libraries to it. They are present in the **lib** folder 
We must therefore add:
- jinput.jar
- lwjgl.jar
- slick.jar

These three libraries allow the Slick2D library to function properly.
Other important information you have to edit the project configurations by adding the path to the **lib / natives** folder which contains the rest of the library used.

The last library to import is the MySQL library which allows connection to the database.
# Database


The database.sql file allows you to load the project database.
For that you have to import it into MySQL.
> Please note the game can only be used with MySQL!
> If you want to use another Database Management System then you have to modify the code in **LienBDD** class.

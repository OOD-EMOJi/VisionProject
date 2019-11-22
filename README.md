# Vision Project 🙀🌟👀🙃🐭

## Install and run

1. Download and unzip all the files into one folder
1. Open command line to the directory enclosing both the EMOJI and LepinskiEngine folders
1. Run `$ java LepinskiEngine.GameEngine`

## Robots and RobotBehaviors

### Changing the bot behaviors

`EMOJI/EMOJITeam.java` holds a `Map<Integer, RobotBehavior>` which ties each Robot to a personality. `startGame()` decides which robots get which behavior.

### The RobotBehavior interface

`RobotBehavior`s are the mind of each robot. They determine how and when a robot moves and/or does other robotic tasks such as picking up coins. The `RobotBehavior` interface contains one method:

````
Command getCommand(Robot robot, Location location)
````

This takes in the robot it's speaking for and the current location of said robot. It returns, based on this information, what it thinks the robot should do next.

#### Coin bot behaviors

#### Scout bot behaviors

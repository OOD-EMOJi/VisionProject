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

### Coin bot behavior

````
class CoinBotBehavior implements RobotBehavior
````

Coin bots can walk *and* pick up coins, and they must be capable of deciding which is the best move.

Our coin bot maintains an internal representation of the maze in EMOJi maze format (more on this format [here](https://github.com/OOD-EMOJi/A4/blob/master/README.md)) and it uses an EMOJi-compatible Pathfinder to come up with a list of possible routes based on its current location and the number of turns it has left. It returns the first move of the best route in this list.

### Scout bot behaviors

We have created a number of scouting behaviors suitable for different needs. We recommend using different behaviors for different scouts.

#### Deterministic behaviors

* **LeftWallScout:** starts by going west and hugs the wall to its left (not west, left is relative depending on direction). Sometimes has trouble turning corners.
* **RightWallScout:** starts by going east and hugs the wall to its right. Sometimes has trouble turning corners.
* **ClockwiseTwirler:** starts by going in a direction of your choosing (constructor takes a `DirType`). Continues in that direction while there is a path. When it hits a wall, it turns clockwise until it finds a path forward.
* **CounterTwirler:** starts by going in a direction of your choosing. Continues in that direction while there is a path. When it hits a wall, it turns counterclockwise until it finds a path forward.

#### Random behaviors

* **RandomScout:** starts by going in a direction of your choosing (constructor takes a `DirType`). Continues in that direction while there is a path. When it hits a wall, it chooses from the available directions randomly.
* **CrazyScout:** doesn't remember where it's going. Picks a random direction from the paths available on each turn.

import java.util.*;

public class WallFollowerPathfinder implements Pathfinder {
    
    enum Direction {
        N, S, E, W
    }
    Maze maze;
    
    
    /* * * * * * * * * *
     * WALL  FOLLOWER: *
     * * * * * * * * * *
     * keeps a hand on the rightmost wall and follows wherever that wall goes.
     * By keeping a hand (...paw? claw? What do mice have?)
     * on the railing, the mouse is guaranteed not to get lost.
     * They will eventually reach an exit if there is one.
     * (Our maze always has an exit.)
     */
    
    public WallFollowerPathfinder(Maze m) {
        maze = m;
    }
    
    public List<Tile> findPath() {
        Tile c = maze.getStart();
        Tile prev;
        Direction dir = Direction.E;
        
        LinkedList<Tile> path = new LinkedList<Tile>();
        path.add(c);
		
        while (c != maze.getEnd()) {
            
            int x = c.getX(), y = c.getY();
            Tile west = maze.tiles[x-1][y], east = maze.tiles[x+1][y];
            Tile north = maze.tiles[x][y-1], south = maze.tiles[x][y+1];
            
            switch (dir) {
                case E:
                    if (!south.isWall()) {
                        c = south;
                        dir = Direction.S;
                    } else if (south.isWall() && !east.isWall()) {
                        c = east;
                    } else if (south.isWall() && east.isWall() && !north.isWall()) {
                        c = north;
                        dir = Direction.N;
                    } else if (south.isWall() && east.isWall() && north.isWall() && !west.isWall()) {
                        c = west;
                        dir = Direction.W;
                    } break;
                case S:
                    if (!west.isWall()) {
                        c = west;
                        dir = Direction.W;
                    } else if (west.isWall() && !south.isWall()) {
                        c = south;
                    } else if (west.isWall() && south.isWall() && !east.isWall()) {
                        c = east;
                        dir = Direction.E;
                    } else if (west.isWall() && south.isWall() && east.isWall() && !north.isWall()) {
                        c = north;
                        dir = Direction.N;
                    } break;
                case W:
                    if (!north.isWall()) {
                        c = north;
                        dir = Direction.N;
                    } else if (north.isWall() && !west.isWall()) {
                        c = west;
                    } else if (north.isWall() && west.isWall() && !south.isWall()) {
                        c = south;
                        dir = Direction.S;
                    } else if (north.isWall() && west.isWall() && south.isWall() && !east.isWall()) {
                        c = east;
                        dir = Direction.E;
                    } break;
                case N:
                    if (!east.isWall()) {
                        c = east;
                        dir = Direction.E;
                    } else if (east.isWall() && !north.isWall()) {
                        c = north;
                    } else if (east.isWall() && north.isWall() && !west.isWall()) {
                        c = west;
                        dir = Direction.W;
                    } else if (east.isWall() && north.isWall() && west.isWall() && !south.isWall()) {
                        c = south;
                        dir = Direction.S;
                    } break;
                default:
                    System.out.println("I'm stuck at tile " + c.getX() + ", " + c.getY());
            }
			path.add(c);
        }
		path.add(c);
        return path;
    }
}

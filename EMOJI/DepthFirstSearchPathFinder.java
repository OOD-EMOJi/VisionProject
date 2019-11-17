package EMOJI;

import java.util.Stack;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

/*
                 Algorithm
        
        
        1] Initialize a list to store the Tiles in the path
        2] Initialize 2D boolean array "visited" 
        3]  The findPath(): that takes the  tile, the path list, and the visited list
        4] if the tile is not the ending tile
        5] shift up, down, left , or right
        6] if the neighbor tile is not visited, visit it
        7] call findpath() recursively on each neighbor
        8] return true when you find the ending tile
        9] add the neighbor to the path list only if  the function returns true, so we can create the shortest path
  

 */

public class DepthFirstSearchPathFinder implements Pathfinder {

    // memeber fields
    private Maze maze;
    private int height;
    private int width;
    private Tile start;
    private Tile end;

    public DepthFirstSearchPathFinder(Maze maze) {
        this.maze = maze;
        this.height = maze.tiles[0].length;
        this.width = maze.tiles.length;
        this.start = maze.tiles[0][1];
        this.end = maze.tiles[width - 1][height - 2];
    }

    public List<Tile> findPath(Tile startTile, Tile endTile) {
        List<Tile> path = new ArrayList<Tile>();
        boolean[][] visited = new boolean[width][height];
        findPath(startTile, endTile, path, visited);
        //System.out.println(findPath(start, path, visited));
        Collections.reverse(path);
        System.out.println(path.size());
        return path;

    }

    public Boolean findPath(Tile startTile, Tile endTile, List<Tile> path, boolean[][] visited) {
        //System.out.println("finding path!");
        Tile currentTile = startTile;
        if (currentTile == endTile) {
            return true;
        }

        int[][] SHIFTS = {
            {0, 1}, // going down
            {1, 0}, // going right
            {-1, 0}, // going left`
            {0, -1} // going up
        };

        for (int[] shift : SHIFTS) {
            //System.out.println("Doing shifts!");
            int x = currentTile.getX() + shift[0];
            int y = currentTile.getY() + shift[1];
            if (x >= 0 && x < width && y >= 0 && y < height) {
                //System.out.println("in maze!");
                Tile neighbor = maze.tiles[x][y];
                if (canTraverse(neighbor, visited)) {
                    //System.out.println("can traverse");
                    visited[currentTile.getX()][currentTile.getY()] = true;
                    if (findPath(neighbor, endTile, path, visited)) {
                        path.add(neighbor);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
      Helper function
     */
    private Boolean canTraverse(Tile tile, boolean[][] visited) {
        return !tile.isWall() && !visited[tile.getX()][tile.getY()];
    }
}

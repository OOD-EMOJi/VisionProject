package EMOJI;

import java.util.*;

public class PathOption implements Comparable<PathOption> {

    List<Tile> path;
    int turnsLeft;
    int points;
    int earlierFactor;

    public PathOption(List<Tile> path, int turns) {
        this.path = path;
        this.turnsLeft = turns;
        this.points = 0;
        this.earlierFactor = 0;
    }

    public void countPoints() {
        points = 0;
        earlierFactor = 0;
        for (int i = 0; i < turnsLeft && i < path.size(); i++) {
            List<Thing> contents = path.get(i).getContents();
            for (Thing t : contents) {
                if (t instanceof Coin) {
                    points += ((Coin) t).getValue();
                    earlierFactor += i;
                    i++;
                }
            }
        }
    }

    public int compareTo(PathOption that) {
        if (that.points != this.points) {
            return this.points - that.points;
        } else {
            return that.earlierFactor - this.earlierFactor;
        }
    }

    @Override
    public String toString() {
        return "PathOption: " + "turns: " + turnsLeft + " size: " + path.size() + " points: " + points + " eF: " + earlierFactor;
    }

	public static class DeadEndPathOptionComparator implements Comparator<PathOption> {
		public int compare(PathOption a, PathOption b) {
			if (a.points != b.points) {
				return b.points - a.points;
			} else {
				return a.earlierFactor - b.earlierFactor;
			}
		}
	}

	public static class GetCoinPathOptionComparator implements Comparator<PathOption> {
		public int compare(PathOption a, PathOption b) {
			return b.earlierFactor - a.earlierFactor;
		}
	}


}

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
		for(int i = 0; i < turns && i < path.size(); i++) {
			List<Thing> contents = path.get(i).getContents();
			for(Thing t : contents){ 
				if( t instanceof Coin ) {
					points += ((Coin)t).getValue();
					earlierFactor += i;
				}
			}
		}
	}
	
	public int compareTo(PathOption that) {
		if(that.points != this.points) {
			return this.points - that.points;
		}
		else {
			return that.earlierFactor - this.earlierFactor;
		}
	}
}
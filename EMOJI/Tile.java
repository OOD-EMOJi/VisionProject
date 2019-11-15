import java.util.*;

public class Tile {
	
	private List<Thing> contents;
    private boolean isWall;
    private int x, y;

    public Tile(boolean isWall, int x, int y) {
        this.isWall = isWall;
		this.contents = new ArrayList<Thing>();
        this.x = x;
        this.y = y;
    }

    public boolean isWall() {
        return isWall;
    }

    public void makeWall() {
        isWall = true;
    }

    public void makePath() {
		isWall = false;
    }

	public List<Thing> getContents() {
		return contents;
	}
	
	public void addThing(Thing contents) {
		this.contents.add(contents);
	}
    
	public void removeThing(Thing thing) {
			this.contents.remove(thing);
	}
	
	public void clearContents() {
		contents.clear();
	}
	
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

	@Override
	public String toString() {
		return (isWall ? "X" : " ");
	}
}

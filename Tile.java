public class Tile {
	
	private Thing contents;
    private boolean isWall;
    private int x, y;

    public Tile(boolean isWall, int x, int y) {
        this.isWall = isWall;
		this.contents = null;
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

	public Thing getContents() {
		return contents;
	}
	
	public void setContents(Thing contents) {
		this.contents = contents;
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

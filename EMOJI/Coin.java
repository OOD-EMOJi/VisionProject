package EMOJI;
public class Coin implements Thing{
	
	int x, y, value;
	
	public Coin( int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void move() {
		return;
	}
	
	public char draw() {
		return '$';
	}
}
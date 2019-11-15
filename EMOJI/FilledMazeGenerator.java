public class FilledMazeGenerator implements MazeGenerator {
	public Tile[][] generateMaze(int w, int h) {
		Tile[][] r = new Tile[w][h];
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				r[x][y] = new Tile(true, x, y);
			}
		}
		return r;
	}
}
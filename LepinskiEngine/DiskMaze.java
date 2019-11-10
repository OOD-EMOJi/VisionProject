package LepinskiEngine;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class DiskMaze implements Maze{
    final int COIN_CHANCE = 30; 
    
    MazeLocation[][] rooms;
    int max_x;
    int max_y;

    public MazeLocation getLocation(int x, int y){
	return rooms[x][y];
    }

    public MazeLocation getTeamStart(){
	int x = max_x/2;
	return rooms[x][0];
	
    }

    public int getMaxX(){
	return max_x;
    }

    public int getMaxY(){
	return max_y;
    }

    public int getNumCoin(){
	int count = 0;
	for(int i=0; i<max_x; i++){
	    for(int j=0; j<max_y; j++){
		if (rooms[i][j].getCoins() != null){
		    count = count + rooms[i][j].getCoins().size();
		}
	    }
	}
	return count;
    }

	
    public MazeRobot getRobot(int id){	
	for(int i=0; i<max_x; i++){
	    for(int j=0; j<max_y; j++){
		List<MazeRobot> bots = rooms[i][j].getRobots();
		if (bots != null){
		    for(int k=0; k<bots.size(); k++){
			if(bots.get(k).getID() == id){
				return bots.get(k);
			}
		    }
		}
	    }
	}
	return null;
    }
    
    void placeRandomCoins(int chance){
	Random rand = new Random();
	List<CoinType> coins;
	int rand100;
	int rand4;

	for(int i=0; i<max_x; i++){
	    for(int j=0; j<max_y; j++){
		coins = new ArrayList<CoinType>();
		rand100 = rand.nextInt(100);
		if(rand100<chance){
		    coins.add(CoinType.Gold);
		}
		rooms[i][j].setTheCoins(coins);
	    }
	}
    }

    public DiskMaze(String filename){
	FileReader fr = null;
	BufferedReader reader = null;
	try{
	    fr = new FileReader(filename);
	    reader = new BufferedReader(fr);
	    String line;
	    MazeLocation loc;
	
	    max_x = Integer.parseInt(reader.readLine());
	    max_y = Integer.parseInt(reader.readLine());
	    
	    rooms = new MazeLocation[max_x][max_y];
	
	    for(int j = 0; j<max_y; j++){
		line = reader.readLine();
		for(int i = 0; i<max_x; i++){
		    loc = makeLocation(line.charAt(i),i, j);
		    rooms[i][j] = loc;
		}
	    }
	    reader.close();
	    fr.close();

	    rooms[max_x/2][0].is_start=true;
	    
	    placeRandomCoins(COIN_CHANCE);

	} catch(IOException e){
	    e.printStackTrace();
	} 
    }

    MazeLocation makeLocation(char symbol, int x, int y){
	MazeLocation loc = new MazeLocation(x,y);
	List<DirType> dirs = new ArrayList<DirType>();

	loc.is_scanned = true;

	if(symbol == '+'){
	    dirs.add(DirType.North);
	    dirs.add(DirType.South);
	    dirs.add(DirType.East);
	    dirs.add(DirType.West);
	}

	if(symbol == 'T'){
	    dirs.add(DirType.South);
	    dirs.add(DirType.East);
	    dirs.add(DirType.West);
	}
	if(symbol == 'E'){
	    dirs.add(DirType.North);
	    dirs.add(DirType.South);
	    dirs.add(DirType.East);
	}
	if(symbol == '3'){
	    dirs.add(DirType.North);
	    dirs.add(DirType.South);
	    dirs.add(DirType.West);
	}
	if(symbol == '1'){
	    dirs.add(DirType.North);
	    dirs.add(DirType.East);
	    dirs.add(DirType.West);
	}

	if(symbol == '-'){
	    dirs.add(DirType.East);
	    dirs.add(DirType.West);
	}
	if(symbol == '|'){
	    dirs.add(DirType.North);
	    dirs.add(DirType.South);
	}
	if(symbol == 'L'){
	    dirs.add(DirType.North);
	    dirs.add(DirType.East);
	}
	if(symbol == 'J'){
	    dirs.add(DirType.North);
	    dirs.add(DirType.West);
	}
	if(symbol == '7'){
	    dirs.add(DirType.South);
	    dirs.add(DirType.West);
	}
	if(symbol == 'P'){
	    dirs.add(DirType.South);
	    dirs.add(DirType.East);
	}

	if(symbol == '['){
	    dirs.add(DirType.East);
	}
	if(symbol == ']'){
	    dirs.add(DirType.West);
	}
	if(symbol == 'U'){
	    dirs.add(DirType.North);
	}
	if(symbol == '^'){
	    dirs.add(DirType.South);
	}

	loc.the_directions = dirs;
	return loc;

    }


}
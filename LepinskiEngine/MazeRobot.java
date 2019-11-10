package LepinskiEngine;
class MazeRobot{
    MazeLocation current_loc;
    ModelType the_model;
    int the_id;

    ScanMethod scan;
    CheckCoin ch_coin;
    CheckMove ch_move;

    public ModelType getModel(){
	return the_model;
    }
    
    public int getID(){
	return the_id;
    }

    public MazeLocation getCurrentLoc(){
	return current_loc;
    }

    public void setCurrentLoc(MazeLocation loc){
	current_loc = loc;
    }

    public void setCheckCoin(CheckCoin cc){
	ch_coin = cc;
    }

    public void setCheckMove(CheckMove cm){
	ch_move = cm;
    }

    public void setScanMethod(ScanMethod sc){
	scan = sc;
    }   

    public void doScan(Maze the_maze, DarkMaze dark_maze){
	scan.doScan(this, the_maze, dark_maze);
    }

    public boolean legalMove(CommandMove mov, Maze the_maze){
	return ch_move.do_check(this, mov, the_maze);
    }

    public boolean legalCoin(CoinType c){
	return ch_coin.do_check(c);
    }

    public MazeRobot(ModelType m, int id, MazeLocation loc){
	the_model = m;
	the_id = id;
	current_loc = loc;
    }
    
}
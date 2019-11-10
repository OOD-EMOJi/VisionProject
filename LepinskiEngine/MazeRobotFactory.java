package LepinskiEngine;
import java.util.List;
import java.util.ArrayList;

public class MazeRobotFactory{
    
    public static MazeRobot makeMazeRobot(ModelType model, int id, MazeLocation loc){
	
	switch (model)
	    {
	    case CoinBot: return makeCoinBot(id, loc);
	    case ScoutBot:  return makeScoutBot(id, loc);
	    }
	return null;
    }

    public static MazeRobot makeCoinBot(int id, MazeLocation loc){
	MazeRobot bot = new MazeRobot(ModelType.CoinBot, id, loc);
	List<CoinType> coins = new ArrayList<CoinType>();
	coins.add(CoinType.Gold);
  
	bot.setCheckMove(new CheckMoveNormal());
	bot.setCheckCoin(new CheckCoin(coins));
	bot.setScanMethod(new ScanMethodNormal(1));
	return bot;
    }

    public static MazeRobot makeScoutBot(int id, MazeLocation loc){
	MazeRobot bot = new MazeRobot(ModelType.ScoutBot, id, loc);
	List<CoinType> coins = new ArrayList<CoinType>();
     
	bot.setCheckMove(new CheckMoveNormal());
	bot.setCheckCoin(new CheckCoin(coins));
	bot.setScanMethod(new ScanMethodNormal(3));
	return bot;
    }
}
		    
	    
	
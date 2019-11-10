package LepinskiEngine;
import java.util.Collection;

public class CheckCoin{
    Collection<CoinType> validCoins;
    
    public boolean do_check(CoinType coin){
	if (validCoins.contains(coin)){
		return true; 
	    }
	else {
	    return false;
	}
    }

    public CheckCoin(Collection<CoinType> valid){
	validCoins = valid;
    }
}
package game.pattern;

import java.util.*;

import cards.Card;
import game.Pattern;

public class Single implements Pattern{

    @Override
    public int compare(ArrayList<Card> cardsA, ArrayList<Card> cardsB){
    	Card a = cardsA.get(0);
    	Card b = cardsB.get(0);
    	return a.compareTo(b);
    }
   

    @Override
    public String getPatternName() {
        return "single";
    }

    @Override
    public boolean isThePattern(ArrayList<Card> cards){
    	return cards.size() == 1;
    }
    
}

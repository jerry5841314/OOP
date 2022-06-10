package game.pattern;

import java.util.*;

import cards.Card;

import game.Pattern;

public class Pair implements Pattern{

    @Override
    public int compare(ArrayList<Card> cardsA, ArrayList<Card> cardsB){
    	Collections.sort(cardsA);
        Collections.sort(cardsB);
        return cardsA.get(1).compareTo(cardsB.get(1));
     }
   
    @Override
    public String getPatternName() {
        return "pair";
    }

    @Override
    public boolean isThePattern(ArrayList<Card> cards){
    	return cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank();
    }
}

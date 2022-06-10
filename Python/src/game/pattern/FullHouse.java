package game.pattern;

import java.util.*;

import cards.Card;
import cards.Rank;

import game.Pattern;

public class FullHouse implements Pattern{
    @Override
    public int compare(ArrayList<Card> cardsA, ArrayList<Card> cardsB){
    	Collections.sort(cardsA);
        Collections.sort(cardsB);
        return cardsA.get(2).compareTo(cardsB.get(2));
     }
   

    @Override
    public String getPatternName() {
        return "full house";
    }

    @Override
    public boolean isThePattern(ArrayList<Card> cards){
        if (cards.size() != 5)
            return false;

        boolean type32 = false; 
        if (cards.get(2).getRank() == cards.get(1).getRank()) // 看是三張兩張 還是兩張三張
            type32 = true;

        if (type32){
            Rank rank = cards.get(2).getRank();
            if (cards.get(0).getRank() != rank || cards.get(1).getRank() != rank)
                return false;
            rank = cards.get(3).getRank();
            if (cards.get(4).getRank() != rank)
                return false;
        }
        else{ // type 2 3
            Rank rank = cards.get(2).getRank();
            if (cards.get(3).getRank() != rank || cards.get(4).getRank() != rank)
                return false;
            rank = cards.get(0).getRank();
            if (cards.get(1).getRank() != rank)
                return false;
        }
    	return true;
    }
}

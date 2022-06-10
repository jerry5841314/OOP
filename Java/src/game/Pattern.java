package game;

import cards.*;

import java.util.*;

public interface Pattern{
    public int compare(ArrayList<Card> cardsA, ArrayList<Card> cardsB);
    public String getPatternName();
    public boolean isThePattern(ArrayList<Card> cards);
}

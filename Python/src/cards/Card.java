package cards;

import java.util.*;


public class Card implements Comparable<Card>{
    private Suit suit;
    private Rank rank;
    private String str;
    public Card(String str){
        this.str = str;
    	if (str.length() == 4){
            suit = Suit.getSuit(str.substring(0, 1));
            rank = Rank.getRank(str.substring(2, 3));
        }
        else if (str.length() == 5){
            suit = Suit.getSuit(str.substring(0, 1));
            rank = Rank.getRank(str.substring(2, 4));
        }
        else
            throw new ArithmeticException();
    }
    public Rank getRank(){
    	return rank;
    }

    public Suit getSuit(){
    	return suit;
    }

    @Override
    public int compareTo(Card oppo) {
        if (this.rank.getOrder() > oppo.rank.getOrder())
        	return 1;
        else if (this.rank.getOrder() < oppo.rank.getOrder())
        	return -1;
        else if (this.rank.getOrder() == oppo.rank.getOrder()){
        	if (this.suit.getOrder() > oppo.suit.getOrder())
        		return 1;
     		else if (this.suit.getOrder() < oppo.suit.getOrder())
        		return -1;
        	else
        		return 0;
        }
        throw new IllegalArgumentException();
    }
    @Override
    public String toString() {
        return str;
        // return suit.toString() + "[" + rank.toString() + "]";
    }
    public static String cardsToString(ArrayList<Card> cards){
        Collections.sort(cards);
        StringBuilder str = new StringBuilder();
        for (Card c : cards){
            str.append(c.toString() + " ");
        }
        return str.toString().trim();
    }

}
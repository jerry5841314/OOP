package game;

import java.util.*;

import cards.Card;

public class Player {
	private String name;
	private ArrayList<Card> handCards;

	public Player(String name){
        handCards = new ArrayList<Card>();
        this.name = name;
    }
    public String getName(){
    	return name;
    }
    public boolean isEmpty(){
        return handCards.isEmpty();
    }
    public boolean hasCard(String str){
        Collections.sort(handCards);
        return handCards.get(0).toString().equals(str);
    }
    public void getCard(Card card){
        handCards.add(card);
    }
    public void playCards(ArrayList<Card> play){
        for (Card p : play){
            int idx = handCards.indexOf(p);
            handCards.remove(idx);
        }

    }
    public void printHandCards(){
    	Collections.sort(handCards);
        StringBuilder numbers = new StringBuilder();
        StringBuilder cards = new StringBuilder();
        for (int i = 0; i < handCards.size(); i++) {
            String str = handCards.get(i).toString();
            cards.append(str).append(" ");
            numbers.append(String.format("%" + (-str.length()) + "s", i)).append(" ");
        }
        System.out.println(numbers.toString().stripTrailing());  // strip the trailing whitespaces
        System.out.println(cards.toString().stripTrailing()); // strip the trailing whitespaces
    }
    public ArrayList<Card> str2cards(String str){
        Scanner scanner = new Scanner(str);
        ArrayList<Integer> intArr = new ArrayList<Integer>();
        while (scanner.hasNextInt()){
            int idx = scanner.nextInt();
            intArr.add(idx);
        }
        Collections.sort(intArr);

        for (int i = 1; i < intArr.size(); i++){
            if (intArr.get(i).intValue() == intArr.get(i - 1).intValue())
                return null;
        }
        for (int i = 0; i < intArr.size(); i++){
            if (intArr.get(i).intValue() >= handCards.size())
                return null;
        }
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < intArr.size(); i++){
            int idx = intArr.get(i).intValue();
            cards.add(handCards.get(idx));
        }
        Collections.sort(cards);
        return cards;
    }
}
package cards;
import java.util.*;

public class Deck {
	private Stack<Card> cards;

	public Deck (String str){
		cards = new Stack<Card>();
		Scanner scanner = new Scanner(str);
		for (int i = 0; i < 52; i++){
			String str2 = scanner.next();
			cards.push(new Card(str2));
		}
	}

	public Card deal(){
		return cards.pop();
	}

	public boolean isEmpty(){
		return cards.isEmpty();
	}
}

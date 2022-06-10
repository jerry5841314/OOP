import java.util.*;

import cards.Deck;
import game.Big2;
import game.Player;

import game.Pattern;
import game.pattern.Single;
import game.pattern.Pair;
import game.pattern.Straight;
import game.pattern.FullHouse;

public class Main {
	public static void main(String[] args){
		// initialize
		Scanner scanner = new Scanner(System.in);

		// patternList 
        ArrayList<Pattern> patternList = new ArrayList<Pattern>();
        patternList.add(new Single());
        patternList.add(new Pair());
        patternList.add(new Straight());
        patternList.add(new FullHouse());

		// deck
		Deck deck = new Deck(scanner.nextLine());

		// player
		final int playerNum = 4;
		Player[] players = new Player[playerNum];
		for (int i = 0; i < playerNum; i++)
			players[i] = new Player(scanner.nextLine());

		// game initialize
		Big2 big2 = new Big2(patternList, playerNum, players, scanner);

		// 加入牌堆（這裡 deck 不放在 construtor 是為了讓相同玩家規則可以用不同牌堆重複遊戲）
		
		int winnerIdx = big2.startGame(deck);
		// 印出勝利者
		System.out.printf("Game over, the winner is %s.\n", players[winnerIdx].getName());
	}
}
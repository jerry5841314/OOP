package game;

import java.util.*;
import cards.Card;
import cards.Deck;
import cards.Rank;
import cards.Suit;

public class Big2 {
    // 重複執行遊戲不會改變的 data
    private ArrayList<Pattern> patternList;
    private int playerNum;
    private Player[] players;
    private Scanner scanner;
    // 重複執行遊戲會要 reset 的
    private int nextPlayer;
    private int winnerIdx;

    public Big2(ArrayList<Pattern> patternList, int playerNum, Player[] players, Scanner sc){
        this.patternList = patternList;
        this.playerNum = playerNum;
        this.players = players;
        this.scanner = sc;
    }
    private void dealCard(Deck deck){
        while (!deck.isEmpty())
            for (int i = 0; i < playerNum; i++)
                players[i].getCard(deck.deal());
    }
    private int findFirstPlayer(){
        for (int i = 0; i < playerNum; i++)
            if (players[i].hasCard("C[3]"))
                return i;
        throw new NullPointerException();
    }
    Pattern findPattern(ArrayList<Card> cards){
        for (int i = 0; i < patternList.size(); i++)
            if (patternList.get(i).isThePattern(cards))
                return patternList.get(i);
        return null;
    }
    // 確認是否為合法出牌：是的話回傳 pattern 名稱；不是則回傳 "none"
    private String isLegalPlay(boolean isFirstRound, boolean isNewRound, ArrayList<Card> prev, ArrayList<Card> play){
        if (play == null)
            return null;
        // 第一個玩家是否出梅花三
        if (isFirstRound && isNewRound && !play.get(0).toString().equals("C[3]"))
            return null;
        // 找到他出的牌的 pattern 
        Pattern pattern = findPattern(play);
        if (pattern == null)
            return null;
        // 若為該回合第一個玩家則無須比較 top play；否則得確認是否跟 top play 相同牌形且 order 較大
        if (isNewRound)
            return pattern.getPatternName();
        else {
            if (!pattern.isThePattern(prev) || pattern.compare(play, prev) < 0 )
                return null;
        }
        return pattern.getPatternName();
    }
    // private void tryToPlay(boolean isFirstRound, boolean isNewRound){
    private ArrayList<Card> nextPlayerPlay(boolean isFirstRound, boolean isNewRound, ArrayList<Card> prev){
        //底下 while 迴圈為重複要求一個人出牌直到他的行為合法 
        while (true){
            players[nextPlayer].printHandCards();
             // 確認 player 是否要 pass
            String str = scanner.nextLine();
            if (str.equals("-1")){
                if (isNewRound){
                    System.out.println("You can't pass in the new round.");
                    continue;
                }
                else
                    return null;
            }
            // 確認要出的牌是否合法
            ArrayList<Card> play = players[nextPlayer].str2cards(str);
            String patternName = isLegalPlay(isFirstRound, isNewRound, prev, play);
            if (patternName == null){
                System.out.println("Invalid play, please try again.");
                continue;
            }
            else {
                players[nextPlayer].playCards(play); // 把牌打掉
                return play;
            }
        }
    }
    public int startGame(Deck deck){
        winnerIdx = -1;
        dealCard(deck);
        nextPlayer = findFirstPlayer();
        boolean isFirstRound = true;
        // 玩遊戲直到出現勝利者
        while (winnerIdx == -1){
            System.out.println("New round begins.");
            boolean isNewRound = true;
            ArrayList<Card> prev = null;
            int passCount = 0;
            // 玩一回合（三個人 pass 或是產生勝利者）
            while (passCount < playerNum - 1 && winnerIdx == -1){
                //底下 while 迴圈為重複要求一個人出牌直到他的行為合法 
                System.out.printf("Next turn: %s\n", players[nextPlayer].getName());
                // 讓下一個玩家 tyr & error 直到出現可以打的牌堆
                ArrayList<Card> play = nextPlayerPlay(isFirstRound, isNewRound, prev);
                // 如打出來的牌堆不是 null 則代表有打 null 則代表合法的 pass
                if (play != null){
                    System.out.printf("Player %s plays a %s %s.\n", players[nextPlayer].getName(), findPattern(play).getPatternName(), Card.cardsToString(play));
                    prev = play;
                    passCount = 0;
                }
                else {
                    passCount++;
                    System.out.printf("Player %s passes.\n", players[nextPlayer].getName());
                }
                // 確認這個玩家是否沒牌了成為勝利者結束遊戲
                if (players[nextPlayer].isEmpty()){
                    winnerIdx = nextPlayer;
                    break;
                }
                isNewRound = false;
                nextPlayer = (nextPlayer + 1) % playerNum;
            }
            isFirstRound = false;
        }
        nextPlayer = -1;
        return winnerIdx;
    }
}

package game.pattern;

import java.util.*;

import cards.Card;
import game.Pattern;

public class Straight implements Pattern{

    @Override
    public int compare(ArrayList<Card> cardsA, ArrayList<Card> cardsB){
    	Collections.sort(cardsA);
        Collections.sort(cardsB);
        return cardsA.get(4).compareTo(cardsB.get(4));
     }
   

    @Override
    public String getPatternName() {
        return "straight";
    }

    @Override
    public boolean isThePattern(ArrayList<Card> cards){
        if (cards.size() != 5)
            return false;

        // 轉換成用 rank 的數字排列
        ArrayList<Integer> intArr = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++)
            intArr.add(cards.get(i).getRank().getNum());
        Collections.sort(intArr);

        // 處理 K 跨越到 Ａ 的情況
        if (intArr.get(0).equals(1) && intArr.get(4).equals(13)){
            for (int i = 0; i < 5 && intArr.get(i) == i + 1; i++)
                intArr.set(i, i + 1 + 13);
            Collections.sort(intArr);
        }
        
        // 檢查後一個是否為前一個加一
        for (int i = 1; i < 5; i++)
            if (intArr.get(i).intValue() != intArr.get(i - 1).intValue() + 1)
                return false;

    	return true;
    }
}

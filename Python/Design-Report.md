# Design Report
Student ID:  B06703084 
Name:  邢皓霆
Email: B06703084@ntu.edu.tw


## Software Design
class 分為三大類：cards, pattern, game  
cards 包含：Rank, Suit, Card, Deck  (置於 cards 的資料夾裡面）
pattern 包含：Pattern, Single, Pair, Straight, Fullhouse  (前一及後嗣分別置於 game 及 game/pattern 的資料夾裡面）
game 包含：big2, player (置於 game 的資料夾裡面）

1.Rank
Duty: 用 enum 來列舉 13 種牌的數字，及其個別大小跟字串等資訊
Interaction：跟 Suit 一起被包含在 Card 裡面代表牌的數字

2. Rank
Duty: 用 enum 來列舉 4 種花色，及其個別大小跟字串等資訊
Interaction：跟 Rank 一起被包含在 Card 裡面代表牌的花色

3. Card
Duty: 用來代表一張牌的數字花色，並存了其字串及一些輔助的 function 如比較牌的大小
Interaction：被使用在了以及包在 Pair, Single 等四個 pattern 裡面。及使用在 Deck, Players(手牌資訊) ,Big2 裡面。

4. Deck
Duty: 用一個 Stack 來代表牌堆（後疊上去的牌會先被發走），被支援 deal 給出一張牌, isEmpty 確認是否空了 
Interaction：裡面包含了 Card

4. Pattern
Duty: 為一個 interface，定義了 compare (比較兩個同類型牌的大小)，getPatternName（取得該 pattern 名稱)，isThePattern （確認該牌是否為某個 pattern)
Interaction：定義了 Pair, Single 等四個 pattern 之 API

5. Single
Duty: 實作 pattern，並定義了 single 的 rule
Interaction：在 big2 裡面被拿來玩

6. Pair
Duty: 實作 pattern，並定義了 pair 的 rule
Interaction：在 big2 裡面被拿來玩

7. Straight
Duty: 實作 pattern，並定義了 straight 的 rule
Interaction：在 big2 裡面被拿來玩

8. Fullhouse
Duty: 實作 pattern，並定義了 fullhouse 的 rule
Interaction：在 big2 裡面被拿來玩

9. Player
Duty: 代表一個玩家，包含了名稱及手牌等資訊
Interaction：在 main 裡面被宣告並讀入名稱，並且傳入 Big2 當作裡面的玩家

10. Big2
Duty: 實現遊戲的流程，以 startGame 為主流程（被main function call），其中的 nextPlayerPlay 則讓一個 player 嘗試出牌
Interaction：先把"牌堆"分給四個玩家，並讓玩家輪流出牌，所以也使用了 Single 等四個 pattern


## Bonus Design: Open-Closed Principle (OCP)
1. 我用 Pattern 當作 interface 定義了各個 pattern 的 API，而要加新的 pattern 時依照 interface 所定義的 API 實作新的 class
2. 我在 main 裡面宣告了 ArrayList<Pattern> patternList，並把已經有的四個 pattern (single, pair, ...) 加到裡面，並把 patternList 傳到 big2 裡面當作規則列表（DI）
3. 所以有新的 pattern class (假設叫 newPattern)時，除了加上這個 class 的 java 檔外，只要在 main 裡面多加一行 patternList.add(new FullHouse()); (Object instantiations )
結論：以上只需要 "modify the initialization procedure"，所以符合 OCP 的規範


## Class Relation Graph

除了以下連結外我在資料夾裡也放著 graph.png 供助教方便查看

![](https://i.imgur.com/j99ZBim.png)

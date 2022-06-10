package cards;

public enum Suit {
    club("C", 1),
    diamond("D", 2),
    heart("H", 3),
    spade("S", 4);
    
    private String str;
    private int orderInBig2;

    Suit(String str, int order){
        this.str = str;
        this.orderInBig2 = order;
    }
     
    public static Suit getSuit(String str){
        for (Suit suit : Suit.values()){
            if (suit.str.equals(str))
                return suit;
        }
        throw new NullPointerException();
    }
    public int getOrder(){
        return orderInBig2;
    }
    @Override
    public String toString(){
        return this.str;
    }
}

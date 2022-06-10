package cards;

public enum Rank {
	three("3", 3, 1),
	four("4", 4, 2),
	five("5", 5, 3),
	six("6", 6, 4),
	seven("7", 7, 5),
	eight("8", 8, 6),
	nine("9", 9, 7),
	ten("10", 10, 8),
	jack("J", 11, 9),
	queen("Q", 12, 10),
	king("K", 13, 11),
	ace("A", 1, 12),
	two("2", 2, 13);

	private String str;
	private int num;
	private int orderInBig2;

	Rank(String str, int num, int order){
		this.str = str;
		this.num = num;
		this.orderInBig2 = order;
	}
	
	public static Rank getRank(String str){
		for (Rank rank : values()){
			if (rank.str.equals(str))
				return rank;
		}
		throw new NullPointerException();
	}

	public int getNum(){
		return num;
	}

	public int getOrder(){
		return orderInBig2;
	}
	
    @Override
    public String toString(){
        return this.str;
    }
}
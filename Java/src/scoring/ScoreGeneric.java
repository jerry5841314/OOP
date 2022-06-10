package scoring;

import item.mobileItem.MobileItem;

public class ScoreGeneric extends ScoreConversion {

    int score;

    public ScoreGeneric(MobileItem item, int score) {
        super(item);
        this.score = score;
         
    }

    @Override
    public int getScore() {
         
        return score;
    }
    
}

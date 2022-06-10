package scoring;

import item.mobileItem.MobileItem;

public abstract class ScoreConversion {
    
    private MobileItem item;

    public ScoreConversion(MobileItem item){
        this.item = item;
    }

    public boolean compatible(MobileItem item){
        return this.item.getClass().equals(item.getClass());
    }

    public abstract int getScore();
    
}

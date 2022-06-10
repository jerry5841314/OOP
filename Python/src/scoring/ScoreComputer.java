package scoring;

import java.util.ArrayList;

import item.mobileItem.MobileItem;

public class ScoreComputer {
    
    private ArrayList<ScoreConversion> conversionList;

    public ScoreComputer(ArrayList<ScoreConversion> conversionList){
        this.conversionList = conversionList;
    }

    public void addScoreConversion(ScoreConversion conversion) {
        conversionList.add(conversion);
    }

    public void addScoreConversion(MobileItem item, int score) {
        conversionList.add(new ScoreGeneric(item, score));
    }

    public boolean compatible(MobileItem item){
        for(var conversion : conversionList){
            if(conversion.compatible(item)){
                return true;
            }
        }
        return false;
    }

    public int computeScore(MobileItem item){
        for(var conversion : conversionList){
            if(conversion.compatible(item)){
                return conversion.getScore();
            }
        }
        return 0;
    }
}

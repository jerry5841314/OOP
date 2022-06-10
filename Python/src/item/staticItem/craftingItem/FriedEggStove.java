package item.staticItem.craftingItem;

import java.awt.Point;
import crafting.recipe.FriedEggRecipe;
import item.mobileItem.MobileItem;
import model.SpriteShape;

public class FriedEggStove extends Crafter {

    public FriedEggStove(Point location, SpriteShape shape, SpriteShape productShape) {
        super(location, shape, productShape, "friedeggstove");
        
        cookTime = 10 * 1000;

        recipes.add(new FriedEggRecipe(productShape));
    }

    @Override
    public Point itemPlaceLocation(MobileItem item) {
        int x = this.getX();
        int y = this.getY();
        int w = this.getRange().width;
        int h = this.getRange().height;

        int index = items.indexOf(item);
        int itemWidth = item.getRange().width;

        int wOffset = (w - itemWidth * 2) / 3;
        int hOffset =  (h) / 5;
        return new Point(x + wOffset + index * (wOffset + itemWidth), y + hOffset); 
    }
    
}

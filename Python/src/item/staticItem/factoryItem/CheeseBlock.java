package item.staticItem.factoryItem;

import item.mobileItem.MobileItem;
import item.mobileItem.ingredient.Cheese;
import model.SpriteShape;
import java.awt.*;

public class CheeseBlock extends Factory {

    public CheeseBlock(Point location, SpriteShape shape, SpriteShape productShape) {
        super(location, shape, productShape, "cheeseblock");
    }

    @Override
    public MobileItem produceItem() {
        Cheese newItem = new Cheese(new Point(0, 0), productShape);
        this.world.addSprite(newItem);
        return newItem;
    }

}

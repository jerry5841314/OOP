package item.staticItem.factoryItem;

import item.mobileItem.MobileItem;
import item.mobileItem.ingredient.Apple;
import model.SpriteShape;
import java.awt.*;

public class AppleBox extends Factory {

    

    public AppleBox(Point location, SpriteShape shape, SpriteShape productShape) {
        super(location, shape, productShape, "applebox");
    }

    @Override
    public MobileItem produceItem() {
        Apple newItem = new Apple(new Point(0, 0), productShape);
        this.world.addSprite(newItem);
        return newItem;
    }

}

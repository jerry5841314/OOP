package item.staticItem.factoryItem;

import item.mobileItem.MobileItem;
import item.mobileItem.ingredient.Bread;
import model.SpriteShape;
import java.awt.*;

public class BreadBasket extends Factory {

    public BreadBasket(Point location, SpriteShape shape, SpriteShape productShape) {
        super(location, shape, productShape, "breadbasket");
    }

    @Override
    public MobileItem produceItem() {
        Bread newItem = new Bread(new Point(0, 0), productShape);
        this.world.addSprite(newItem);
        return newItem;
    }

}

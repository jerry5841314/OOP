package item.staticItem.factoryItem;

import java.awt.Point;

import item.mobileItem.MobileItem;
import item.mobileItem.ingredient.RawEgg;
import model.SpriteShape;

public class EggBasket extends Factory {

    public EggBasket(Point location, SpriteShape shape, SpriteShape productShape) {
        super(location, shape, productShape, "eggbasket");
    }

    @Override
    public MobileItem produceItem() {
        RawEgg newItem = new RawEgg(new Point(0, 0), productShape);
        this.world.addSprite(newItem);
        return newItem;
    }
    
}

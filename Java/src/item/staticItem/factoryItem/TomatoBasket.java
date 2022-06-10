package item.staticItem.factoryItem;

import item.mobileItem.MobileItem;
import item.mobileItem.ingredient.Tomato;
import model.SpriteShape;
import java.awt.*;

public class TomatoBasket extends Factory {

    public TomatoBasket(Point location, SpriteShape shape, SpriteShape productShape) {
        super(location, shape, productShape, "tomatobasket");
    }

    @Override
    public MobileItem produceItem() {
        Tomato newItem = new Tomato(new Point(0, 0), productShape);
        this.world.addSprite(newItem);
        return newItem;
    }

}

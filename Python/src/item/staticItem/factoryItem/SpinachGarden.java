package item.staticItem.factoryItem;

import item.mobileItem.MobileItem;
import item.mobileItem.ingredient.Spinach;
import model.SpriteShape;
import java.awt.*;

public class SpinachGarden extends Factory {

    public SpinachGarden(Point location, SpriteShape shape, SpriteShape productShape) {
        super(location, shape, productShape, "spinachgarden");
    }

    @Override
    public MobileItem produceItem() {
        Spinach newItem = new Spinach(new Point(0, 0), productShape);
        this.world.addSprite(newItem);
        return newItem;
    }

}
